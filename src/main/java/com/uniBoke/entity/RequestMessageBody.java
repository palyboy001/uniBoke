package com.uniBoke.entity;

public enum RequestMessageBody {

    ReSetPasswordNotMatch("两次密码不一致","1001"),
    PasswordBeyondLength("请输入长度小于16的密码","1002"),
    CodeNotCorrect("验证码不正确","1003"),
    PhoneNotCorrect("请输入正确的手机号码","1004"),
    Login_info("登录","1005"),

    Forget_pass("忘记密码","1007"),
    check_code_success("验证成功","1008"),
    check_code_error("验证码错误","1009"),
    send_code_success("验证码发送成功","1010"),
    send_code_error("验证码发送失败","1011"),
    account_not_find("账号不存在","1012"),
    login_success("登录成功","1013"),
    login_fail("登录失败","1014"),
    password_fail("账号或密码错误","1015"),
    register_success("注册成功","1016"),
    register_fail("注册失败","1017"),
    info_update_fail("用户信息修改失败","1018"),
    info_update_success("用户信息修改成功","1019"),
    save_file_success("文件上传成功","1020"),
    save_file_fail("文件上传失败","1021"),
    user_token_fail("token校验失败","1022"),
    user_token_overdue("token已过期，重新登录","1021");

    // 消息类型
    private final String message;
    // 消息类型代码
    private final String code;

//    构造函数
    RequestMessageBody(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{"
                + "\"message\":\""
                + message + '\"'
                + ",\"code\":\""
                + code + '\"'
                ;

    }
}
