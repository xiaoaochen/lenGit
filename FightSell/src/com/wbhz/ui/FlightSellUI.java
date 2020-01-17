package com.wbhz.ui;

import com.wbhz.util.CheckUtils;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class FlightSellUI {

    public static void main(String[] args) throws SQLException, ParseException {
        AdminsterUi adminsterUi = new AdminsterUi();
        UserUi userUi = new UserUi();
        System.out.println("-------------飞机订票系统-------------");
        System.out.println("请输入登陆身份：\n1:普通用户\n2:管理员");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        while (number < 1 || number > 2){
            System.out.println("请输入正确的数字");
            number = scanner.nextInt();
        }
        switch (number){
            case 1:
                userUi.initUserUi();
                break;
            case 2:
                adminsterUi.login();
                break;
        }
//
    }
}
