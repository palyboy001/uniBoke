package com.uniBoke.controller;

import com.uniBoke.entity.Register;
import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface userInterface {
//    User Login: 用户登录
    RequestMessage login(Register user);
//    tokenAutoLogin：token自动登录
    RequestMessage tokenAutoLogin(Register register);
//    User register: 用户注册
    RequestMessage register(Register register);
//    upDateInformation：修改用户信息
    RequestMessage upDateInformation(User user);
//    getCOde：获取/发送验证码
    RequestMessage getCode(String phone,String index);
//    checkCode：校验验证码是否正确
    RequestMessage checkCode(String phone,String index,String code);
//    checkAccount：校验手机号是否已注册账号
    RequestMessage checkPhone(Register register);
//    User image upLoad: 用户头像上传
    RequestMessage upLoadImage(MultipartFile file, String userRole);
//    getInformation：获取用户基本信息
    RequestMessage getInformation(User user);
//    exitLogin：用户退出登录
}
