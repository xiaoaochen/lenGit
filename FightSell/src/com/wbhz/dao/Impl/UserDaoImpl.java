package com.wbhz.dao.Impl;
import com.wbhz.dao.UserDao;
import com.wbhz.entity.LinkUser;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;
import com.wbhz.util.JDBCTemplate;
import org.junit.Test;

//import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public int insert(User user) throws SQLException {
        String sql = "insert into user(" +
                "user_id," +
                "name," +
                "user_name," +
                "pass_word," +
                "sex," +
                "phone_number," +
                "email," +
                "address" +
                ") values (?,?,?,?,?,?,?,?)";
        int result = 0;
        result = JDBCTemplate.insert(sql,
                user.getUserId(),
                user.getName(),
                user.getUserName(),
                user.getPassWord(),
                user.getSex(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getAddress()
                );
        return result;
    }

    @Override
    public int update(String userId,User user) throws SQLException {
        String sql = "update user set " +
                "user_id = ?," +
                "name = ?," +
                "user_name = ?," +
                "pass_word = ?," +
                "sex = ?," +
                "phone_number = ?," +
                "email = ?," +
                "count = ?," +
                "address = ?" +
                "where user_id = ?";
        int result = 0;
        result = JDBCTemplate.update(sql,
                user.getUserId(),
                user.getName(),
                user.getUserName(),
                user.getPassWord(),
                user.getSex(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getCount(),
                user.getAddress(),
                userId);
        return result;
    }

    @Override
    public User getUserByUserNameAndPassword(String userName,String passWord) throws SQLException {
        User user = null;
        String sql = "select *from user where user_name = ? and pass_word = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,userName,passWord);
        return user;
    }

    @Override
    public User getUserById(String userId) throws SQLException {
        User user = null;
        String sql = "select *from user where user_id = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,userId);
        return user;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        User user = null;
        String sql = "select *from user where name = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,name);
        return user;

    }

    @Override
    public int deleteUserByUserId(String userId) throws SQLException {
        int result = 0;
        String sql = "delete from user where user_id = ?";
        result = JDBCTemplate.delete(sql,userId);
        return result;
    }

    @Override
    public boolean judgeUserByCount(String user_id) throws SQLException {
        User user = null;
        String sql = "select * from user where user_id = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,user_id);
        System.out.println(user.getCount());
        if (user.getCount() > 3){
            return false;
        }
        return true;
    }

    @Override
    public User getUserByUserName(String userName) throws SQLException {
        User user = null;
        String sql = "select *from user where user_name = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,userName);
        return user;
    }


    @Override
    public int insretLink(LinkUser user) {
        String sql = "insert into linkuser(" +
                    "user_id," +
                    "name," +
                    "user_name," +
                    "buy_name," +

                    "phone_number" +


                    ") values (?,?,?,?,?)";
        int result = 0;
            result = JDBCTemplate.insert(sql,
                    user.getUserId(),
                    user.getName(),
                    user.getUserName(),
                    user.getBuyName(),
                    user.getPhoneNumber()


            );
            return result;
    }

    @Override
    public User getLinkUserByUserName(String userName) {
        User user = null;
        String sql = "select *from linkuser where user_name = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,userName);
        return user;
    }

    @Override
    public User getLinKUserById(String userId) throws SQLException {
        User user = null;
        String sql = "select *from linkuser where user_id = ?";
        user = JDBCTemplate.executeQuery(sql,User.class,userId);
        return user;
    }




}
