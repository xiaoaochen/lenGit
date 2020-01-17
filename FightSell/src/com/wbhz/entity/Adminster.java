package com.wbhz.entity;

public class Adminster {
    /**
     * 管理员类
     * @author :xiaoao
     */
    private String userName;//管理员的用户名
    private String userPassword;//管理员的登陆密码
    private String id;//身份证号
    private String name;//姓名
    private String phoneNumber;//手机号

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "Adminster{" +
                "userName='" + userName + '\'' +
                ", passWord='" + userPassword + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
