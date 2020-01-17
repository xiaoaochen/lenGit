package com.wbhz.dao;

import com.wbhz.entity.FlightTable;

import java.sql.SQLException;
import java.util.List;

public interface FightTableDao {
    /**
     * 管理员操作插入一条新的航班记录
     * @param flightTable
     * @throws SQLException
     */
    int insert(FlightTable flightTable) throws SQLException;

    /**
     * 管理员删除一条航班记录
     * @param id
     * @param flightNumber
     * @throws SQLException
     */
    int delete(int id,String flightNumber) throws SQLException;

    /**
     * 管理员更新一条航班记录
     * @param flightTable
     * @return
     * @throws SQLException
     */
    int update(FlightTable flightTable) throws SQLException;

    /**
     * 根据ID获得航班的信息
     * @param id
     * @return
     * @throws SQLException
     */
    FlightTable getflightbyid(int id) throws SQLException;

    /**
     * 根据航班号和出发日期查询航班信息，便于添加航班的时候做判断
     * @param flightNumber
     * @param takeOffTime
     * @return
     * @throws SQLException
     */
    FlightTable getFlightbyflightNumber(String flightNumber,String takeOffTime) throws SQLException;

    /**
     * 根据航班号判断该航班在数据库中是否存在
     * @param flghtNumber 航班号
     * @return
     * @throws SQLException
     */
    boolean judgeFlightByflightNumber(String flghtNumber,String takeOffTime) throws SQLException;

    /**
     * 根据时间段获取航班号
     * @param takeOffTimeOne
     * @return
     * @throws SQLException
     */
    List<FlightTable> getFlightbytakeOffTime(String takeOffTimeOne) throws SQLException;

    /**
     * 根据航班号确认此航班在这个时间是否存在
     * @param flightNumber
     * @return
     * @throws SQLException
     */
    boolean judgeFlyTimeByFlightNumber(String flightNumber,String takeOffTime) throws SQLException;

    /**
     * 根据航班号删除所有该航班（万一飞机爆炸，地球毁灭）
     * @param flightNumber
     * @return
     * @throws SQLException
     */
    int deleteFlightByFlightNumber(String flightNumber) throws  SQLException;

    /**
     *
     * @param startPlace 出发地
     * @param endPlace 目的地
     * @param takeOffTime 出发时间
     * @return
     */
    List<FlightTable> getFidbyTimeAndPlace(String startPlace,String endPlace,String takeOffTime);

    FlightTable getByAlltickets(int seat);

    List<FlightTable> getFidbyTimeAndPlace(String startPlace,String endPlace,String startTime,String enfTime);

    List<FlightTable> getFIDbyOrderNumber(String flightNumber);

    List<FlightTable> getFidByFlightId(int id);

    void setFlightState();
}
