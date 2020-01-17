package com.wbhz.dao;

import com.wbhz.entity.Record;
import com.wbhz.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface RecordDao {
    /**
     * 记录的持久层操作
     * @author xiaoao
     */

    /**
     * 添加一条记录
     * @param record
     * @throws SQLException
     */
    int insret(Record record) throws SQLException;

    /**
     * 查询所有记录信息
     * @return
     * @throws SQLException
     */
    List<Record> queryAll() throws SQLException;

    /**
     * 根据给的信息查询
     * @param startPlace 出发地点
     * @param endPlace 目的地
     * @return
     * @throws SQLException
     */
    List<Record> queryAllByInformation(String startPlace, String endPlace)throws SQLException;

    /**
     * 根据用户的用户名查询
     * @param userId 用户名
     * @return
     * @throws SQLException
     */
     Record queryByUserIdAndOrderNumber(String byName,String orderNumber) throws SQLException;

    /**
     * 根据航班号查询
     * @param FlightNumber 航班号
     * @return
     * @throws SQLException
     */
    List<Record> queryAllByFlightNumber(String FlightNumber) throws SQLException;

    /**
     * 根据航班号和飞机号判断座位号是否售空
     * @param FlightNumber
     * @param seat
     * @return
     * @throws SQLException
     */
    Boolean JudgeRecordByFlightNumberAndSeat(String FlightNumber,int seat) throws SQLException;

    /**
     * 根据航班号查询该航班的空座位
     * @param FlightNumber 航班号
     * @return
     * @throws SQLException
     */
    List<Record> getUnusedSeatsByFlightNumber(String FlightNumber) throws SQLException;

    /**
     * 更具用户的身份证号来查询订单记录的信息
     * @param id 用户的身份证
     * @return
     * @throws SQLException
     */
    List<Record> getRecordByUserId(String id) throws SQLException;

    /**
     * 根据序号更新记录信息
     * @param orderNumber
     * @throws SQLException
     */
    int updateStateById(String orderNumber,String state) throws SQLException;

    /**
     * 通过orderNumber获取订单
     * @param orderNumber
     * @return
     * @throws SQLException
     */
    Record getFlightTableByOrderNumber(String orderNumber) throws SQLException;

    /**
     * 根据票的状态获取飞机票
     * @param userId
     * @return
     */
    Record gerNowFlightByState(String userId);

    /**
     * 修改订单
     * @param record
     * @return
     */
    int update(Record record);

    /**
     * 根据座位号
     * @param seat
     * @param canUsed
     * @return
     */
    Record getRecordByseat(String flightNumber,int seat,String canUsed);

    /**
     *
     * @param flighyNumber
     * @param takeOffTime
     * @return
     */
    List<Record> getRecordBycondtion(String flighyNumber,String takeOffTime);

    /**
     *
     * @param userId
     * @return
     */
    Record queryByUserId(String userId);

    /**
     *
     * @param userId
     * @param takeOffTime
     * @param flightNumber
     * @return
     */
    Record queryByUserIdAndTakeOfftime(String userId,String takeOffTime,String flightNumber);

    List<Record> queryListByById(String buyId);


}
