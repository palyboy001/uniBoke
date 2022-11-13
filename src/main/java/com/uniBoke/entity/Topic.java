package com.uniBoke.entity;

import java.sql.Date;

public class Topic {
    private String id;// 话题id
    private User userRole; // 发布用户信息
    private String content; // 内容
    private String topicImage; // 图片
    private String address; // 地址
    private Date creteTime; // 创建时间
    private String remindUser; // @的用户id
    private int  read_count; // 阅读量
    private int like_count; // 点赞量
    private int collect_count; // 收藏量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserRole() {
        return userRole;
    }

    public void setUserRole(User userRole) {
        this.userRole = userRole;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
    }

    public String getRemindUser() {
        return remindUser;
    }

    public void setRemindUser(String remindUser) {
        this.remindUser = remindUser;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    @Override
    public String toString() {
        return "{"
                + "\"id\":\""
                + id + '\"'
                + ",\"userRole\":"
                + userRole
                + ",\"content\":\""
                + content + '\"'
                + ",\"topicImage\":\""
                + topicImage + '\"'
                + ",\"address\":\""
                + address + '\"'
                + ",\"creteTime\":\""
                + creteTime + '\"'
                + ",\"remindUser\":\""
                + remindUser + '\"'
                + ",\"read_count\":"
                + read_count
                + ",\"like_count\":"
                + like_count
                + ",\"collect_count\":"
                + collect_count
                + "}";

    }
}
