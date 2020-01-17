package com.wbhz.controller;


import com.wbhz.entity.Adminster;
import com.wbhz.entity.FlightTable;
import com.wbhz.entity.Record;
import com.wbhz.entity.User;
import com.wbhz.service.Impl.AdminsterServiceImpl;
import com.wbhz.service.Impl.FlightTableServiceImpl;
import com.wbhz.service.Impl.RecordServiceImpl;
import com.wbhz.service.Impl.UserServiceImpl;
import com.wbhz.util.CheckUtils;
import com.wbhz.util.DataUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdminsterController {
    private AdminsterServiceImpl adminsterService = new AdminsterServiceImpl();
    private RecordServiceImpl recordService = new RecordServiceImpl();
    private FlightTableServiceImpl flightTableService = new FlightTableServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();

    public int login(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入登陆名");
        String userName = input.next();
        System.out.println("请输入密码");
        String passWord = input.next();
        Adminster adminster = adminsterService.login(userName,passWord);
        if (adminster != null){
            System.out.println("登陆成功，当前用户为： 【" + adminster.getUserName() + "】");
            return 1;
        }else {
            System.out.println("登陆名或密码出错");
            System.out.println("是否继续登陆  Yes or No");
            String choice = input.next();
            if (choice.equals("Yes")){
                login();
                return 1;
            }
            else{
                return 0;
            }
        }
//        return 0;

    }

    public List<FlightTable> quryFlightByCondiition(){

        Scanner condition = new Scanner(System.in);
        System.out.println("请输入出发地：");
        String startPlace = condition.next();
        System.out.println("请输入目的地： ");
        String endPlace = condition.next();
        System.out.println("请输入最早飞行时间:");
        String startTime = condition.next();
        while( !CheckUtils.isFormatTime(startTime)){
            System.out.println("输入的时间不符合规范，规范(yyyy-MM-dd_HH:mm：ss) / 时间不对");
            startTime = condition.next();
        }
        startTime = startTime.replace("_"," ");
        System.out.println("最迟飞行时间： ");
        String endTime = condition.next();
        while (!CheckUtils.isFormatTime(endTime)){
            System.out.println("输入的时间不符合规范，规范(yyyy-MM-dd_HH:mm：ss) / 时间不对");
            endTime = condition.next();
        }
        endTime = endTime.replace("_"," ");
        List<FlightTable> flightTables = null;
        flightTables = flightTableService.getFidBy(startTime,endTime,startPlace,endPlace);
        iteraorList(flightTables);
        return flightTables;

    }

    private List<FlightTable> iteraorList(List<FlightTable> flightTables){
        if (flightTables.size() == 0){
            System.out.println("当前没有航班信息");
        }else{
//            System.out.println("[编号]\t航班号\t\t\t\t\t起飞时间\t\\\t\t飞行时间\t    \t出发地   目的地\t  剩余座位\t 票价");
            for (FlightTable f:flightTables){
                System.out.print("[编号] " + f.getId() + "\t\t");
                System.out.print("[航班号]  " + f.getFlightNumber() + "\t\t\t");
                System.out.print("[起飞时间" + f.getTakeOffTime() + "\t");
                System.out.print("[飞行时间]" + f.getFlyingTime() +"\t\t\t");
                System.out.print("[出发地]" + f.getStartPlace() + "\t");
                System.out.print("[目的地]" + f.getEndPlace() + "\t\t");
                System.out.print("[剩余票数]" + f.getTickets() + "\t\t");
                System.out.print("[价格]" + f.getPrice() );
                System.out.println();
            }
        }
        return flightTables;
    }

    private void iterRorList(List<Record> records){
        User user = new User();
        if (records.size() == 0){
            System.out.println("当前没有订单信息");
        }else{
//            System.out.println("订单号\t省份证\t\t\t手机号\t\t姓名\t航班号\t出发地\t目的地\t\t出发时间");
            for(Record r :records){
                System.out.println("[订单号]: " + r.getOrderNumber());
                System.out.println("[身份证] : " + r.getUserId());
                System.out.println("[手机号]: " + user.getPhoneNumber());
                System.out.println("[姓名]: " +r.getName());
                System.out.println("[航班号]: " + r.getFlightNumber());
                System.out.println("[出发地]: " + r.getStartPlace());
                System.out.println("[目的地]: " + r.getEndPlace());
                System.out.println("[出发时间]: " + r.getTakeOffTime());
            }
        }
    }

    public void update(){
        Scanner update = new Scanner(System.in);
        System.out.println("请输入航班信息");
        String number = update.next();
        List<FlightTable> lists = flightTableService.getFidNumber(number);
        iteraorList(lists);
        if (lists.size() > 0){
            System.out.println("请选择需要修改的编号: ");
            int id = update.nextInt();
            FlightTable flightTable = adminsterService.getFidById(id);
            if (null == lists){
                System.out.println("暂无航班信息");
            }else {
                System.out.println("请重新输入飞行时间");
                String takeOffTime = update.next();
                while (!CheckUtils.isFormatTime(takeOffTime) || DataUtils.returnTime(takeOffTime).getTime() < DataUtils.getNowTime().getTime()){
                    System.out.println("输入的时间不符合规范，规范(yyyy-MM-dd_HH:mm：ss) / 时间不对");
                    takeOffTime = update.next();
                }
                takeOffTime = takeOffTime.replace("_"," ");
                flightTable.setTakeOffTime(DataUtils.StringTurnIntoDate(takeOffTime));
                adminsterService.update(flightTable);
                System.out.println("修改成功");
                FlightTable cflightTable = adminsterService.getFidById(id);
                System.out.print("[编号] " + flightTable.getId() + "\t\t");
                System.out.print("[航班号]  " + flightTable.getFlightNumber() + "\t\t\t");
                System.out.print("[起飞时间" + flightTable.getTakeOffTime() + "\t");
                System.out.print("[飞行时间]" + flightTable.getFlyingTime() +"\t\t\t");
                System.out.print("[出发地]" + flightTable.getStartPlace() + "\t");
                System.out.print("[目的地]" + flightTable.getEndPlace() + "\t\t");
                System.out.print("[剩余票数]" + flightTable.getTickets() + "\t\t");
                System.out.print("[价格]" + flightTable.getPrice() );
                System.out.println();
            }
        }
        else {
            System.out.println("修改失败");
        }
    }
    public void addFlight(){
        Scanner flightInput = new Scanner(System.in);
        System.out.println("请输入航班编号： ");
        String flightNumber = flightInput.next();
        while (!CheckUtils.checkFlightNumberIsTrue(flightNumber)){
            System.out.println("输入的航班编号不规范 格式（XXXXXXX 前两位是字母，后四位是数字");
            flightNumber = flightInput.next();
        }
        System.out.println("请输入起飞时间： ");
        String takeOffTime = flightInput.next();

        while (!CheckUtils.isFormatTime(takeOffTime) || DataUtils.returnTime(takeOffTime).getTime() < DataUtils.getNowTime().getTime()){
            System.out.println("输入的时间不符合规范，规范(yyyy-MM-dd_HH:mm：ss) ");
            takeOffTime = flightInput.next();
        }
        takeOffTime = takeOffTime.replace("_"," ");
        if (!adminsterService.judgeIsTrue(flightNumber,takeOffTime)){
            System.out.println("请输入飞行时间：");
            int flyingTime = flightInput.nextInt();
            while (!CheckUtils.checkFlyTime(flyingTime)){
                System.out.println("请输入正整数");
                flyingTime = flightInput.nextInt();
            }
            System.out.println("请输入输入目的地：");
            String endPlace = flightInput.next();
            System.out.println("请输入出发地 ");
            String startPlace = flightInput.next();
            System.out.println("请输入票数");
            int tickets = flightInput.nextInt();
            while (!CheckUtils.checkFlyTime(tickets)) {
                System.out.println("请输入正整数");
                tickets = flightInput.nextInt();
            }
            System.out.println("请输入票价");
            double price = flightInput.nextDouble();
            FlightTable flightTable = new FlightTable();
            flightTable.setFlightNumber(flightNumber);
            flightTable.setTakeOffTime(DataUtils.StringTurnIntoDate(takeOffTime));
            flightTable.setFlyingTime(flyingTime);
            flightTable.setStartPlace(startPlace);
            flightTable.setEndPlace(endPlace);
            flightTable.setTickets(tickets);
            flightTable.setPrice(price);
            flightTable.setAlltickets(tickets);
            adminsterService.insert(flightTable);
            System.out.println("添加成功");
        }
        else {
            System.out.println("航班已经存在");
        }

    }


    public void delFlight(){
        FlightTable flightTable = null;
        List<FlightTable> lists = queryFlight();
        String flightNumber = null;
        if (lists.size() > 0){
            flightNumber = lists.get(0).getFlightNumber();
            System.out.println("请输入要删除的航班编号");
            Scanner delInput = new Scanner(System.in);
            int id = delInput.nextInt();
            flightTable = flightTableService.getFlightById(id);
            List<Record> recordsOfFlight = recordService.getRecordByFlightNumberAndTakeofftime(flightTable.getFlightNumber(),DataUtils.DataIntoString(flightTable.getTakeOffTime()));
            if (recordsOfFlight.size() > 0){
                System.out.println("当前航班已经有乘客，无法删除");
                iterRorList(recordsOfFlight);

            }else {
                int result = adminsterService.delete(flightNumber,id);
                if (result > 0){
                    System.out.println("删除成功");
                }else {
                    System.out.println("删除失败");
                }
            }
        }else{
            System.out.println("没有此航班");
        }
    }

    public List<FlightTable> queryFlight(){
        System.out.println("1. 按出发地,目的地，日期查询\n2.按航班号查询");
        Scanner select = new Scanner(System.in);
        System.out.print("请选择");
        int choice = select.nextInt();
        if (choice == 1){
            return quryFlightByCondiition();
        }else if (choice == 2){
            return queryByFlightNumber();
        }else {
            System.out.println("请选择正确的数字");
        }
        return null;
    }

    private List<FlightTable> queryByFlightNumber(){
        System.out.println("请输入要查询的航班号");
        Scanner flightNumberInput = new Scanner(System.in);
        String flightNumber = flightNumberInput.next();
        List<FlightTable> lists = flightTableService.getFidNumber(flightNumber);
        iteraorList(lists);
        return lists;
    }
    public static void main(String[] args) {
        AdminsterController adminsterController = new AdminsterController();

//        adminsterController.login();
        FlightTableServiceImpl flightTableService = new FlightTableServiceImpl();
//         List<FlightTable> flightTables = flightTableService.getFidBy("2020-01-12 22:32:46","2020-01-12 22:32:48","2","taizhou");
        FlightTable flightTable = new FlightTable();
        flightTable.setAlltickets(20);
        flightTable.setPrice(500.0);
        flightTable.setEndPlace("泰州");
        flightTable.setTickets(20);
        flightTable.setTakeOffTime(DataUtils.StringTurnIntoDate("2020-01-12 22:32:46"));
        flightTable.setFlightNumber("as123");
        flightTable.setStartPlace("广州");
        flightTable.setId(1);
//        System.out.println("[编号]\t航班号\t\t\t\t\t起飞时间\t\t\t\t飞行时间\t    \t出发地   目的地\t  剩余座位\t 票价");
        System.out.print("[编号] " + flightTable.getId() + "\t\t");
        System.out.print("[航班号]  " + flightTable.getFlightNumber() + "\t\t\t");
        System.out.print("[起飞时间" + flightTable.getTakeOffTime() + "\t");
        System.out.print("[飞行时间]" + flightTable.getFlyingTime() +"\t\t\t");
        System.out.print("[出发地]" + flightTable.getStartPlace() + "\t");
        System.out.print("[目的地]" + flightTable.getEndPlace() + "\t\t");
        System.out.print("[剩余票数]" + flightTable.getTickets() + "\t\t");
        System.out.print("[价格]" + flightTable.getPrice() );
        System.out.println();
    }

}
