package com.wbhz.ui;

import com.mysql.cj.jdbc.SuspendableXAConnection;
import com.wbhz.controller.UserController;
import com.wbhz.entity.User;
import com.wbhz.util.CheckUtils;
import com.wbhz.util.DataUtils;
import com.wbhz.util.ParseUtil;

import java.net.ServerSocket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUi {
    UserController userController = new UserController();
    public void initUserUi() throws SQLException, ParseException {

        System.out.println("-------------用户界面-------------");
        System.out.println("------------1.登录系统------------");
        System.out.println("------------2.注册系统------------");
        Scanner input = new Scanner(System.in);
        System.out.println("请选择： ");
        int choice = 0;
        try {
            choice = input.nextInt();
        }catch (Exception e){
            System.out.println("请输入数字");
            System.out.println("请选择： ");
            choice = input.nextInt();
        }
        while (choice <  1||choice >2){
            System.out.println("请选择正确的数字");
            choice = input.nextInt();
        }
        User user = null;
        switch (choice){
            case 1:
                 user = userController.login();
                if (user != null && user.getCount() <= 3){
                    menu(user);
                }else
                   initUserUi();
                break;
            case 2:
                user = userController.register();
                initUserUi();
        }
    }

    private User menu(User user) throws ParseException {
        System.out.println("-------------操作界面-------------");
        System.out.println("--------当前用户：【" + user.getUserName()+"】");
        System.out.println("请选择功能： ");
        System.out.println("1.查询航班信息");
        System.out.println("2.预定机票");
        System.out.println("3.订单改签");
        System.out.println("4.退 票");
        System.out.println("5.修改信息");
        System.out.println("6.推出系统");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请选择： ");
        int choice = 0;
        try {
            choice = scanner.nextInt();
        }catch (Exception e){
            System.out.println("请选择正确的数字");
            choice = scanner.nextInt();
        }
        while (!CheckUtils.checkFlyTime(choice) || choice < 1 || choice >6){
            System.out.println("请选择正确的数字");
            choice = scanner.nextInt();
        }
        switch (choice){
            case 1:
                userController.query();
                back(user);
                break;
            case 2:
                userController.order(user);
                back(user);
                break;
            case 3:
                userController.change(user);
                back(user);
                break;
            case 4:
                userController.refund(user);
                back(user);
                break;
            case 5:
                userController.updateInfo(user);
                back(user);
            default:
                break;
        }
        return user;
    }
    private int back(User user) throws ParseException {
        Scanner backInput = new Scanner(System.in);
        System.out.println("1.返回主界面\n2.退出");
        System.out.println("请选择");
        int choice = 0;
        try{
            choice = backInput.nextInt();
        }catch (Exception e){
            System.out.println("请输入正确的数字");
            System.out.println("请选择");
            choice = backInput.nextInt();
        }




        while (choice < 1||choice >2){
            System.out.println("请选贼正确的数字");
            choice = backInput.nextInt();
        }
        int flag = 0;
        switch (choice){
            case 1:
                menu(user);
                break;
            case 2:
                System.out.println("推出成功");
                System.exit(0);
                break;
        }
        return 1;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("2");
        System.out.println(list);
        list.remove("2");
        System.out.println(list);
    }
}
