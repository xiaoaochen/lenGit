package com.wbhz.service.Impl;

import com.wbhz.dao.Impl.AdminsterDaoImpl;
import com.wbhz.dao.Impl.FlightTableDaoImpl;
import com.wbhz.entity.Adminster;
import com.wbhz.entity.FlightTable;
import com.wbhz.service.AdminsterService;

import java.sql.SQLException;
import java.util.List;

public class AdminsterServiceImpl implements AdminsterService {
    private AdminsterDaoImpl adminsterDao = new AdminsterDaoImpl();
    private FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();

    /**
     * 登陆返回adminster的实体类
     * @param userName 用户名
     * @param user_password 密码
     * @return 为null则表示登陆失败，不为空则登陆成功
     */
    @Override
    public Adminster login(String userName, String user_password) {
        Adminster adminster = null;
        try {
            adminster = adminsterDao.getAdminsterByUsernameAndPassword(userName,user_password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("管理员登陆出错");
        }
        return adminster;
    }

    /**
     *
     * @param flightTable 航班的实体类
     * @return
     */
    @Override
    public int insert(FlightTable flightTable) {
        int result = 0;
        try {
            result = flightTableDao.insert(flightTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按照航班信息和ID删除航班记录
     * @param flightNumber 航班号
     * @param id 排序ID
     * @return
     */
    @Override
    public int delete(String flightNumber, int id) {
       int result = 0;
        try {
            result = flightTableDao.delete(id,flightNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据航班实体类更新航班信息
     * @param flightTable 航班的实体类
     * @return
     */
    @Override
    public int update(FlightTable flightTable) {
        int result = 0;
        try {
            result = flightTableDao.update(flightTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据ID获得航班信息
     * @param id 排序的ID
     * @return
     */
    @Override
    public FlightTable getFidById(int id) {
        FlightTable flightTable = null;
        try {
            flightTable = flightTableDao.getflightbyid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightTable;
    }

    /**
     * 根据最晚出发时间获得航班记录的集合
     * @param takeOffTime 最晚出发时间
     * @return
     */
    @Override
    public List<FlightTable> queryByTime(String takeOffTime) {
        List<FlightTable> list = null;
        try {
            list = flightTableDao.getFlightbytakeOffTime(takeOffTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean judgeIsTrue(String flightNumber, String takeOffTime) {
        boolean result = false;
        try {
            result = flightTableDao.judgeFlightByflightNumber(flightNumber,takeOffTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
