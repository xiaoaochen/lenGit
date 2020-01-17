package com.wbhz.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 工具类，主要提供数据库连接，关闭连接
 */
public class JDBCUtil {
    private static Logger logger = Logger.getLogger(JDBCUtil.class);
    static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    static Properties dbProperties  = PropertiesUtil.getProperties("datasource.properties");
    static DataSource dataSource = null;
    static {
        try {
            //1.通过数据源的工厂类获取数据源
            dataSource = BasicDataSourceFactory.createDataSource(dbProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取链接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
           connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
        }
        return connection;
    }

    /**
     * 获取事务连接
     * @return
     */
    public static Connection getTransactionConnection(){
        Connection connection = null;
        try {
            connection = threadLocal.get();
            if (connection == null) {
                threadLocal.set(dataSource.getConnection());
                connection = threadLocal.get();
            }
        }catch (SQLException e){
            logger.error(e);
        }
        return connection;
    }

    /**
     * 关闭连接
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (null != resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        if (null != connection){
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        if (null != preparedStatement){
            try {
                preparedStatement.close();
            }catch (SQLException e){
                logger.error(e);
            }
        }
    }

    /**
     * 关闭连接
     * @param preparedStatement
     */
    public static void closeResource(PreparedStatement preparedStatement){
        closeResource(null,preparedStatement,null);
    }

    /**
     * 关闭连接
     * @param connection
     * @param preparedStatement
     */
    public static void closeResource(Connection connection,PreparedStatement preparedStatement){
        closeResource(connection,preparedStatement,null);
    }

    /**
     * 关闭连接
     * @param connection
     */
    public static void closeResource(Connection connection){
        threadLocal.remove();
        closeResource(connection,null,null);
    }

}
