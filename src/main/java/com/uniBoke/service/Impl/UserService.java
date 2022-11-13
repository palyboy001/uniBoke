package com.uniBoke.service.Impl;

import com.uniBoke.entity.Register;
import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.RequestMessageBody;
import com.uniBoke.entity.User;
import com.uniBoke.mapper.UserMapper;
import com.uniBoke.redis.RedisCacheTest;
import com.uniBoke.service.userServiceInterface;
import com.uniBoke.utils.RandomChar;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.UUID;


@Service
@Transactional
public class UserService implements userServiceInterface {

    Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RandomChar randomChar;
    @Autowired
    private RedisCacheTest redisCacheTest;
    @Value("${url}")
    private String url;
    @Value("${imagePath}")
    private String filePath;
    @Value("${imageUrl}")
    private String imageUrl;

    /**
     * 用户登录
     * */
    @Override
    public RequestMessage loginService(Register user) {
        // 查询账号密码
        User login = userMapper.login(user.getPhone(), user.getPassword());
        // 查询不到直接返回
        if(login == null){
            logger.info(user.getPhone()+"账号不存在数据库。");
            return new RequestMessage(RequestMessageBody.password_fail.getMessage(),
                    RequestMessageBody.password_fail.getCode(),null);
        }
        // 密码正确返回用户信息
        if(login.getPhoneNumber().equals(user.getPhone()) && login.getPassword().equals(user.getPassword())){
            logger.info(user.getPhone()+"密码校验成功！");
            // 查询用户信息
            RequestMessage informationServer = getInformationServer(login);
            if(informationServer == null){
                logger.info(user.getPhone()+"账号不存在数据库。");
                return new RequestMessage(RequestMessageBody.account_not_find.getMessage(),
                        RequestMessageBody.account_not_find.getCode(),null);
            }
            logger.info(user.getPhone()+"登陆成功");
            // 更新生成token
            informationServer.setData(createJwt((User) informationServer.getData()));
            return informationServer;
        }else {
            logger.info(user.getPhone()+"密码校验失败！");
            return new RequestMessage(RequestMessageBody.password_fail.getMessage(),
                    RequestMessageBody.password_fail.getCode(),null);
        }
    }

    /**
     * 注册用户
     * */
    @Override
    public RequestMessage registerService(Register register) {
        User user = new User();
        // 生成id
        user.setUserID(UUID.randomUUID().toString());
        // 生成账号
        user.setUserAccount(generateAccount());
        // 手机号
        user.setPhoneNumber(register.getPhone());
        // 密码
        user.setPassword(register.getPasswordSecond());
        // 创建时间
        Date date = new Date(new java.util.Date().getTime());
        logger.info("创建时间为："+date);
        user.setCreateTime(date);
        user.setDelFlag("1");
        int row = userMapper.register(user);
        logger.info(row > 0?"新账号插入成功！！！":"新账号插入失败！！！");
        if(row > 0){
            // 初始化用户的信息
            if(userMapper.registerInformation(user) > 0){
                return new RequestMessage(RequestMessageBody.register_success.getMessage(),
                        RequestMessageBody.register_success.getCode(),user);
            }
        }
        return new RequestMessage(RequestMessageBody.account_not_find.getMessage(),
                RequestMessageBody.account_not_find.getCode(),user);
    }

    /**
     * 生成验证码
     *
     * */
    @Override
    public RequestMessage getCode(String phone, String index) {
        // 生成验证码
        String code = randomChar.getRandomString(6, true);
        try {
            // 切换数据库
            redisCacheTest.selectDb(Integer.parseInt(index));
            redisCacheTest.set(phone,code,60*15L);
        } catch (Exception e) {
            e.printStackTrace();
            return new RequestMessage(RequestMessageBody.send_code_error.getMessage(),
                    RequestMessageBody.send_code_error.getCode(),null);
        }
        logger.info("插入成功！！！");
        System.out.println(redisCacheTest.get(phone));
        return new RequestMessage(RequestMessageBody.send_code_success.getMessage(),
                RequestMessageBody.send_code_success.getCode(),null);
    }

    /**
     * 校验验证码
     *
     * */
    @Override
    public RequestMessage checkCode(String phone, String index, String code) {
        // 切换数据库
        redisCacheTest.selectDb(Integer.parseInt(index));
        // 校验验证码
        if(code.equals(redisCacheTest.get(phone))){
            logger.info("用户："+phone+"，校验验证"+code+"成功");
//            System.out.println("key还剩+"+redisCacheTest.ttl(phone));
//            System.out.println(redisCacheTest.ttl(phone) <= 3400);
            // 校验手机号是否注册,已注册返回用户信息
            Register register = new Register();
            register.setPhone(phone);
            return checkPhone(register);
        }
        return new RequestMessage(RequestMessageBody.check_code_error.getMessage(),
                RequestMessageBody.check_code_error.getCode(),null);
    }

