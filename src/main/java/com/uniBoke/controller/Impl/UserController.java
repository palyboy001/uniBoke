package com.uniBoke.controller.Impl;

import com.uniBoke.controller.userInterface;
import com.uniBoke.entity.Register;
import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.RequestMessageBody;
import com.uniBoke.entity.User;
import com.uniBoke.service.Impl.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController implements userInterface {

    Logger logger = Logger.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    /**
     * 用户登录
     * */
    @PostMapping("/login")
    @ResponseBody
    @Override
    public RequestMessage login(@RequestBody Register user) {
        logger.info("用户：" + user.getPhone() + " 正在登录···");
        return userService.loginService(user);
    }

    /**
     * token自动登录
     * */
    @PostMapping("/token")
    @ResponseBody
    @Override
    public RequestMessage tokenAutoLogin(@RequestBody Register user) {
        logger.info("用户：" + user.getPhone() + " 正在登录···");
        return userService.tokenAutoLoginService(user);
    }
    /**
     * 用户注册
     * */
    @PostMapping("/register")
    @ResponseBody
    @Override
    public RequestMessage register(@RequestBody Register register) {
        // 格式不正确直接返回
        if(register.getPassword().equals("") || register.getPasswordSecond().equals("")
            || register.getPassword().isEmpty() || register.getPasswordSecond().isEmpty()
        ){
            return new RequestMessage(RequestMessageBody.ReSetPasswordNotMatch.getMessage(),
                    RequestMessageBody.ReSetPasswordNotMatch.getCode(),"date");
        }
        // 如果两次密码不正确直接返回
        if(!register.getPassword().equals(register.getPasswordSecond())){
            return new RequestMessage(RequestMessageBody.ReSetPasswordNotMatch.getMessage(),
                    RequestMessageBody.ReSetPasswordNotMatch.getCode(),"date");
        }
        return userService.registerService(register);
    }

    /**
     * 修改用户个人信息
     * @param user 用户对象
     * */
    @PostMapping("/update")
    @ResponseBody
    @Override
    public RequestMessage upDateInformation(@RequestBody User user) {
        return userService.upDateInformation(user);
    }

    /**
     * 获取验证码
     * */
    @GetMapping("/getCode")
    @ResponseBody
    @Override
    public RequestMessage getCode(@RequestParam("phone") String phone,@RequestParam("index") String index) {
        return userService.getCode(phone,index);
    }

    /**
     * 校验验证码是否正确
     *
     * */
    @GetMapping("/checkCode")
    @ResponseBody
    @Override
    public RequestMessage checkCode(@RequestParam("phone") String phone,
                             @RequestParam("index") String index,
                             @RequestParam("code") String code) {
        return userService.checkCode(phone,index,code);
    }

    /**
     * 校验手机号是否注册
     * */
    @Override
    public RequestMessage checkPhone(@RequestBody Register register) {
        return userService.checkPhone(register);
    }

    /**
     * 上传头像
     * */
    @RequestMapping("/upLoad")
    @ResponseBody
    @Override
    public RequestMessage upLoadImage(@RequestParam("userImage") MultipartFile file, @RequestParam("id") String userID) {
        return userService.upLoadImageServer(file,userID);
    }

    /**
     * 获取用户基本信息
     * @param user 用户信息
     * */
    @Override
    public RequestMessage getInformation(User user) {
        return userService.getInformationServer(user);
    }

}
