package com.wbhz.util;

import java.util.Date;
import java.util.UUID;

public class orderCodeFactory {
    public static String getRandomNickname() {
        Date date = new Date();
        long numbers = date.getTime();
        String newNumber = ParseUtil.parseStringFromObj(numbers);
        String orderNumber = newNumber.substring(newNumber.length() - 10,newNumber.length());
        return orderNumber;
    }

    public static void main(String[] args) {
        System.out.println(getRandomNickname());
    }
}