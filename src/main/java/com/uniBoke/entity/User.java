package com.uniBoke.entity;


import java.sql.Date;

public class User {
    private String userID = ""; // 用户id
    private String userAccount = ""; // 用户账号
    private String phoneNumber = ""; // 电话号码
    private String password = ""; // 密码?token
    private String email = ""; // 用户邮箱
    private Date createTime; //创建时间
    private String delFlag = ""; // 是否注销
    private String infoImage; // 用户头像
    private String nikeName = ""; // 用户昵称
    private String sex = ""; // 性别：1男，0女
    private String cover = ""; // 个人中心背景图片
    private String birthday = ""; // 生日
    private String address = ""; // 地址
    private String ip = ""; // ip地址
    private String education = ""; // 学历
    private String signature = ""; // 签名
    private int integral; // 积分余额
    private Date lastLoginTime; // 上一次登录时间
    private String status = ""; // 账号是否在线
    private int level = 0; // 账号等级
    private int VIPLevel = 0; // vip等级

    public String getInfoImage() {
        return infoImage;
    }

    public void setInfoImage(String infoImage) {
        this.infoImage = infoImage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVIPLevel() {
        return VIPLevel;
    }

    public void setVIPLevel(int VIPLevel) {
        this.VIPLevel = VIPLevel;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public java.sql.Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(java.sql.Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{"
                + "\"userID\":\""
                + userID + '\"'
                + ",\"userAccount\":\""
                + userAccount + '\"'
                + ",\"phoneNumber\":\""
                + phoneNumber + '\"'
                + ",\"password\":\""
                + password + '\"'
                + ",\"email\":\""
                + email + '\"'
                + ",\"createTime\":\""
                + createTime + '\"'
                + ",\"delFlag\":\""
                + delFlag + '\"'
                + ",\"infoImage\":\""
                + infoImage + '\"'
                + ",\"nikeName\":\""
                + nikeName + '\"'
                + ",\"sex\":\""
                + sex + '\"'
                + ",\"cover\":\""
                + cover + '\"'
                + ",\"birthday\":\""
                + birthday + '\"'
                + ",\"address\":\""
                + address + '\"'
                + ",\"ip\":\""
                + ip + '\"'
                + ",\"education\":\""
                + education + '\"'
                + ",\"signature\":\""
                + signature + '\"'
                + ",\"integral\":"
                + integral
                + ",\"lastLoginTime\":\""
                + lastLoginTime + '\"'
                + ",\"status\":\""
                + status + '\"'
                + ",\"level\":"
                + level
                + ",\"VIPLevel\":"
                + VIPLevel
                + "}";

    }
}
