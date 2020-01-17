package com.wbhz.dao;

import com.wbhz.entity.LinkUser;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 插入一条新的用户信息
     *
     * @param user 用户对象
     * @throws SQLException
     */
    int insert(User user) throws SQLException;

    /**
     * 更新用户的对象
     *
     * @param user 用户对象
     * @throws SQLException
     */
    int update(String userId, User user) throws SQLException;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return
     * @throws SQLException
     */
    User getUserByUserNameAndPassword(String userName, String passWord) throws SQLException;

    /**
     * 根据用户的身份证号码查询用户信息
     *
     * @param id 用户的身份证号码
     * @return
     * @throws SQLException
     */
    User getUserById(String id) throws SQLException;

    /**
     * 根据用户的真实姓名查询用户信息
     *
     * @param name 用户的真实姓名
     * @return
     * @throws SQLException
     */
    User getUserByName(String name) throws SQLException;

    /**
     * 根据身份证号码删除用户信息（管理员操作）/（用户注销账户）
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    int deleteUserByUserId(String userId) throws SQLException;

    /**
     * 根据count判断用户是否被锁定
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    boolean judgeUserByCount(String userId) throws SQLException;

    /**
     * @param userName
     * @return
     * @throws SQLException
     */
    User getUserByUserName(String userName) throws SQLException;

    int insretLink(LinkUser user);
    User getLinkUserByUserName(String userName);
    User getLinKUserById(String userId) throws SQLException;

}