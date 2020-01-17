package com.wbhz.util;

import com.wbhz.dao.Impl.UserDaoImpl;
import com.wbhz.dao.UserDao;
import com.wbhz.entity.LinkUser;
import com.wbhz.entity.User;
import org.junit.Test;

import java.sql.SQLException;

public class testUserDaoImpl {
    UserDaoImpl userDao = new UserDaoImpl();
    User user = new User();
    @Test
    public void testInsert(){
        user.setName("陈伟杰");
        user.setUserId("321202199804024818");
        user.setUserName("xiaoao");
        user.setPassWord("123");
        user.setSex("nan");
        user.setPhoneNumber("18252601398");
        user.setAddress("1231");
        user.setEmail("969117612@qq.com");
        try {
            userDao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        int result = 0;
        try {
           result = userDao.deleteUserByUserId("321202199804024818");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCount() throws SQLException {
        boolean result = false;
        result = userDao.judgeUserByCount("321202199804024818");
        System.out.println(result);

    }

    @Test
    public void testUpdate() throws SQLException {
        user.setName("chen");
        user.setUserId("321202199804024818");
        user.setUserName("xiaoao");
        user.setPassWord("123");
        user.setSex("nan");
        user.setPhoneNumber("182526013981");
        user.setAddress("1231");
        user.setEmail("969117612@qq.com");
        userDao.update(user.getUserId(),user);
    }

//    @Test
//    public void testGetUserByUserName(){
//        user.setName("chen");
//        userDao.getUserByName()
//    }
    @Test
    public void testgetUserByUserName(){
        User user = null;
        try {
            user = userDao.getUserByName("c");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Test
    public void testDeleteUserByUserId(){
        try {
            userDao.deleteUserByUserId("321202199804024818");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertLink(){
        LinkUser linkUser  = new LinkUser();
        System.out.println(linkUser.getEmail());
    }

}
