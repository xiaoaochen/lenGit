package com.wbhz.dao.Impl;

import com.wbhz.dao.AdminsterDao;
import com.wbhz.entity.Adminster;
import com.wbhz.util.JDBCTemplate;

import java.sql.SQLException;

public class AdminsterDaoImpl implements AdminsterDao {
     /**
     * 根据账户密码获取管理员信息
     * @param userName
     * @param passWord
     * @return
     * @throws SQLException
     */
    @Override
    public Adminster getAdminsterByUsernameAndPassword(String userName, String passWord) throws SQLException {
      Adminster adminster = null;
      String sql = "select user_name, user_password from adminster where user_name = ? and user_password = ?";
      adminster = JDBCTemplate.executeQuery(sql,Adminster.class,userName,passWord);
      return adminster;
    }
}
