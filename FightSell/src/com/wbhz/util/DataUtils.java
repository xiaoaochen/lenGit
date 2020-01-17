package com.wbhz.util;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类主要是为了数据库中的datatime类型
 */
public class DataUtils {
    static Calendar calendar = Calendar.getInstance();

    /**
     * 获得一个月有多少天
     * @param year
     * @param mouth
     * @return
     */
    public static Integer getDayOfMonth(Integer year,Integer mouth){
        calendar.set(year,mouth,0);
        return calendar.getActualMaximum(calendar.DATE);
    }

    /**
     * 获取当前时间的DataTime格式
     * @return
     */
    public static String getNowDataTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(date);
        return dateStr;
    }


//    public static Timestamp getTwoHoursTime(){
//        Date date = new Date();
//        long currentTime = date.getTime();
//        currentTime = currentTime - 120 * 60 * 1000;
//
//
//    }

    /**
     * 将dataTime转为Data格式
     * @param datatime 数据库中的dataTime类型
     * @return
     */
    public static Date StringTurnIntoDate(String datatime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null; //初始化date

        try {

            date = sdf.parse(datatime); //Mon Jan 14 00:00:00 CST 2013

        } catch (ParseException e) {

            e.printStackTrace();

        }
        return date;
    }

    /**
     * 将获取的Data类转为String类型
     */
    public static Long dataIntoLong(Date date){
        return date.getTime();
    }

    /**
     * 判断此时时间是否在固定时间的两个小时之间
     * @param datatime
     * @return
     */
    public static boolean judgeIsBeforeTwoHours(String datatime){
        Date data = StringTurnIntoDate(datatime);
        long stringData = data.getTime();
        Date nowData = new Date();
        long nowStringTime = nowData.getTime();
        if ((nowStringTime - 120 * 60 * 1000) < stringData){
            return false;
        }
        else
            return true;
    }

    /**
     * 将Date格式转为String格式
     * @param date
     * @return
     */
    public static String DataIntoString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(date);
        return dateStr;
    }

    public static Date getBeforeTime(String time) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(StringTurnIntoDate(time));
        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date = format.parse(dateStr);
        format.setTimeZone(TimeZone.getTimeZone("GMT-2"));
        dateStr = format.format(date);
        return StringTurnIntoDate(dateStr);
    }

    public static Date getNowTime(){
        return new Date();
    }

    public static Date returnTime(String time){
        String newTime = time.replace("_"," ");
        return StringTurnIntoDate(newTime);
    }

}
