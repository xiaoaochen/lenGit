package com.wbhz.controller;

import com.wbhz.constants.Constants;
import com.wbhz.constants.StateOfTicket;

import com.wbhz.entity.FlightTable;
import com.wbhz.entity.LinkUser;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;
import com.wbhz.service.Impl.FlightTableServiceImpl;
import com.wbhz.service.Impl.RecordServiceImpl;
import com.wbhz.service.Impl.UserServiceImpl;
import com.wbhz.util.CheckUtils;
import com.wbhz.util.DataUtils;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.wbhz.util.orderCodeFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class UserController {
    private UserServiceImpl userService = new UserServiceImpl();
    private FlightTableServiceImpl flightTableService = new FlightTableServiceImpl();
    private RecordServiceImpl recordService = new RecordServiceImpl();

    /**
     * 登录
     *
     * @throws SQLException
     */
    public User login() throws SQLException {

        System.out.println("-------------登录界面-------------");
        Scanner loginInput = new Scanner(System.in);
        User user = null;
        System.out.println("请输入用户名： ");
        String userName = loginInput.next();
        System.out.println("请输入密码： ");
        String password = loginInput.next();
        if (userService.judgeIsInTable(userName, password) && userService.getUserByUserLinkName(userName) == null) {
            user = userService.getUserByUserName(userName);
            if (user.getCount() <= 3) {
                System.out.println("登陆成功");
//                loginInput.close();
                return user;
            } else {
                System.out.println("账户被锁定，请联系管理员");
                System.out.println("是否继续登陆  (Yes / No)");
                String choice = loginInput.next();
                if (choice.equalsIgnoreCase("Yes")) {
                    User user1 = login();
                    return user1;
                } else {
                    System.out.println("成功退出");
                    return null;
                }
            }
        } else {
            if (userService.getUserByUserName(userName) != null && userService.getUserByUserName(userName).getCount() <= 3) {
                user = userService.getUserByUserName(userName);
//                System.out.println(user);
                if (!((user.getPassWord()).equals(password))) {
                    System.out.println("密码错误");
                    System.out.println("你好，密码输入错误，还有" + (3 - user.getCount()) + "次机会");
                    user.setCount(user.getCount() + 1);
                    System.out.println("是否继续登陆  (Yes / No)");
                    String choice = loginInput.next();
                    while (!choice.equals("Yes") && !choice.equals("No")){
                        System.out.println("请输入正确的数字：");
                        choice = loginInput.next();
                    }
                    if (choice.equalsIgnoreCase("Yes")) {
//                        System.out.println("你好，密码输入错误，还有" + (3 - user.getCount()) + "次机会");
                        System.out.println("请输入密码： ");
                        password = loginInput.next();
                        while (!password.equals(user.getPassWord())) {
                            user.setCount(user.getCount() + 1);
                            if (user.getCount() > 3)
                                break;
                            System.out.println("你好，密码输入错误，还有" + (3 - user.getCount() + 1) + "次机会");
                            System.out.println("请输入密码： ");
                            password = loginInput.next();

                        }
                        if (user.getCount() > 3) {
                            System.out.println("此账户已被锁定");
                            userService.update(user.getUserId(), user);
                            return null;
                        } else {
                            user.setCount(0);
                            userService.update(user.getUserId(), user);
                            System.out.println("登陆成功");
                            return user;
                        }
                    } else {
                        System.out.println("成功退出");
                        return null;
                    }

                }
            } else if (userService.getUserByUserName(userName) != null && userService.getUserByUserName(userName).getCount() >= 3) {
                System.out.println("账户已经被锁，请联系管理员");
                System.out.println("是否继续登陆  (Yes / No)");
                String choice = loginInput.next();
                if (choice.equalsIgnoreCase("Yes")) {
                    User user1 = login();
                    return user;

                } else {
                    System.out.println("成功退出");
                    return null;
                }

            } else {

                System.out.println("用户或者密码错误");
                System.out.println("是否继续登陆  (Yes / No)");

                String choice = loginInput.next();
                while (!choice.equals("Yes") && !choice.equals("No")){
                    System.out.println("请输入正确的数字：");
                    choice = loginInput.next();
                }
                if (choice.equalsIgnoreCase("Yes")) {
                    User user1 = login();
                    return user1;
                } else {
                    System.out.println("成功退出");
                    return null;
                }
            }
        }
        return user;

    }

    /**
     * 注册
     */
    public User register() {
        System.out.println("-------------用户注册-------------");
        Scanner registerInput = new Scanner(System.in);
        User user = new User();
        System.out.println("请输入用户名： " + Constants.USERNAME_FORMAT_ERROR);
        String userName = registerInput.next();
        while (!CheckUtils.checkUsername(userName) || userService.getUserByUserName(userName) != null) {
            System.out.println("用户名不符合规范/用户名已经被占用,请重新输入:");
            userName = registerInput.next();
        }
        System.out.println("请输入密码： " + Constants.PASSWORD_FORMAT_ERROR);
        String passWord = registerInput.next();
        while (!CheckUtils.checkPassword(passWord)) {
            System.out.println("密码输入不规范,请重新输入:");
            passWord = registerInput.next();
        }
        System.out.println("请输入身份证：" + Constants.ID_CARD_FORMAT_ERROR);
        String userId = registerInput.next();
        while (!CheckUtils.checkIdCardNumber(userId)) {
            System.out.println("身份证输入不规范,请重新输入:");
            userId = registerInput.next();
        }
        System.out.println("请输入真实姓名: " + Constants.ACTUAL_NAME_FORMAT_ERROR);
        String name = registerInput.next();
        while (!CheckUtils.checkActualName(name)) {
            System.out.println("姓名格式不规范,请重新输入:");
            name = registerInput.next();
        }
        System.out.println("请输入手机号：" + Constants.PHONE_NUMBER_FORMAT_ERROR);
        String phoneNumber = registerInput.next();
        while (!CheckUtils.checkPhoneNumber(phoneNumber)) {
            System.out.println("手机号输入不规范,请重新输入:");
            phoneNumber = registerInput.next();
        }
        System.out.println("请输入性别  " + "男/女");
        String sex = registerInput.next();
        while (!sex.equals("男") && !sex.equals("女")) {
            System.out.println("输入不规范,请重新输入:");
            sex = registerInput.next();
        }
        System.out.println("请输入邮箱 " + "格式：XXXXXX@XXX.XXX");
        String email = registerInput.next();
        while (!CheckUtils.checkEmail(email)) {
            System.out.println("输入不规范,请重新输入:");
            email = registerInput.next();
        }
        System.out.println("请输入地址 ");
        String address = registerInput.next();
        user.setUserName(userName);
        user.setEmail(email);
        user.setAddress(address);
        user.setSex(sex);
        user.setPassWord(passWord);
        user.setPhoneNumber(phoneNumber);
        user.setName(name);
        user.setUserId(userId);
        if (userService.regiser(user)) {
            System.out.println();
            return user;
        } else {
            System.out.println("注册失败,是否继续" + "Yes/No");
            String choice = registerInput.next();
            if ("Yes".equalsIgnoreCase(choice)) {
                User user1 = register();
                return user1;
            } else {
//                registerInput.close();
                System.out.println("退出成功");
                return null;
            }
        }
    }

    /**
     * 查询
     *
     * @return
     */
    public int query() {
        Scanner queryInput = new Scanner(System.in);
        System.out.println("请输入出发地:  ");
        String startplace = queryInput.next();
        System.out.println("请输入目的地： ");
        String endPlace = queryInput.next();
        System.out.println("输入最早出发时间： ");
        String minTime = queryInput.next();
        while (!CheckUtils.isFormatTime(minTime)) {
            System.out.println("输入非法，按制定要求输入信息(yyyy-MM-dd_HH:mm:ss),请重新输入: ");
            minTime = queryInput.next();
        }
        System.out.println("输入最晚出发时间： ");
        String maxTime = queryInput.next();
        while (!CheckUtils.isFormatTime(maxTime)) {
            System.out.println("输入非法，按制定要求输入信息(yyyy-MM-dd_HH:mm:ss),请重新输入: ");
            maxTime = queryInput.next();
        }
        List<FlightTable> lists = null;
        lists = flightTableService.getFidBy(minTime, maxTime, startplace, endPlace);
        System.out.println("信息如下----------------------------------------------------------");
        if (lists.size() > 0) {
//            System.out.println("[编号]\t航班号\t\t\t\t\t起飞时间\t\t\t\t飞行时间\t    \t出发地   目的地\t  剩余座位\t 票价");
            for (FlightTable f : lists) {
                System.out.print("[编号] " + f.getId() + "\t   ");
                System.out.print("[航班号] " + f.getFlightNumber() + "\t\t    ");
                System.out.print("[起飞时间 " + DataUtils.DataIntoString(f.getTakeOffTime()) + "\t\t");
                System.out.print("[飞行时间] " + f.getFlyingTime() + "\t\t");
                System.out.print("[出发地] " + f.getStartPlace() + "\t   ");
                System.out.print("[目的地] " + f.getEndPlace() + "\t    ");
                System.out.print("[剩余座位] " + f.getTickets() + "\t");
                System.out.println("[票价] " + f.getPrice());
                System.out.println();
            }
        } else {
            System.out.println("抱歉，暂无该航线信息，详情请联系管理员!");
        }
        return lists == null ? 0 : lists.size();

    }

    public int update(User user) {
        Scanner infoInput = new Scanner(System.in);
        System.out.println("请输入用户名 :");
        String userName = infoInput.next();
        System.out.println("请输入性别");
        String sex = infoInput.next();

        while (!(sex.equals("男") || sex.equals("女"))) {
            System.out.print("性别输入不合法，请重新输入！");
            sex = infoInput.next();
        }
        System.out.print("请输入手机号： ");
        String phoneNumber = infoInput.next();
        while (!CheckUtils.checkPhoneNumber(phoneNumber)) {
            System.out.println("手机号输入不规范,请重新输入:");
            phoneNumber = infoInput.next();
        }
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setSex(sex);
        int result = userService.update(user);
        if (result > 0) {
            System.out.println("更新成功！");
            System.out.println("用户名： " + user.getUserName() + ",性别：" + user.getSex() + ",手机号： " + user.getPhoneNumber());
            return 1;
        } else {
            System.out.println("更新失败！");
            return 0;
        }

    }

    public int updatePassword(User user) {
        System.out.println("请输入旧密码： ");
        Scanner passWordInput = new Scanner(System.in);
        String password = passWordInput.next();
        while (!CheckUtils.checkPassword(password)) {
            System.out.println("密码格式不正确，请重新输入");
            password = passWordInput.next();
        }
        if (!(user.getPassWord()).equals(password)) {
            System.out.println("密码输入错误");
            return 0;
        }
        System.out.println("新密码： ");
        String newPassWord = passWordInput.next();
        System.out.println("确认新密码：");
        String conPassword = passWordInput.next();
        if (!conPassword.equals(newPassWord)) {
            System.out.println("两次输入的密码不一致");
            return 0;
        }
        user.setPassWord(conPassword);
        int result = userService.update(user);
        if (result > 0) {
            System.out.println("修改密码成功！");
            return 1;
        } else {
            System.out.println("修改密码失败");
            return 0;
        }

    }

    public boolean order(User user) throws ParseException {
        Record newecord = new Record();
        LinkUser LinkUser = new LinkUser();
        if (query() > 0) {
            System.out.println("请输入飞机编号");
            Scanner orderInput = new Scanner(System.in);

            int choice = orderInput.nextInt();
            FlightTable flightTable = flightTableService.getFlightById(choice);
            if (null == flightTable|| flightTable.getTakeOffTime().getTime() < DataUtils.getNowTime().getTime()) {

                if (flightTable.getTakeOffTime().getTime() < DataUtils.getNowTime().getTime()){
                    System.out.println("抱歉，目前没有编号为：" + choice + "的航班，航班已经起飞，请重新预定");
                }else {
                    System.out.println("抱歉，目前没有编号为：" + choice + "的航班，请重新预定");
                }
            }
            else{

                List<Record> lists = new ArrayList<Record>();
                System.out.println("请输入身份证： ");
                String identity = orderInput.next();
                while (!CheckUtils.checkIdCardNumber(identity)) {
                    System.out.println("身份证格式不正确，请重新输入: ");
                    identity = orderInput.next();
                }

                System.out.println("请输入手机号: ");
                String phoneNumber = orderInput.next();
                while (!CheckUtils.checkPhoneNumber(phoneNumber)) {
                    System.out.println("手机号输入不规范,请重新输入:");
                    phoneNumber = orderInput.next();
                }
                System.out.println("请输入真实姓名： ");
                String name = orderInput.next();
                while (!CheckUtils.checkActualName(name)) {
                    System.out.println("姓名格式不规范,请重新输入:");
                    name = orderInput.next();
                }
                List<String> integers = userService.showIsCanUsed(DataUtils.DataIntoString(flightTable.getTakeOffTime()), flightTable.getFlightNumber());
                printSeats(integers);
                System.out.println("请输入座位号");
                int seat = orderInput.nextInt();
                Record record = recordService.getRecordByseat(flightTable.getFlightNumber(), seat, StateOfTicket.CANUSED);

//                System.out.println(record);
                if (record == null) {
                    System.out.println(flightTable.getAlltickets());
                    while (seat > flightTable.getAlltickets()) {
                        System.out.println("选座错误，请选择其他座位");
                        seat = orderInput.nextInt();
                    }
                } else {
                    System.out.println(record.getSeat());
                    while ((recordService.getRecordByseat(flightTable.getFlightNumber(), seat, StateOfTicket.CANUSED)) != null
                            || seat > flightTable.getAlltickets()) {
                        System.out.println("选座错误，请选择其他座位");
                        seat = orderInput.nextInt();
                    }
                }

                if (!user.getUserId().equals(identity)) {
                    System.out.println("是否为代买票: " + "Yes/No");
                    String choice1 = orderInput.next();
                    if ("No".equalsIgnoreCase(choice1)) {
                        System.out.println("请重新购票，检查相关数据是否有误");
                        return false;
                    }
                    newecord.setName(name);
                    newecord.setUserName(name);
                    newecord.setUserId(identity);
                    newecord.setUserName(user.getUserName());
                    newecord.setOrderNumber(orderCodeFactory.getRandomNickname());
                    newecord.setStartPlace(flightTable.getStartPlace());
                    newecord.setEndPlace(flightTable.getEndPlace());
                    newecord.setPrice(flightTable.getPrice());
                    newecord.setBuyTime(DataUtils.getNowTime());
                    newecord.setReturnTicketTime(DataUtils.getBeforeTime(DataUtils.DataIntoString(flightTable.getTakeOffTime())));
                    newecord.setSeat(seat);
                    newecord.setFlightNumber(flightTable.getFlightNumber());
                    newecord.setTakeOffTime(flightTable.getTakeOffTime());
                    newecord.setUserName(user.getUserName());
                    newecord.setBuyId(user.getUserId());
                    flightTable.setTickets(flightTable.getTickets() - 1);
                    LinkUser.setUserName(identity);
                    LinkUser.setUserId(identity);
                    LinkUser.setName(name);
                    LinkUser.setBuyName(user.getName());
                    LinkUser.setPhoneNumber(phoneNumber);
                    userService.regiserNew(LinkUser);
                    int result = 0;
                    try {
                        result = recordService.insret(newecord);
                        System.out.println(result);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (result > 0) {
                        System.out.println("订单如下：");
                        flightTableService.update(flightTable);
                        iterRorList(newecord);
                        System.out.println();
                        return true;
                    } else
                        return false;
                } else if (recordService.isOneTicket(flightTable.getFlightNumber(), DataUtils.DataIntoString(flightTable.getTakeOffTime()), user.getUserId())) {
                    newecord.setName(name);
                    newecord.setUserId(user.getUserId());
                    newecord.setOrderNumber(orderCodeFactory.getRandomNickname());
                    newecord.setStartPlace(flightTable.getStartPlace());
                    newecord.setEndPlace(flightTable.getEndPlace());
                    newecord.setPrice(flightTable.getPrice());
                    newecord.setBuyTime(DataUtils.getNowTime());
                    newecord.setReturnTicketTime(DataUtils.getBeforeTime(DataUtils.DataIntoString(flightTable.getTakeOffTime())));
                    newecord.setSeat(seat);
                    newecord.setFlightNumber(flightTable.getFlightNumber());
                    newecord.setTakeOffTime(flightTable.getTakeOffTime());
                    newecord.setUserName(user.getUserName());
                    newecord.setBuyId(user.getUserId());
                    flightTable.setTickets(flightTable.getTickets() - 1);
                    int result = 0;
                    try {
                        result = recordService.insret(newecord);
                        System.out.println(result);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (result > 0) {
                        System.out.println("订单如下：");
                        flightTableService.update(flightTable);
                        iterRorList(record);
                        return true;
                    } else
                        return false;

                } else {
                    System.out.println("同一个人不能在同一架航班上有两个位置");
                    return false;
                }
            }
        }
        return false;
    }

    public int refund(User user) {
        System.out.println("目前可以使用的订单：");
        List<Record> records = recordService.getListByBuyId(user.getUserId());
        iterRorList(records);
        if (records != null) {
            Scanner reInput = new Scanner(System.in);
            System.out.println("请输入订单号： ");
            String orderNumber = reInput.next();
            Record record = null;
            try {
                record = recordService.queryByUserIdAndOrderNumber(user.getUserId(), orderNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FlightTable flightTable = null;
            if (record == null) {
                System.out.println("没有购买记录");
                return 0;
            }
            Date date = DataUtils.getNowTime();
//            System.out.println(date);
//            System.out.println(record.getTakeOffTime());
            if (date.getTime() >= record.getTakeOffTime().getTime()) {
                System.out.println("飞机已经起飞，不能退票");
                return 0;
            }
//            System.out.println(record);
//            System.out.println("最迟退票时间 " + record.getReturnTicketTime());
//            System.out.println(date.getTime());
            if (date.getTime() > record.getReturnTicketTime().getTime()) {
                System.out.println("已经超过最迟退票时间，不能退票");
            }
            flightTable = flightTableService.getFlightTableByOrderNumber(record.getOrderNumber());
            if (flightTable == null) {
                System.out.println("没有此航班");
                return 0;
            }
//            System.out.println(flightTable);
            flightTable.setTickets(flightTable.getTickets() + 1);
            flightTableService.update(flightTable);
//            System.out.println(record);
            recordService.updateStateById(record.getOrderNumber(), StateOfTicket.HASRETURN);
            System.out.println("退票成功");
            return 1;
        } else
            return 0;
    }

    public void updateInfo(User user) {
        System.out.println("***********修改信息***********");
        System.out.println("1.修改个人信息");
        System.out.println("2.修 改 密 码");
        System.out.println("请选择： ");
        Scanner updateInput = new Scanner(System.in);
        int choice = updateInput.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.println("输入有误，请选择正确的数字");
            choice = updateInput.nextInt();
        }
        switch (choice) {
            case 1:
                update(user);
                break;
            case 2:
                updatePassword(user);
                break;
            default:
                break;
        }
    }

    /**
     * 改签
     *
     * @param user
     * @return
     */
    public int change(User user) {
        System.out.println("目前可以使用的订单：");
        List<Record> records = recordService.getListByBuyId(user.getUserId());
//        System.out.println(records);
        if (records.size() != 0) {
            iterRorList(records);
            System.out.println("请输入订单号");
            Scanner ChangeInput = new Scanner(System.in);
            String orderNumber = ChangeInput.next();
            while (!CheckUtils.checkOrderNumber(orderNumber)) {
                System.out.println("请输入正确订单号：" + Constants.ORDER_NUMBER_ERROR);
                orderNumber = ChangeInput.next();
            }
            System.out.println(user.getName() + "的订单如下： ");
            boolean result = false;
            Record record = recordService.getRecordByUserIdAndOrderNumber(user.getUserId(), orderNumber);
            if (record == null) {
                System.out.println("当前没有航班信息");
                return 0;
            } else {
                iterRorList(record);
                System.out.println();
                FlightTable flightTable = flightTableService.getFlightTableByOrderNumber(record.getOrderNumber());
                Date date = new Date();
                if (date.getTime() > record.getReturnTicketTime().getTime()) {
                    System.out.println("飞机起飞两小时前，无法改签");
                    return 0;
                } else {
                    try {
                        System.out.println(record.getOrderNumber());
                        recordService.updateStateById(record.getOrderNumber(), StateOfTicket.HASHCHAGE);
                        result = order(user);
                        FlightTable flightTable1 = flightTableService.getFlightTableByOrderNumber(record.getOrderNumber());
                        flightTable.setTickets(flightTable.getTickets() - 1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (result) {
                        System.out.println("修改成功");

                        return 1;
                    } else {
                        System.out.println("修改失败");
                        return 0;
                    }
                }

            }
        } else {
            return 0;
        }
    }

    private void printSeats(List<String> list) {
        if (list.size() > 0) {
            System.out.println("目前可选座位");
            for (int i = 0; i < list.size(); i++) {
                System.out.print("[" + list.get(i) + "]" + "   ");
                if (i % 5 == 0 && i != 0) {
                    System.out.print(" ");
                }
                if (i % 10 == 0 && i != 0) {
                    System.out.print(" ");
                }
                if (i % 15 == 0 && i != 0) {
                    System.out.println();
                }
            }
        } else {
            System.out.println("已经满了");
        }
    }

    private void iterRorList(Record record) {
        User user = new User();
        if (record == null) {
            System.out.println("当前没有订单信息");
        } else {
//            System.out.println("[订单号]\t\t\t[省份证]\t\t\t [姓名]\t[航班号]\t [出发地]\t[目的地]\t\t\t\t[出发时间]");
            System.out.print("[订单号] " + record.getOrderNumber() + "\t");
            System.out.print("[省份证] " + record.getUserId() + "\t");
            System.out.print("[姓名] " + record.getName() + "\t");
            System.out.print("[航班号] " + record.getFlightNumber() + "\t");
            System.out.print("[出发地]" + record.getStartPlace() + "\t");
            System.out.print("[目的地]" + record.getEndPlace() + "\t");
            System.out.print("[出发时间]" + record.getTakeOffTime() + "\t");

        }
    }

    private void iterRorList(List<Record> records) {
        User user = new User();
        if (records.size() == 0) {
            System.out.println("当前没有订单信息");
        } else {
//            System.out.println("订单号\t省份证\t\t\t手机号\t\t姓名\t航班号\t出发地\t目的地\t\t出发时间");
            for (Record r : records) {
                System.out.print("[订单号]: " + r.getOrderNumber());
                System.out.print("[身份证] : " + r.getUserId());
                System.out.print("[姓名]: " + r.getName());
                System.out.print("[航班号]: " + r.getFlightNumber());
                System.out.print("[出发地]: " + r.getStartPlace());
                System.out.print("[目的地]: " + r.getEndPlace());
                System.out.print("[出发时间]: " + r.getTakeOffTime());
                System.out.println();
            }
        }
    }

    public LinkUser updateLink(LinkUser user) {
        System.out.println("请输入旧密码： ");
        Scanner passWordInput = new Scanner(System.in);
        String password = passWordInput.next();
        while (!CheckUtils.checkPassword(password)) {
            System.out.println("密码格式不正确，请重新输入");
            password = passWordInput.next();
        }
        if ((user.getPassWord()).equals(password)) {
            System.out.println("密码输入错误");
            return null;
        }
        System.out.println("新密码： ");
        String newPassWord = passWordInput.next();
        System.out.println("确认新密码：");
        String conPassword = passWordInput.next();
        if (!conPassword.equals(newPassWord)) {
            System.out.println("两次输入的密码不一致");
            return null;
        }
        System.out.println("请输入性别  " + "男/女");
        String sex = passWordInput.next();
        while (!sex.equals("男") && !sex.equals("女")) {
            System.out.println("输入不规范,请重新输入:");
            sex = passWordInput.next();
        }
        System.out.println("请输入邮箱 " + "格式：XXXXXX@XXX.XXX");
        String email = passWordInput.next();
        while (!CheckUtils.checkEmail(email)) {
            System.out.println("输入不规范,请重新输入:");
            email = passWordInput.next();
        }
        System.out.println("请输入用户名： " + Constants.USERNAME_FORMAT_ERROR);
        String userName = passWordInput.next();
        while (!CheckUtils.checkUsername(userName) || userService.getUserByUserName(userName) != null) {
            System.out.println("用户名不符合规范/用户名已经被占用,请重新输入:");
            userName = passWordInput.next();
        }
        System.out.println("请输入地址 ");
        String address = passWordInput.next();
        user.setEmail(email);
        user.setAddress(address);
        user.setSex(sex);
        user.setUserName(userName);
        user.setPassWord(conPassword);
        Record record = recordService.getRecordById(user.getUserId());
        record.setUserName(userName);
        recordService.recordUpDate(record);
        if (userService.regiserNew(user)) {
            System.out.println();
            return user;
        } else {
            System.out.println("更新失败,是否继续" + "Yes/No");
            String choice = passWordInput.next();
            if ("Yes".equalsIgnoreCase(choice)) {
                LinkUser user1 = updateLink(user);
                return user1;
            } else {
                passWordInput.close();
                System.out.println("退出成功");
                return null;
            }
        }


    }


    public static void main(String[] args) throws ParseException {
        UserController userController = new UserController();
        UserServiceImpl userService = new UserServiceImpl();
        Record r = new Record();
        r.setOrderNumber("1233311312\t");
        r.setUserId("321312312321\t");
        r.setName("cccc");
        r.setFlightNumber("3123123\t");
        r.setStartPlace("e244324\t");
        r.setEndPlace("312312312\t");
        r.setTakeOffTime(DataUtils.StringTurnIntoDate("2020-01-13 19:23:41"));
        userController.iterRorList(r);


        User user = userService.getUserByUserName("xiaoaoao");
    }
}