    /**
     * 判断手机号是否注册
     * */
    @Override
    public RequestMessage checkPhone(Register register) {
        // 获取手机号
        String phoneNumber = userMapper.getUserPhone(register.getPhone());
        if(phoneNumber == null || phoneNumber.equals("")){
            return new RequestMessage(RequestMessageBody.account_not_find.getMessage(),
                    RequestMessageBody.account_not_find.getCode(),null);
        }
        User user = userMapper.getUserInfo(phoneNumber,"");
        if (user == null){
            System.err.println("用户为：null");
            return new RequestMessage(RequestMessageBody.account_not_find.getMessage(),
                    RequestMessageBody.account_not_find.getCode(),null);
        }
        // 格式化用户数据
        user = formatUser(user);
        return new RequestMessage(RequestMessageBody.Login_info.getMessage(),
                RequestMessageBody.Login_info.getCode(),user);
    }

    /**
     * 修改用户信息
     *
     * */
    @Override
    public RequestMessage upDateInformation(User user) {
        int i = userMapper.userInfoChange(user);
        System.out.println(i);
        System.out.println(user);
        if(i <= 0){
            logger.info("修改用户信息失败："+RequestMessageBody.info_update_fail.getMessage());
            return new RequestMessage(RequestMessageBody.info_update_fail.getMessage(),
                    RequestMessageBody.info_update_fail.getCode(),null);
        }
        logger.info("修改用户信息成功："+i);
        RequestMessage informationServer = getInformationServer(user);
        System.out.println("用户修改信息="+informationServer.getData());
        return informationServer;
    }

