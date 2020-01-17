package com.wbhz.util;

import com.wbhz.dao.Impl.FlightTableDaoImpl;
import com.wbhz.entity.FlightTable;
import com.wbhz.entity.Record;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class testFlightTableDaoImpl {
    FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();
    FlightTable flightTable = new FlightTable();
    @Test
    public void testInsert(){

        flightTable.setFlightNumber("as123");
        flightTable.setEndPlace("1");
        flightTable.setTakeOffTime(DataUtils.StringTurnIntoDate("2020-01-07 22:32:47"));
        flightTable.setTickets(12);
        flightTable.setPrice(12.0);
        flightTable.setStartPlace("2");
        flightTable.setFlyingTime(12);


        try {
            flightTableDao.insert(flightTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            flightTableDao.delete(1,"as123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        flightTable.setFlightNumber("as123");
        flightTable.setEndPlace("1");
        flightTable.setTakeOffTime(DataUtils.StringTurnIntoDate("2020-01-07 23:32:47"));
        flightTable.setTickets(12);
        flightTable.setPrice(12.0);
        flightTable.setStartPlace("2");
        flightTable.setFlyingTime(13);
        try {
            flightTableDao.update(flightTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testSelectById(){
        try {
            flightTable = flightTableDao.getflightbyid(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(flightTable);
    }

    @Test
    public void testGetFlightbyFlightNumber(){
        try {
            flightTable = flightTableDao.getFlightbyflightNumber("as123", "2020-01-07 16:32:47");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(flightTable);

    }

    @Test
    public void testjdgeFlightByflightNumber(){
        try {
            boolean b = flightTableDao.judgeFlightByflightNumber("as123","2020-01-07 15:32:47");
            System.out.println(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testgetFlightbytakeOffTime(){
        List<FlightTable> list = null;
        try {
            list = flightTableDao.getFlightbytakeOffTime("2020-01-11 15:32:40");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getFlightNumber());
        }
    }

    @Test
    public void testJudgeFlyTimeByFlightNumber(){
        Boolean judge = false;
        try {
             judge = flightTableDao.judgeFlyTimeByFlightNumber("as123","2020-01-07 22:32:47");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(judge);
    }

    @Test
    public void testDeleteFlightByFlightNumber() throws SQLException {
        flightTableDao.deleteFlightByFlightNumber("as123");
    }

    @Test
    public void testgetFidbyTimeAndPlace(){
        List<FlightTable> flightTable = null;
        flightTable = flightTableDao.getFidbyTimeAndPlace("2", "taizhou","2020-01-12 22:32:48");
        System.out.println(flightTable);

    }

    @Test
    public void testSearch(){
        FlightTable flightTable = null;
        flightTable = flightTableDao.getByAlltickets(20);
        System.out.println(flightTable.getAlltickets());
    }

    @Test
    public void testFunction() throws SQLException {
        FlightTable flightTable =flightTableDao.getflightbyid(1);
        flightTableDao.setFlightState();
        System.out.println(flightTable);

    }
}
