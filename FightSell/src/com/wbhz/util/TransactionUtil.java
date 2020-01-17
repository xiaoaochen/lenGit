package com.wbhz.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    private static Logger logger = Logger.getLogger(TransactionUtil.class);
    /**
     * 开启事务操作
     */
    public static void beginTransaction(){
        Connection connection = JDBCUtil.getConnection();
        logger.debug("beginTransaction:"+ connection.hashCode());
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提交事务
     */
    public static void commit(){
        Connection connection = JDBCUtil.getConnection();
        logger.debug("commit:"+ connection.hashCode());
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 回滚事务
     */
    public static void rollback(){
        Connection connection = JDBCUtil.getConnection();
        logger.debug("rollback:"+ connection.hashCode());
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 回滚事务
     */
    public static void closeResource(){
        Connection connection = JDBCUtil.getConnection();
        logger.debug("closeResource:"+ connection.hashCode());
        JDBCUtil.closeResource(connection);
    }

}


