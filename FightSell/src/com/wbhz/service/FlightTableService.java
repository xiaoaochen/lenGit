package com.wbhz.service;

import com.wbhz.entity.FlightTable;

import java.util.List;

public interface FlightTableService {
    /**
     * 航班信息的事务
     */

    /**
     * 更新航班信息
     * @param flightTable 航班的实体类
     * @return
     */
    int update(FlightTable flightTable);

    /**
     * 根据航班号获取航班
     * @param  orderNumber 航班号
     * @return
     */
    FlightTable getFlightTableByOrderNumber(String orderNumber);

    /**
     * 根据ID获取航班信息
     * @param id
     * @return
     */
    FlightTable getFlightById(int id);

    /**
     *
     * @param startTime
     * @param endTime
     * @param startPlace
     * @param endPlace
     * @return
     */
    List<FlightTable> getFidBy(String startTime,String endTime,String startPlace,String endPlace);

    /**
     * 根据
     * @param flightNumber
     * @return
     */
    List<FlightTable> getFidNumber(String flightNumber);

}