    /**
     * 用户头像上传
     * */
    @Override
    public RequestMessage upLoadImageServer(MultipartFile file, String userRole) {
        OutputStream outputStream = null;
        if(file != null){
            try {
                // 转换字节
                byte[] imageByte = file.getBytes();
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取点号的位置
                int index = fileName.indexOf(".");
                // 截取文件后缀
                String suffix = fileName.substring(index);

                // 创建用户目录
                String path = filePath + userRole + "\\userImage";
                File userFilePath = new File(path);
                // 判断用户文件夹是否存在
                if(!userFilePath.exists()){
                    userFilePath.mkdirs();
                }
                // 生成新文件名
                String _fileName = "";
                // 新文件名加后缀
                String _fileName_ = "";
                File saveFile = null;
                // 文件重名,重复生成di
                do {
                    _fileName = randomChar.getRandomString(10,true);
                    _fileName_ = _fileName.concat(suffix);
                    saveFile = new File(path,_fileName_);
                    logger.info("头像文件生成id："+_fileName);
                }while (!saveFile.createNewFile());
                outputStream = new FileOutputStream(saveFile);
                outputStream.write(imageByte);
                outputStream.flush();
                String image_url = url+imageUrl+userRole+ "/userImage/" +_fileName_;
                logger.info("头像文件："+_fileName_+"保存成功");
                logger.info("头像文件地址为：："+image_url);
                return new RequestMessage(RequestMessageBody.save_file_success.getMessage(),
                        RequestMessageBody.save_file_success.getCode(),image_url);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(outputStream != null){
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new RequestMessage(RequestMessageBody.save_file_fail.getMessage(),
                RequestMessageBody.save_file_fail.getCode(),null);
    }

    /**
     * 获取用户基本信息
     * */
    @Override
    public RequestMessage getInformationServer(User user) {
        logger.info("正在获取{"+user.getPhoneNumber()+"}的信息");
        user = userMapper.getUserInfo(user.getPhoneNumber(), "");
        System.out.println(user);
        // 如果返回用户不存在返回放回空
        if(user == null){
            return new RequestMessage(RequestMessageBody.account_not_find.getMessage(),
                    RequestMessageBody.account_not_find.getCode(),null);
        }
        // 格式化用户数据
        User _user = formatUser(user);
        return new RequestMessage(RequestMessageBody.login_success.getMessage(),
                RequestMessageBody.login_success.getCode(),_user);
    }

    /**
     * token自动登录
     * */
    @Override
    public RequestMessage tokenAutoLoginService(Register user) {
        // 校验token是否存在
        if(user.getToken() == null || user.getToken().equals("")){
            logger.info("token不存在");
            return new RequestMessage(RequestMessageBody.user_token_fail.getMessage(),
                    RequestMessageBody.user_token_fail.getCode(),null);
        }
        logger.info(user.getPhone()+"token存在");
        User jwt = null;
        // 如果token过期后直接返回
        if(redisCacheTest.ttl(user.getPhone()) == -2){
            logger.info(user.getPhone()+"token已过期");
            return new RequestMessage(RequestMessageBody.user_token_overdue.getMessage(),
                    RequestMessageBody.user_token_overdue.getCode(), user.getPhone());
        }
        // 检验token是否需要重新续约(当过期时间小于一天时过期),获取到的时秒数
        if(redisCacheTest.ttl(user.getPhone()) <= 3600){
            logger.info(user.getPhone()+"正在使用token续约");
            // 根据前端user ID获取用户信息
            User userInfo = userMapper.getUserInfo("",user.getPhone());
            // 格式化头像
            userInfo = formatUser(userInfo);
            jwt = createJwt(userInfo);
            logger.info(user.getPhone()+"续约成功");
            // 格式化用户信息
            // 返回用户信息
            return new RequestMessage(RequestMessageBody.login_success.getMessage(),
                    RequestMessageBody.login_success.getCode(),jwt);
        }
        try {
            // 解析token
            jwt = parseJwt(user.getToken(),user.getPhone());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new RequestMessage(RequestMessageBody.user_token_fail.getMessage(),
                    RequestMessageBody.user_token_fail.getCode(),null);
        }
        logger.info("token解析成功");
        // 进行登录
        // 返回用户信息
        return new RequestMessage(RequestMessageBody.login_success.getMessage(),
                RequestMessageBody.login_success.getCode(),jwt);

    }

    /**
     * 格式化用户的基本信息
     * */
    private User formatUser(User user){
//        System.out.println(user.getInfoImage());
        // 格式化头像路径(替换)
        user.setInfoImage(user.getInfoImage().replace(user.getUserID(),url+imageUrl+user.getUserID()));
//        System.out.println(user.getInfoImage());
        // 隐藏号码
        return user;
    }

    /**
     * 检验数据库ID是否冲突
     *
     * */
    private String generateID(){
        String userID;
        String id;
        int i = 0;
        do {
            id = randomChar.getRandomString(6,false);
            logger.info(++i+"生成id为："+id);
            userID = userMapper.getUserID(id);
        }while (userID != null);
        return id;
    }

    /**
     * 校验账号是否冲突
     *
     * */
    private String generateAccount(){
        String userID;
        String id;
        int i = 0;
        do {
            id = randomChar.getRandomString(10,true);
            logger.info(++i+"生成账号为："+id);
            userID = userMapper.getUserID(id);
        }while (userID != null);
        return id;
    }

    /**
     * 生成token
     * @param user 用户数据
     * @return 传过来的用户数据，添加上token
     * */
    private User createJwt(User user){
        logger.info(user.getPhoneNumber()+"正在生成token");
        System.out.println(user);
        long time = 60 * 60 * 24 * 7; // 7天后过期
        // 过期时间
        long exp = System.currentTimeMillis() + 1000 * time; // 7天后过期
        // 生成token
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(user.getUserID())   // id
                .setSubject(user.getNikeName())   // 主题，一般为用户名
                .setIssuer(user.getPhoneNumber())  // 签发者
                .setIssuedAt(new java.util.Date()) // 签发时间
                .setExpiration(new java.util.Date(exp))  // token过期时间
                .signWith(SignatureAlgorithm.HS256,user.getUserID()); // 签名算法，以及密钥
        // 打印字符串
        logger.info(user.getNikeName()+"的token生成成功");
        logger.info(jwtBuilder.compact());
        // 保存到redis
        try {
            redisCacheTest.set(user.getUserID(),jwtBuilder.compact(),time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setPassword(jwtBuilder.compact());
        return user;
    }

    /**
     * 解析token
     * @param token 传入token
     * @param pass 加密密钥(userID)
     * */
    private User parseJwt(String token,String pass) throws Exception {
        User user = null;
        // 解析token，密钥，token，获取主题
        try{
            // 解析token，密钥，token，获取主题
            Claims claims = Jwts.parser().setSigningKey(pass).parseClaimsJws(token).getBody();
            logger.info(claims.getIssuer()+"正在解析token"+"，id="+claims.getId()+"，昵称："+claims.getSubject());
            user = userMapper.getUserInfo(claims.getIssuer(),"");
        }catch (Exception e){
            System.err.println("token已过期");
            throw new Exception("token已过期");
        }
        return user;
    }
}
