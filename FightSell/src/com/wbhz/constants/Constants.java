package com.wbhz.constants;

public interface Constants {
    /**
     * 通过正则表达式确认输入的数据是否符合规范
     */
    //表单正则
    String USERNAME_REG="[a-zA-Z0-9]{3,10}";  //用户名
    String PASSWORD_REG="[a-zA-Z0-9]{5,20}";  //密码
    String ACTUAL_NAME_REG="[\u4e00-\u9fa5]{2,4}"; //用户姓名
    String PHONE_NUMBER_REG="1[3|5|7|8|]\\d{9}"; //手机格式
    String ID_CARD_NUMBER_REG="\\d{18}"; //身份证
    String FIGHT_NUMBER_REG="[A-z]{2}[0-9]{4}"; //航班号
    String EMAIL_REG = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    String FLYTIME_REG = "^[0-9]*$";
    String ORDER_NUMBER_REG ="\\d{10}";

    //表单提示信息
    String USERNAME_FORMAT_ERROR="用名名为3-10位英文字母";
    String PASSWORD_FORMAT_ERROR="密码为5-20位的字母和数组组合";
    String ACTUAL_NAME_FORMAT_ERROR="姓名为2-4位汉字";
    String PHONE_NUMBER_FORMAT_ERROR="手机号码格式为11位数字";
    String ID_CARD_FORMAT_ERROR="身份证格式为18位";
    String PASSWORD_NOT_MATCH_ERROR="前后输入的密码不符合";
    String ORDER_NUMBER_ERROR = "订单编号格式为10个数字";
}
