package com.wbhz.dao.Impl;

import com.wbhz.constants.StateOfFlight;
import com.wbhz.dao.FightTableDao;
import com.wbhz.entity.FlightTable;
import com.wbhz.util.DataUtils;
import com.wbhz.util.JDBCTemplate;
import jdk.nashorn.internal.scripts.JD;

import javax.sql.rowset.spi.SyncResolver;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FlightTableDaoImpl implements FightTableDao {
    /**
     * 添加航班记录
     * @param flightTable
     * @throws SQLException
     */
    @Override
    public int insert(FlightTable flightTable) throws SQLException {
        int result = 0;
        String sql = "insert into flighttable(" +
                "flight_number," +
                "take_off_time," +
                "flying_time," +
                "start_place," +
                "end_place," +
                "tickets," +
                "alltickets,"+
                "price" +
                ") values (?,?,?,?,?,?,?,?)";

        try {
            result = JDBCTemplate.insert(sql,flightTable.getFlightNumber(),
                    flightTable.getTakeOffTime(),
                    flightTable.getFlyingTime(),
                    flightTable.getStartPlace(),
                    flightTable.getEndPlace(),
                    flightTable.getTickets(),
                    flightTable.getAlltickets(),
                    flightTable.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("输入有误");
        }
        return result;

    }

    @Override
    /**
     * 根据航班号和ID删除航班
     */
    public int delete(int id, String flightNumber) throws SQLException {
        int row = 0;
        String sql = "delete from flighttable where id = ? and flight_number = ?";
        row = JDBCTemplate.delete(sql,id,flightNumber);
        return row;
    }

    @Override
    /**
     * 修改航班信息,根据ID编号
     */
    public int update(FlightTable flightTable) throws SQLException {
        int result = 0;
        String sql = "update flighttable" +
                " set flight_number = ?" +
                ",take_off_time = ?" +
                ",flying_time = ?" +
                ",start_place = ?" +
                ",end_place = ?" +
                ",tickets = ?" +
                ",price = ?" +
                "where id = ?";
        result =JDBCTemplate.update(sql,flightTable.getFlightNumber(),flightTable.getTakeOffTime(),
                flightTable.getFlyingTime(),flightTable.getStartPlace(),flightTable.getEndPlace(),
                flightTable.getTickets(),flightTable.getPrice(),flightTable.getId());
        return result;
    }

    @Override
    /**
     * 通过ID编号来获取航班信息
     */
    public FlightTable getflightbyid(int id) throws SQLException {
        FlightTable flightTable = null;
        String sql = "select *from flighttable where id = ?";
        flightTable = JDBCTemplate.executeQuery(sql,FlightTable.class,id);
        return flightTable;
    }

    @Override
    /**
     * 通过航班号和出发时间来获取航班信息
     */
    public FlightTable getFlightbyflightNumber(String flightNumber, String takeOffTime) throws SQLException {
        FlightTable flightTable = null;
        String sql = "select *from flighttable where take_off_time = ? and flight_number = ?";
        flightTable = JDBCTemplate.executeQuery(sql,FlightTable.class,takeOffTime,flightNumber);
        return flightTable;
    }

    @Override
    /**
     * 通过航班号和出发时间来判断是否有这个航班存在
     */
    public boolean judgeFlightByflightNumber(String flghtNumber,String takeOffTime) throws SQLException {
        if (getFlightbyflightNumber(flghtNumber,takeOffTime) != null){
            return true;
        }
        return false;
    }

    @Override
    /**
     * 获取在此出发时间之前的航班信息
     */
    public List<FlightTable> getFlightbytakeOffTime(String takeOffTimeOne) throws SQLException {
        List<FlightTable> list = null;
        String sql = "select *from flighttable where take_off_time < ?";
        list = JDBCTemplate.executeQueryList(sql,FlightTable.class,takeOffTimeOne);
        return list;
    }

    @Override
    public boolean judgeFlyTimeByFlightNumber(String flightNumber,String takeOffTime) throws SQLException {
        FlightTable flightTable = null;
        String sql = "select take_off_time from flighttable where flight_number = ?";
        flightTable = JDBCTemplate.executeQuery(sql,FlightTable.class,flightNumber);
        System.out.println(flightTable.getTakeOffTime());
        System.out.println( DataUtils.StringTurnIntoDate(takeOffTime));

//        System.out.println(DataUtils.DataIntoString(flightTable.getTakeOffTime()));
        if (flightTable.getTakeOffTime().getTime() ==  DataUtils.StringTurnIntoDate(takeOffTime).getTime()){

            return false;
        }
        return true;
    }

    @Override
    public int deleteFlightByFlightNumber(String flightNumber) throws SQLException {
        String sql = "delete from flighttable where flight_number = ?";
        int result = 0;
        result = JDBCTemplate.delete(sql,flightNumber);
        return result;
    }

    @Override
    public List<FlightTable> getFidbyTimeAndPlace(String startPlace, String endPlace, String takeOffTime) {
        String sql = "select *from flighttable where start_place = ? and end_place = ? and take_off_time < ?";
        List<FlightTable> flightTables = JDBCTemplate.executeQueryList(sql,FlightTable.class,startPlace,endPlace,takeOffTime);
        return flightTables;
    }

    @Override
    public FlightTable getByAlltickets( int seat) {
        String sql = "select *from flighttable where alltickets = ?";
        FlightTable flightTable = JDBCTemplate.executeQuery(sql,FlightTable.class,seat);
        return flightTable;
    }

    @Override
    public List<FlightTable> getFidbyTimeAndPlace(String startPlace, String endPlace, String startTime, String enfTime) {
        String sql = "select *from flighttable where start_place = ? and end_place = ? and take_off_time > ? and take_off_time < ?";
        List<FlightTable> flightTables = JDBCTemplate.executeQueryList(sql,FlightTable.class,startPlace,endPlace,startTime,enfTime);
        return flightTables;
    }

    @Override
    public List<FlightTable> getFIDbyOrderNumber(String flightNumber) {
        String sql = "select *from flighttable where flight_number = ?";
        List<FlightTable> lists = JDBCTemplate.executeQueryList(sql,FlightTable.class,flightNumber);
        return lists;
    }

    @Override
    public List<FlightTable> getFidByFlightId(int id) {
        String sql = "select *from flighttable where id = ?";
        List<FlightTable> lists = JDBCTemplate.executeQueryList(sql,FlightTable.class,id);
        return lists;
    }

    @Override
    public void setFlightState() {
        String sql = "update flighttable" +
                " set state = ?" +
                "where take_off_time <  ?";
        Date date = new Date();
        System.out.println(date);
        JDBCTemplate.update(sql, StateOfFlight.IsFly,date);
    }


}
