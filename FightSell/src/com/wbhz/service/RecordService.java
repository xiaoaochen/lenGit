package com.wbhz.service;

import com.wbhz.entity.Record;
import com.wbhz.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface RecordService {
    /**
     * 机票的事务层
     */

    /**
     * 当购买一张票时，即在record表中插入一条记录
     * @param record
     * @return
     */
    int insert(Record record);

    /**
     *
     * @param orderNumber
     * @param seat
     * @return
     */
    boolean getRecordByFidAndSeat(String orderNumber,int seat);

    /**
     * 根据航班号和编号获取目前可以使用的座位号
     * @param id
     * @return
     */
    List<Record> seatIsUsedByflightId(int id);

    /**
     * 根据ID查询订单
     * @param id
     * @return
     */
    Record getRecordById(String UserId);

    /**
     * 根据订单号改变订单状态
     * @param orderNumber
     * @return
     */
    int updateStateByOrderNumber(String orderNumber,String state);

    /**
     *
     * @param flightNumber
     * @param takeOffTime
     * @return
     */
    List<Record> getRecordByFlightNumberAndTakeofftime(String flightNumber,String takeOffTime);

    /**
     *
     * @param userId
     * @param orderNumber
     * @return
     */
    Record getRecordByUserIdAndOrderNumber(String userId,String orderNumber);

    /**
     *
     * @param seat
     * @param canUsed
     * @return
     */
    Record getRecordByseat(String flightNumber,int seat,String canUsed);

    /**
     *
     * @param record
     * @return
     * @throws SQLException
     */
    int insret(Record record) throws SQLException;

    /**
     *
     * @param userId
     * @param orderNumber
     * @return
     * @throws SQLException
     */
    Record queryByUserIdAndOrderNumber(String userId,String orderNumber) throws SQLException;

    /**
     *
     * @param orderNumber
     * @param state
     * @return
     */
    int updateStateById(String orderNumber,String state);

    Boolean isOneTicket(String flightNumber,String takeOffTime,String userId);


    public int recordUpDate(Record record);

    public List<Record> getListByBuyId(String buyId);
}
