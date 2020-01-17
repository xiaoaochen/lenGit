package com.wbhz.util;

import com.mysql.cj.jdbc.JdbcConnection;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTemplate {
    /**
     * 模板类提供增删改查的方法
     */

    public static void setParams(PreparedStatement preparedStatement,Object...params){
        //参数不为空
        if (params != null && params.length > 0){
            for (int i = 0; i < params.length; i++) {
                try {
                    preparedStatement.setObject(i + 1,params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查询列表
     * @param sql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> executeQueryList(String sql, Class<T> clazz, Object...params){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = JDBCUtil.getConnection();
        List<T> entityList = new ArrayList<T>();
        if (clazz == null){
            clazz = (Class<T>) HashMap.class;
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            int fieldCount = resultSetMetaData.getColumnCount();
            Map<String,String> columnMap = new HashMap<String, String>();
            Map<String,Object> resultMap = new HashMap<String, Object>();
            for (int i = 0; i < fieldCount; i++) {
                columnMap.put(StringUtil.underLinetoCamel(StringUtil.toLowerCase(resultSetMetaData.getColumnName(i + 1))),resultSetMetaData.getColumnName(i + 1));
                resultMap.put(StringUtil.underLinetoCamel(StringUtil.toLowerCase(resultSetMetaData.getColumnName(i + 1))),null);

            }
            while (resultSet.next()){
                for(String key : resultMap.keySet()){
                    resultMap.put(key,resultSet.getObject(columnMap.get(key)));
                }

                T t = (T)clazz.newInstance();
                BeanUtils.populate(t,resultMap);
                entityList.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection,preparedStatement,resultSet);
        }
        return entityList;
    }

    /**
     * 查询单个实体或Map
     * @param <T>
     * @param sql 数据库语句
     * @param params
     * @return
     */
    public static <T> T executeQuery(String sql, Class<T> clazz,Object... params){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = JDBCUtil.getConnection();
        T entity = null;
        if (clazz == null){
            clazz = (Class<T>) HashMap.class;
        }
        if (StringUtil.isEmpty(sql)){
            System.err.println("参数异常");
            Map resultMap = new HashMap();
            resultMap.put("message","参数异常");

            try {
                entity = (T)clazz.newInstance();
                BeanUtils.populate(entity,resultMap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return entity;
        }

        try {
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            int fieldCount = resultSetMetaData.getColumnCount();
            Map<String,String> columnMap = new HashMap<String, String>();
            Map<String,Object> resultMap = new HashMap<String, Object>();
            for (int i = 0; i < fieldCount; i++) {
                columnMap.put(StringUtil.underLinetoCamel(StringUtil.toLowerCase(resultSetMetaData.getColumnName(i + 1))),resultSetMetaData.getColumnName(i + 1));
                resultMap.put(StringUtil.underLinetoCamel(StringUtil.toLowerCase(resultSetMetaData.getColumnName(i + 1))),null);
            }
            if (resultSet.first()){
                for (String key : resultMap.keySet()) {
                    resultMap.put(key,resultSet.getObject(columnMap.get(key)));
                }
                entity = (T)clazz.newInstance();
                BeanUtils.populate(entity,resultMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(connection,preparedStatement,resultSet);
        }
        return entity;

    }

    /**
     * 修改语句标准方法
     * @param sql 数据库语句
     * @param params 参数
     * @return
     */
    public static int update(String sql,Object...params){
        Boolean isTransaction = true;
        int countRow = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(StringUtil.isEmpty(sql)){
            System.err.println("参数异常");
            return countRow;
        }
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            countRow = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (isTransaction){
                JDBCUtil.closeResource(preparedStatement);
            }else {
                JDBCUtil.closeResource(connection,preparedStatement);
            }

        }
        return countRow;
    }

    /**
     * 插入标准语句
     * @param sql 数据库语句
     * @param params 参数
     * @return
     */
//    public Integer insert(String sql,Object...params){
//        Integer number =  update(sql,params);
//        System.out.println("插入:" + ((number > 0) ? "成功":"失败"));
//        return number;
//    }
    public static int insert(String sql,Object...params) {
        int resultNumber = 0;
        boolean isTransaction = true;
        Connection connection = null;//数据库连接
        PreparedStatement preparedStatement = null;//sql操作对象
        connection =JDBCUtil.getConnection();
        //获取连接

        //获取sql操作对象
        try {

            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            resultNumber = preparedStatement.executeUpdate();
            System.out.println("执行结果： " + resultNumber);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if (isTransaction){
                JDBCUtil.closeResource(preparedStatement);
            }else {
                JDBCUtil.closeResource(connection,preparedStatement);
            }
        }
        return resultNumber;

    }

    /**
     * 删除标准语句
     * @param sql 数据库语句
     * @param params 参数
     * @return
     */
    public static int  delete(String sql, Object... params){
        int updateResult = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isTransaction = true;
        try{
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            updateResult = preparedStatement.executeUpdate();
            System.out.println("修改结果：" + ((updateResult > 0)?"成功":"失败"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (isTransaction){
                JDBCUtil.closeResource(preparedStatement);
            }else {
                JDBCUtil.closeResource(connection,preparedStatement);
            }
        }
        return updateResult;

    }
}
