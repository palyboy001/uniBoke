package com.uniBoke.entity;

/**
 *  用户注册类：
 *      phoneNumber:电话号码
 *      password：输入的密码/注册时候的验证码
 *
 * */

public class Register {
    private String phone;
    private String password;
    private String passwordSecond;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSecond() {
        return passwordSecond;
    }

    public void setPasswordSecond(String passwordSecond) {
        this.passwordSecond = passwordSecond;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{"
                + "\"phone\":\""
                + phone + '\"'
                + ",\"password\":\""
                + password + '\"'
                + ",\"passwordSecond\":\""
                + passwordSecond + '\"'
                + ",\"token\":\""
                + token + '\"'
                + "}";

    }
}
