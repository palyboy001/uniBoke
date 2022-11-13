import com.uniBoke.utils.RSA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

public class Test {
    @org.junit.Test
    public void test(){
        String str = "哈哈哈";
        // 生成密钥保存
        try {
            PrivateKey privateKey = RSA.getPrivateKey("priKey.pri", "RSA");
            PublicKey publicKey = RSA.getPublicKey("pubKey.pub", "RSA");

            // 获取对象
            Cipher cipher = Cipher.getInstance("RSA");
            // 使用密钥加密
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            // 加密返回密文
            byte[] bytes = cipher.doFinal(str.getBytes());
            System.out.println(Base64.encodeBase64String(bytes));
//            System.out.println(Base64.encodeBase64String(privateKey.getEncoded()));
            // 使用公钥解密
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            // 解密
            byte[] bytes1 = cipher.doFinal(bytes);
            System.out.println(new String(bytes1));
//            System.out.println(Base64.encodeBase64String(publicKey.getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test1(){
        String str = "哈哈哈";
        String str1 = "哈哈哈";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String digest = Base64.encodeBase64String(md5.digest(str.getBytes()));
            String digest1 = Base64.encodeBase64String(md5.digest(str1.getBytes()));
            System.out.println(digest.length());
            System.out.println(digest1);
            if(digest.equals(digest1)){
                System.out.println(true);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void createJwt(){
        long exp = System.currentTimeMillis() + 1000 * 60;  // 过期时间为1分钟
        // 生成token
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("91e0c1b1-548e-4c8f-9323-ddb0141d1d03")   // id
                .setSubject("热热热")   // 主题，一般为用户名
                .setIssuer("369")  // 签发者
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(exp))  // 过期时间
                .signWith(SignatureAlgorithm.HS256,"91e0c1b1-548e-4c8f-9323-ddb0141d1d03"); // 签名算法，以及密钥
        // 打印字符串
        System.out.println(jwtBuilder.compact());
    }

    @org.junit.Test
    public void jwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5MWUwYzFiMS01NDhlLTRjOGYtOTMyMy1kZGIwMTQxZDFkMDMiLCJzdWIiOiLng63ng63ng60iLCJpc3MiOiIzNjkiLCJpYXQiOjE2NjgwNzkyNDMsImV4cCI6MTY2ODA3OTMwMn0.lgMwBpi_xmu-F2_Y3rytjleKS3eLqp-3GFHxMFPew3I";
        // 解析token，密钥，token，获取主题
        try{
            Claims claims = Jwts.parser().setSigningKey("91e0c1b1-548e-4c8f-9323-ddb0141d1d03").parseClaimsJws(token).getBody();
            System.out.println(claims.getId());
            System.out.println(claims.getSubject());
            System.out.println(claims.getIssuedAt());
            System.out.println(claims.getIssuer());
            Date date = new Date();
            System.out.println(date);
            System.out.println(date == claims.getIssuedAt());
        }catch (Exception e){
            System.err.println("token已过期");
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void test222(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().length());
    }

}
