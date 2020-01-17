package com.wbhz.util;

import com.wbhz.dao.Impl.FlightTableDaoImpl;
import com.wbhz.entity.FlightTable;
import com.wbhz.service.Impl.FlightTableServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class testfFlightTableServiceImpl {
    FlightTableServiceImpl flightTableService = new FlightTableServiceImpl();
    FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();
    @Test
    public void testGetFlightTableByOrderNumber(){
        FlightTable flightTable = flightTableService.getFlightTableByOrderNumber("qw");
        System.out.println(flightTable);
    }

    @Test
    public void testUpdate(){
        FlightTable flightTable = null;
        try {
            flightTable  = flightTableDao.getflightbyid(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        flightTable.setEndPlace("taizhou");

        flightTableService.update(flightTable);
    }
}
