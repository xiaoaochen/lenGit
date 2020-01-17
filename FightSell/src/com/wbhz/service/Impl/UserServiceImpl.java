package com.wbhz.service.Impl;

import com.wbhz.constants.StateOfTicket;
import com.wbhz.dao.Impl.FlightTableDaoImpl;
import com.wbhz.dao.Impl.RecordDaoImpl;
import com.wbhz.dao.Impl.UserDaoImpl;
import com.wbhz.entity.FlightTable;
import com.wbhz.entity.LinkUser;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;
import com.wbhz.service.UserService;
import com.wbhz.util.DataUtils;
import com.wbhz.util.ParseUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
   private UserDaoImpl userDao = new UserDaoImpl();
   private RecordDaoImpl recordDao = new RecordDaoImpl();

   private FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();

    @Override
    public boolean regiser(User user) {
        try {
            if (userDao.getUserByUserName(user.getUserName()) != null){
                System.out.println("用户名重复");
                return false;
            }
            if (userDao.getUserById(user.getUserId()) != null){
                System.out.println("身份证已被使用");
                return false;
            }
            userDao.insert(user);
            System.out.println("注册成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("注册失败");
        }
        return false;
    }

    @Override
    public boolean judgeIsInTable(String userName,String passWord) {
        User user = null;
        try {
            user = userDao.getUserByUserNameAndPassword(userName,passWord);
            if (user == null) {
//                System.out.println("当前用户不存在");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

//            if (user.getCount() < 3){
//                if (!user.getPassWord().equals(passWord)){
//                    user.setCount(user.getCount() + 1);
//                    userDao.update(user.getUserId(),user);
//                    if (user.getCount() <3){
//                        System.out.println("你好，密码输入错误，还有" +
//                                (3 - user.getCount()) + "次机会");
//                    }else if(user.getCount() == 3){
//                        System.out.println("账户已被锁定");
//                    }
//                }
//                else{
//                    user.setCount(0);
//                    userDao.update(user.getUserId(),user);
//                    return true;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;

        @Override
    public User getUserByUserName(String userName) {
        User user = null;
        try {
            user = userDao.getUserByUserName(userName);
//            if (user == null){
//                System.out.println("查询失败，查无此人");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int update(User user) {
        int result = 0;
        try {
            result = userDao.update(user.getUserId(),user);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("更新失败");
        }
        return result;
    }

    @Override
    public List<Record> queryFlightByUserId(String userId) {
        List<Record> records = null;
        try {
             records = recordDao.getRecordByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("没有任何购买机票的记录");
        }
        return records;
    }

    @Override
    public FlightTable queryNowFlightByUserId(String userId,String state) {
        FlightTable flightTable = null;
        Record record = null;
        record = recordDao.gerNowFlightByState(userId);
        try {
            record = recordDao.gerNowFlightByState(userId);
            flightTable = flightTableDao.getFlightbyflightNumber(record.getFlightNumber(),
                    DataUtils.DataIntoString(record.getTakeOffTime()));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("当前无未使用航班");
        }

        return flightTable;
    }

    @Override
    public Record queryNowRecordByUserId(String userId,String state) {
        Record record = null;
        record = recordDao.gerNowFlightByState(userId);
        return record;
    }

    @Override
    public List<FlightTable> queryCanPreFlight(String placeOne, String placeTwo, String maxTime) {
       List<FlightTable> flightTables = flightTableDao.getFidbyTimeAndPlace(placeOne,placeTwo,maxTime);
       return flightTables;
    }

    @Override
    public int returnTicket(String userId) {
        User user = null;
        Record record = null;
        FlightTable flightTable = null;
        try {
            user = userDao.getUserById(userId);
            record = queryNowRecordByUserId(userId, StateOfTicket.CANUSED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date date = DataUtils.getNowTime();
        System.out.println(date);
        System.out.println(record.getTakeOffTime());
        if (date.getTime() >= record.getTakeOffTime().getTime()){
            System.out.println("飞机已经起飞，不能退票");
            return 0;
        }
        System.out.println(record);
        System.out.println("最迟退票时间" + record.getReturnTicketTime().getTime());
        System.out.println(date.getTime());
        if (date.getTime() > record.getReturnTicketTime().getTime()){
            System.out.println("已经超过最迟退票时间，不能退票");
            return 0;
        }
        try {
            flightTable = flightTableDao.getFlightbyflightNumber(record.getFlightNumber(),
                    DataUtils.DataIntoString(record.getTakeOffTime()));
        } catch (SQLException e) {
            System.out.println(1);
            e.printStackTrace();
        }
        if (flightTable == null){
            System.out.println("没有此航班");
            return 0;
        }

        System.out.println(flightTable);
        flightTable.setTickets(flightTable.getTickets() + 1);
        try {
            flightTableDao.update(flightTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(record);
        try {
            recordDao.updateStateById(record.getOrderNumber(),StateOfTicket.HASRETURN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;


    }

    @Override
    public int byTickets(String userId,Record record) {
        User user = null;
        try {
            user = userDao.getUserById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("找不到此用户");
        }
        try {
            userDao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("插入错误");
        }
        try {
            recordDao.insret(record);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("插入错误");
        }


        return 1;
    }

    @Override
    public int changeTicket(String userId,Record record) {
        User user = null;
        try {
            user = userDao.getUserById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date date = DataUtils.getNowTime();
        if (date.getTime() > record.getReturnTicketTime().getTime()){
            System.out.println("过了最迟退票时间，不能退票");
            return 0;
        }
        recordDao.update(record);
        return 1;


    }

    @Override
    public int update(String userId, User user) throws SQLException {
        int result = userDao.update(userId,user);
        return result;
    }

    @Override
    public List showIsCanUsed(String takeOffTime,String fligtNumber){
        FlightTable flightTable = null;
        try {
            flightTable = flightTableDao.getFlightbyflightNumber(fligtNumber,takeOffTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(flightTable);
        List<Record> records = recordDao.queryByFlightCondition(fligtNumber,takeOffTime);
//        System.out.println(records);
//        System.out.println(fligtNumber);
//        System.out.println(takeOffTime);
        List<String> lists = new ArrayList<>();
//        int list[] = new int[flightTable.getTickets()];
        for (int i = 1; i <= flightTable.getAlltickets(); i++) {
            lists.add(ParseUtil.parseStringFromObj(i));
        }
        for (int i = 0; i < records.size(); i++) {
            lists.remove(ParseUtil.parseStringFromObj(records.get(i).getSeat()));
            System.out.println(records.get(i).getSeat());
        }
        return lists;
    }

    @Override
    public User getUserByUserLinkName(String userName) {
        User user = null;
        try {
            user = userDao.getLinkUserByUserName(userName);
//            if (user == null){
//                System.out.println("查询失败，查无此人");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean regiserNew(LinkUser user) {
        try {
            if (userDao.getLinkUserByUserName(user.getUserName()) != null){
                System.out.println("用户名重复");
                return false;
            }
            if (userDao.getLinkUserByUserName(user.getUserId()) != null){
                System.out.println("身份证已被使用");
                return false;
            }
            userDao.insretLink(user);
            System.out.println("注册成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册失败");
        }
        return false;
    }

}
