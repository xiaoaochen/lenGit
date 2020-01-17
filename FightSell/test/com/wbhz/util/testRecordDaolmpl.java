package com.wbhz.util;

import com.wbhz.constants.StateOfTicket;
import com.wbhz.dao.Impl.RecordDaoImpl;
import com.wbhz.entity.Record;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class testRecordDaolmpl {
    RecordDaoImpl recordDao = new RecordDaoImpl();
    Record record = new Record();
    @Test
    public void testInsert() throws ParseException {
        record.setName("hao");
        record.setUserId("12234");
        record.setUserName("xiaoao");
        record.setBuyTime(DataUtils.StringTurnIntoDate("2020-01-07 22:32:47"));
        record.setEndPlace("123");
        record.setReturnTicketTime(DataUtils.getBeforeTime("2020-01-07 22:32:47"));
        record.setFlightNumber("as123");
        record.setTakeOffTime(DataUtils.StringTurnIntoDate("2020-01-07 22:32:47"));
        record.setOrderNumber("qw");
        record.setPrice(12.0);
        record.setStartPlace("2234");
        record.setSeat(12);
        record.setCanUsed(StateOfTicket.HASRETURN);
        try {
            recordDao.insret(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testQueryAll() throws SQLException {
        List<Record> records = null;
        records = recordDao.queryAll();
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
        }
    }

    @Test
    public void testQueryInfo(){
        List<Record> records = null;
        try {
            records = recordDao.queryAllByInformation("2234","123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
        }
    }

    @Test
    public void testQueryByUserId(){
        Record record = null;
        record = recordDao.queryByUserId("12234");
        System.out.println(record);
    }

    @Test
    public void testJudgeRecordByFlightNumberAndSeat() throws SQLException {
         boolean result = false;
         result = recordDao.JudgeRecordByFlightNumberAndSeat("as123",0);
         System.out.println(result);
    }

    @Test
    public void testChangeState() throws SQLException {
        Record record = null;
        record = recordDao.queryByUserId("12234");
        recordDao.updateStateById(record.getOrderNumber(),StateOfTicket.HASHCHAGE);
    }

}
