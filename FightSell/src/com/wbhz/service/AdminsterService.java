package com.wbhz.service;

import com.wbhz.dao.FightTableDao;
import com.wbhz.entity.Adminster;
import com.wbhz.entity.FlightTable;

import java.util.List;

public interface AdminsterService {
    /**
     * 管理员的业务层
     */
    /**
     * 用户的登陆
     * @param userName 用户名
     * @param user_password 密码
     * @return
     */
    Adminster login(String userName,String user_password);

    /**
     * 添加一条航班信息
     * @param flightTable 航班的实体类
     */
    int insert(FlightTable flightTable);

    /**
     * 删除一条航班信息
     * @param flightNumber 航班号
     * @param id 排序ID
     * @return
     */
    int delete(String flightNumber,int id);

    /**
     * 更新一条航班信息
     * @param flightTable 航班的实体类
     * @return
     */
    int update(FlightTable flightTable);

    /**
     * 根据ID查询一个航班信息
     * @param id 排序的ID
     * @return
     */
    FlightTable getFidById(int id);

    /**
     * 根据起飞时间查询所有符合要求的航班信息
     * @param takeOffTime 最晚出发时间
     * @return
     */
    List<FlightTable> queryByTime(String takeOffTime);

    boolean judgeIsTrue(String flightNumber,String takeOffTime);

}
