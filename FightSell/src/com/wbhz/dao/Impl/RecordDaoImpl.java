package com.wbhz.dao.Impl;

import com.sun.jmx.snmp.SnmpNull;
import com.wbhz.constants.StateOfTicket;
import com.wbhz.dao.RecordDao;
import com.wbhz.entity.FlightTable;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;
import com.wbhz.util.JDBCTemplate;
import jdk.nashorn.internal.scripts.JD;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    @Override
    public int insret(Record record) throws SQLException {
        int result = 0;
        FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();
        String sql = "insert into record " +
                "(name," +                    //用户姓名
                "user_id," +                  //用户身份证号
                "order_number," +             //订单编号
                "start_place," +              //出发地点
                "end_place," +                //目的地
                "price," +                    //票价价格
                "buy_time," +                 //购买时间
                "take_off_time," +            //出发时间
                "return_ticket_time," +       //最迟退票时间
                "user_name," +
                "flight_number," +
                "seat," +
                "buy_id" +
                ")values " +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        result =JDBCTemplate.insert(sql,
                record.getName(),
                record.getUserId(),
                record.getOrderNumber(),
                record.getStartPlace(),
                record.getEndPlace(),
                record.getPrice(),
                record.getBuyTime(),
                record.getTakeOffTime(),
                record.getReturnTicketTime(),
                record.getUserName(),
                record.getFlightNumber(),
                record.getSeat(),
                record.getBuyId());
        return result;

    }

    @Override
    public List<Record> queryAll() throws SQLException {
        List<Record> records = null;
        String sql = "select *from Record;";
        records = JDBCTemplate.executeQueryList(sql,Record.class,null);
        return records;

    }

    @Override
    public List<Record> queryAllByInformation(String startPlace, String endPlace) throws SQLException {
       List<Record> records = null;
       String sql = "select *from record where start_place = ? and end_place = ?";
       records = JDBCTemplate.executeQueryList(sql,Record.class,startPlace,endPlace);
       return records;

    }

    @Override
    public Record queryByUserIdAndOrderNumber(String buyId,String orderNumber) throws SQLException {
        Record record = null;
        String sql = "select *from record where buy_id = ? and order_number = ?";
        record = JDBCTemplate.executeQuery(sql,Record.class,buyId,orderNumber);
        return record;
    }

    @Override
    public List<Record> queryAllByFlightNumber(String FlightNumber) throws SQLException {
        List<Record> records = null;
        String sql = "select *from record where flight_number = ?";
        records = JDBCTemplate.executeQueryList(sql,Record.class,FlightNumber);
        return records;
    }

    @Override
    public Boolean JudgeRecordByFlightNumberAndSeat(String FlightNumber, int seat) throws SQLException {
       Record record = null;
       String sql = "select *from record where flight_number = ? and seat = ?";
       record = JDBCTemplate.executeQuery(sql,Record.class,FlightNumber,seat);
       if (record != null){
           return false;
       }
       return true;
    }

    @Override
    public List<Record> getUnusedSeatsByFlightNumber(String FlightNumber) throws SQLException {
        return null;
    }


    @Override
    public List<Record> getRecordByUserId(String id) throws SQLException {
        List<Record> records = null;
        String sql = "select *from record where user_id = ?";
        records = JDBCTemplate.executeQueryList(sql,Record.class,id);
        return records;
    }



    @Override
    public int updateStateById(String orderNumber,String state) throws SQLException {
        String sql = "update record set can_used = ? where order_number = ?";
        int result = 0;
        result = JDBCTemplate.update(sql,state,orderNumber);
        return result;
    }

    @Override
    public Record getFlightTableByOrderNumber(String orderNumber) throws SQLException {
        String sql = "select *from record where order_number = ?";
        Record record = JDBCTemplate.executeQuery(sql,Record.class,orderNumber);
        return record;
    }

    @Override
    public Record gerNowFlightByState(String userId) {
        Record record = null;
        String sql = "select *from record where user_id = ? and can_used = ?";
        record = JDBCTemplate.executeQuery(sql,Record.class,userId, StateOfTicket.CANUSED);
        return record;
    }

    @Override
    public int update(Record record) {
        int result = 0;
        String sql = "update record set " +
                "name = ?," +
                "user_id = ?," +
                "order_name = ?," +
                "start_place = ?," +
                "end_place = ?," +
                "price = ?," +
                "buy_time = ?," +
                "take_off_time = ?," +
                "return_ticket_time = ?," +
                "user_name = ?," +
                "flight_number = ?," +
                "seat = ?";
        result = JDBCTemplate.update(sql,
                record.getName(),
                record.getUserId(),
                record.getOrderNumber(),
                record.getStartPlace(),
                record.getEndPlace(),
                record.getPrice(),
                record.getBuyTime(),
                record.getTakeOffTime(),
                record.getReturnTicketTime(),
                record.getUserName(),
                record.getFlightNumber()
                );
        return result;
    }

    @Override
    public Record getRecordByseat(String flightNumber,int seat, String canUsed) {
        String sql = "select *from record where seat = ? and can_used  = ? and  flight_number = ?";
        Record record = null;
        record = JDBCTemplate.executeQuery(sql,Record.class,seat,canUsed,flightNumber);
        return record;
    }

    @Override
    public List<Record> getRecordBycondtion(String flighyNumber, String takeOffTime) {
        String sql = "select *from record where flight_number = ? and take_off_time = ?";
        List<Record> lists = null;
        lists = JDBCTemplate.executeQueryList(sql,Record.class,flighyNumber,takeOffTime);
        return lists;
    }

    @Override
    public Record queryByUserId(String userId) {
        String sql = "select *from record where user_Id = ?";
        Record record = JDBCTemplate.executeQuery(sql,Record.class,userId);
        return record;
    }

    @Override
    public Record queryByUserIdAndTakeOfftime(String userId, String takeOffTime, String flightNumber) {
        String sql = "select *from record where flight_number = ? and take_off_time = ? and user_id = ? and can_used = ?";
        Record record = JDBCTemplate.executeQuery(sql, Record.class, flightNumber, takeOffTime, userId, StateOfTicket.CANUSED);
        return record;
    }

    @Override
    public List<Record> queryListByById(String buyId) {
        List<Record> records = null;
        String sql  = "select *from record where buy_id = ? and can_used = ?";
        records = JDBCTemplate.executeQueryList(sql,Record.class,buyId,StateOfTicket.CANUSED);
        return records;
    }


    public List<Record> queryByFlightCondition(String flghtNumber,String takeOfftime){
        String sql = "select *from record where flight_number = ? and take_off_time = ? and can_used = ?";
        List<Record> records = JDBCTemplate.executeQueryList(sql,Record.class,flghtNumber,takeOfftime,StateOfTicket.CANUSED);
        return records;
    }
}
//    public int update(String userId, User user) throws SQLException {
//        String sql = "update user set " +
//                "user_id = ?," +
//                "name = ?," +
//                "user_name = ?," +
//                "pass_word = ?," +
//                "sex = ?," +
//                "phone_number = ?," +
//                "email = ?," +
//                "address = ?" +
//                "where user_id = ?";
