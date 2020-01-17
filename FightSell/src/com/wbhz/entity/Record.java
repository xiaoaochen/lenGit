package com.wbhz.entity;

import java.util.Date;

public class Record {
    /**
     * 订单的是实体类
     *
     * @author xiaoao
     */
    private String name;//用户姓名
    private String userId;//用户的身份证号
    private String orderNumber; //订单编号
    private String flightNumber;//航班号
    private String startPlace;//出发地点
    private String endPlace;//目的地
    private Double price;//票价
    private Date buyTime;//购票时间
    private Date takeOffTime;//出发时间
    private Date returnTicketTime;//可以退票时间
    private int seat;//座位号
    private String userName;
    private String canUsed;
    private String buyId;

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getCanUsed() {
        return canUsed;
    }

    public void setCanUsed(String canUsed) {
        this.canUsed = canUsed;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Date takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public Date getReturnTicketTime() {
        return returnTicketTime;
    }

    public void setReturnTicketTime(Date returnTicketTime) {
        this.returnTicketTime = returnTicketTime;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", startPlace='" + startPlace + '\'' +
                ", endPlace='" + endPlace + '\'' +
                ", price=" + price +
                ", buyTime=" + buyTime +
                ", takeOffTime=" + takeOffTime +
                ", returnTicketTime=" + returnTicketTime +
                ", seat=" + seat +
                ", userName='" + userName + '\'' +
                '}';
    }
}
