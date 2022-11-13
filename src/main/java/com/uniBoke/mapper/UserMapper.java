package com.uniBoke.mapper;

import com.uniBoke.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 用户登录
     * */
    User login(@Param("phone") String phone, @Param("password") String password);

    /**
     *     查询数据库是否存在用户id
     * @param userID 用户ID
     * @return 返回用户id
     */
    String getUserID(String userID);

    /**
     *      注册用户
     * @param user 传入一个user用户的基类
     * @return 影响行数
     * */
    int register(User user);

    /**
     * 注册用户基本信息
     * @param user 用户ID
     * */
    int registerInformation(User user);
    /**
     * 查询用户手机号
     * @param phone 用户手机号码
     * @return String用户手机号
     * */
    String getUserPhone(String phone);

    /**
     * 获取用户的账号
     * @param account 账号
     * @return 返回用户账号
     * */
    String getUserAccount(String account);

    /**
     * 获取用基本信息
     *      任意一个都能查询到
     * @param phone 手机号码
     * @param userID 用户id
     * */
    User getUserInfo(@Param("phone") String phone,@Param("userID") String userID);

    /**
     * 修改用户基本信息
     * @param user 用户基本信息
     * @return {影响行数}
     * */
    int userInfoChange(User user);
}
