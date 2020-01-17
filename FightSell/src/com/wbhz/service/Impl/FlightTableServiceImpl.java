package com.wbhz.service.Impl;

import com.wbhz.dao.Impl.FlightTableDaoImpl;
import com.wbhz.dao.Impl.RecordDaoImpl;
import com.wbhz.entity.FlightTable;
import com.wbhz.entity.Record;
import com.wbhz.service.FlightTableService;
import com.wbhz.util.DataUtils;

import java.sql.SQLException;
import java.util.List;

public class FlightTableServiceImpl implements FlightTableService {
    private FlightTableDaoImpl flightTableDao = new FlightTableDaoImpl();
    private RecordDaoImpl recordDao = new RecordDaoImpl();

    /**
     * 更新航班信息
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
            System.out.println("更新失败");
        }
        return result;
    }

    /**
     * 根据订单号获取航班信息
     * @param  orderNumber 航班号
     * @return
     */
    @Override
    public FlightTable getFlightTableByOrderNumber(String orderNumber) {
        FlightTable flightTable = null;
        Record record = null;
        try {
            record = recordDao.getFlightTableByOrderNumber(orderNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            flightTable = flightTableDao.getFlightbyflightNumber(record.getFlightNumber(),
                    DataUtils.DataIntoString(record.getTakeOffTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightTable;
    }

    /**
     * 根据ID获得航班信息
     * @param id 排序ID
     * @return
     */
    @Override
    public FlightTable getFlightById(int id) {
        FlightTable flightTable = null;
        try {
            flightTable = flightTableDao.getflightbyid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightTable;
    }

    @Override
    public List<FlightTable> getFidBy(String startTime, String endTime, String startPlace, String endPlace) {
        List<FlightTable> flightTables =flightTableDao.getFidbyTimeAndPlace(startPlace, endPlace,startTime,endTime);
        return flightTables;
    }

    @Override
    public List<FlightTable> getFidNumber(String flightNumber) {
        List<FlightTable> flightTables = flightTableDao.getFIDbyOrderNumber(flightNumber);
        return flightTables;
    }
}
