package com.wbhz.service;

import com.wbhz.entity.FlightTable;
import com.wbhz.entity.LinkUser;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * 用户的业务层
     * @author xiaoao
     */

    /**
     * 用户的注册
     * @param user 用户的实体类
     * @return true注册成功 false 注册失败
     */
    boolean regiser(User user);

    /**
     * 用户的登陆
     * @param userName
     * @param passWord
     * @return true登陆成功 false 登陆失败
     */
    boolean judgeIsInTable(String userName,String passWord);

    /**
     * 根据用户的用户名查询
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 更新用户信息
     * @param user
     */
    int update(User user);

    /**
     * 根据用户的身份证号码来查询他所有的航班记录
     * @param userId
     * @return
     */
    List<Record> queryFlightByUserId(String userId);

    /**
     * 根据用户的身份证查询目前订的航班信息
     * @param userId
     * @return
     */
    FlightTable queryNowFlightByUserId(String userId,String state);

    /**
     * 根据用户的身份证查询目前的订的飞机票信息
     * @param userId
     * @return
     */
    Record queryNowRecordByUserId(String userId,String state);

    /**
     * 根据出发地和目的地查询可以预定的票
     * @param placeOne 出发地
     * @param placeTwo 目的地
     * @param maxTime 最晚时间
     * @return
     */
    List<FlightTable> queryCanPreFlight(String placeOne,String placeTwo,String maxTime);

    /**
     * 退票
     * @param  userId
     * @return
     */
    int returnTicket(String userId);

    /**
     * 买票
     * @param userId
     * @return
     */
    int byTickets(String userId,Record record);

    /**
     * 改签
     * @param userId
     * @return
     */
    int changeTicket(String userId,Record record);

    /**
     *
     * @param userId
     * @param user
     * @return
     * @throws SQLException
     */
    int update(String userId,User user) throws SQLException;

    public List<Integer> showIsCanUsed(String takeOffTime,String fligtNumber);

    public User getUserByUserLinkName(String userName);
    public boolean regiserNew(LinkUser user);


}
