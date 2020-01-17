package com.wbhz.util;

import com.wbhz.entity.Record;
import com.wbhz.service.Impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class testQueryByFlightCondition {
    UserServiceImpl userService = new UserServiceImpl();
    @Test

    public  void  testQuery(){
        List<String> list =userService.showIsCanUsed("2020-04-13 19:24:12","er5634");
        System.out.println(list);

    }
}
