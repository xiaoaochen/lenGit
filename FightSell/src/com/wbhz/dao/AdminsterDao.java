package com.wbhz.dao;

import com.wbhz.entity.Adminster;

import java.sql.SQLException;

public interface AdminsterDao {
    /**
     *
     * @param userName
     * @param passWord
     * @return 管理员
     * @throws SQLException
     */
    Adminster getAdminsterByUsernameAndPassword(String userName,String passWord) throws SQLException;
}
