package com.wbhz.ui;

import com.wbhz.controller.AdminsterController;

import java.util.Scanner;

public class AdminsterUi {
    AdminsterController adminsterController = new AdminsterController();

    public int login(){
        System.out.println("-------------管理员登陆界面-------------");
        int result = 0;
        result = adminsterController.login();
        if (result > 0){
            AdminsterUiInit();
            return 1;
        }else
            login();
            return 0;
    }

    public  int AdminsterUiInit(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("_-------------管理员界面--------------");
            System.out.println("1.航班查询\n2.航班删除\n3.修改航班\n4.添加航班\n5.退出系统");
            System.out.println("请输入正确的数字:");
            int number = scanner.nextInt();
            while (number < 1 || number > 5) {
                System.out.println("请输入正确的数字");
                number = scanner.nextInt();
            }
            switch (number) {
                case 1:
                    new AdminsterController().queryFlight();
                    back();
                    break;
                case 2:
                    new AdminsterController().delFlight();
                    back();
                    break;
                case 3:
                    new AdminsterController().update();
                    back();
                    break;
                case 4:
                    new AdminsterController().addFlight();
                    back();
                    break;
                case 5:
                    System.exit(0);
                default:
                    break;
            }
        return 0;
        }

    private int back(){
        Scanner backInput = new Scanner(System.in);
        System.out.println("1.返回主界面\n2.退出");
        System.out.println("请选择");
        int choice = backInput.nextInt();
        while (choice < 1||choice >2){
            System.out.println("请选贼正确的数字");
            choice = backInput.nextInt();
        }
        int flag = 0;
        switch (choice){
            case 1:
                AdminsterUiInit();
                break;
            case 2:
                System.out.println("推出成功");
                System.exit(0);
                break;
        }
        return 1;
    }
}
