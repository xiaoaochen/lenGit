package com.wbhz.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static com.wbhz.util.DataUtils.*;

public class testDateUtil {

    @Test
    public  void testgetDayOfMonth(){
        int numer = getDayOfMonth(2020,2);
        System.out.println(numer);
    }

    @Test
    public void testgetNowDataTime(){
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    public void testStringTurnIntoDate(){
        System.out.println(StringTurnIntoDate("2020-01-07 23:32:47"));
    }

//    @Test
//    public void testgetTwoHoursTime(){
//        System.out.println( getTwoHoursTime());
//    }
    @Test
    public void testdataIntoLong(){
        System.out.println(dataIntoLong(StringTurnIntoDate("2020-01-07 23:32:47")));
    }

    @Test
    public void testjudgeIsBeforeTwoHours(){
        System.out.println(judgeIsBeforeTwoHours("2020-01-08 8:32:47"));
    }

    @Test
    public void testTwoHours() throws ParseException {
        System.out.println(getBeforeTime("2020-01-08 8:32:47"));
    }

    @Test
    public void testY(){
        String startTime = "2020-01-22_00:00:00";
        startTime = startTime.replace("_"," ");
        System.out.println(DataUtils.StringTurnIntoDate(startTime));
    }
}
