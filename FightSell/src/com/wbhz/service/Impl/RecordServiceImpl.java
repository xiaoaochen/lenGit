package com.wbhz.service.Impl;

import com.wbhz.dao.Impl.FlightTableDaoImpl;
import com.wbhz.dao.Impl.RecordDaoImpl;
import com.wbhz.entity.FlightTable;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;
import com.wbhz.service.RecordService;

import java.sql.SQLException;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    RecordDaoImpl recordDao = new RecordDaoImpl();
    FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();
    FlightTableServiceImpl flightTableService = new FlightTableServiceImpl();

    /**
     * 根据记录的实体类插入记录
     * @param record 记录的实体类
     * @return
     */
    @Override
    public int insert(Record record) {
        int result = 0;
        try {
            result = recordDao.insret(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据订单号获取购票信息
     * @param orderNumber 订单号
     * @param seat 座位号
     * @return
     */
    @Override
    public boolean getRecordByFidAndSeat(String orderNumber, int seat) {
        boolean record = false;
        FlightTable flightTable = flightTableService.getFlightTableByOrderNumber(orderNumber);
        try {
            record = recordDao.JudgeRecordByFlightNumberAndSeat(flightTable.getFlightNumber(),seat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    @Override
    public List<Record> seatIsUsedByflightId(int id) {

        return null;
    }

    @Override
    public Record getRecordById(String userId) {
        Record record = null;
        record = recordDao.queryByUserId(userId);
        return record;
    }

    @Override
    public int updateStateByOrderNumber(String orderNumber,String state) {
        int record = 0;
        try {
            record = recordDao.updateStateById(orderNumber,state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    @Override
    public List<Record> getRecordByFlightNumberAndTakeofftime(String flightNumber, String takeOffTime) {
        List<Record> records = null;
        records = recordDao.getRecordBycondtion(flightNumber,takeOffTime);
        return records;
    }

    @Override
    public Record getRecordByUserIdAndOrderNumber(String buyId, String orderNumber) {
        Record record = null;
        try {
            record = recordDao.queryByUserIdAndOrderNumber(buyId,orderNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    @Override
    public Record getRecordByseat(String flightNumber,int seat, String canUsed) {
        Record record = recordDao.getRecordByseat(flightNumber,seat,canUsed);
        return record;
    }

    @Override
    public int insret(Record record) throws SQLException {
        int result = recordDao.insret(record);
        return result;
    }

    @Override
    public Record queryByUserIdAndOrderNumber(String userId, String orderNumber) throws SQLException {
        Record record = recordDao.queryByUserIdAndOrderNumber(userId,orderNumber);
        return record;
    }

    @Override
    public int updateStateById(String orderNumber, String state) {
        int result = 0;
        try {
            result = recordDao.updateStateById(orderNumber,state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean isOneTicket(String flightNumber, String takeOffTime, String userId) {
        FlightTable flightTable = null;
        try {
             flightTable = flightTableDao.getFlightbyflightNumber(flightNumber,takeOffTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Record record = recordDao.queryByUserIdAndTakeOfftime(userId,takeOffTime,flightNumber);
        if (record == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int recordUpDate(Record record){
        int result = recordDao.update(record);
        return result;
    }


    @Override
    public List<Record> getListByBuyId(String buyId){
        List<Record> lists = recordDao.queryListByById(buyId);
        return lists;
    }

}


