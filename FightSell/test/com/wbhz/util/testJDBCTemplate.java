package com.wbhz.util;

import org.junit.Test;

public class testJDBCTemplate {

    @Test
    public void testInsert() {
        String sql = "insert into test(time,user_age,user_name) values(?,?,?)";
        JDBCTemplate.insert(sql, DataUtils.getNowDataTime(), 12, "cwj");
    }
}

//    @Test
//    public void TestexecuteQuery(){
//        user user = null;
//        String sql = "select *from user where name = ? and age = ?";
//        user = JDBCTemplate.executeQuery(sql, user.class,1,1);
//        System.out.println(user.getAge() +" " + user.getName());
//    }
//}
