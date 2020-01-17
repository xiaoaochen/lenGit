package com.wbhz.util;

import com.wbhz.constants.Constants;

import java.util.Scanner;

public class CheckUtils {
    /**
     * 判断用户名是否符合要求
     * @param username
     * @return
     */

    public static Boolean checkUsername(String username){
        return username.matches(Constants.USERNAME_REG);
    }

    /**
     * 判断密码是否符合要求
     * @param password
     * @return
     */
    public static Boolean checkPassword(String password){
        return password.matches(Constants.PASSWORD_REG);
    }

    /**
     * 判断真实姓名是否正确
     * @param actualName
     * @return
     */
    public static Boolean checkActualName(String actualName){
        return actualName.matches(Constants.ACTUAL_NAME_REG);
    }

    /**
     * 判断手机号码是否正确
     * @param phoneNumber
     * @return
     */
    public static Boolean checkPhoneNumber(String phoneNumber){
        return phoneNumber.matches(Constants.PHONE_NUMBER_REG);
    }

    /**
     * 判断身份证号码是否正确
     * @param idCardNumber
     * @return
     */
    public static Boolean checkIdCardNumber(String idCardNumber){
        return idCardNumber.matches(Constants.ID_CARD_NUMBER_REG);
    }

    /**
     * 检查飞机票是否符合规范
     * @param tickNumber
     * @return
     */
    public static Boolean checkFlightNumberIsTrue(String tickNumber){
        return tickNumber.matches(Constants.FIGHT_NUMBER_REG);
    }

    public static Boolean checkEmail(String email){
        return email.matches(Constants.EMAIL_REG);
    }
    public static boolean isFormatTime(String str) {
        if (!str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}_[0-9]{2}:[0-9]{2}:[0-9]{2}") && !"~".equals(str)) {
            return false;
        }
        return true;
    }
    public static boolean checkFlyTime(int flyTime){
        return ParseUtil.parseStringFromObj(flyTime).matches(Constants.FLYTIME_REG);
    }

    public static boolean checkOrderNumber(String orderNumber){

        return orderNumber.matches(Constants.ORDER_NUMBER_REG);
    }
    public static void main(String[] args) {
        System.out.println(checkOrderNumber("8879299196"));
        }


}
