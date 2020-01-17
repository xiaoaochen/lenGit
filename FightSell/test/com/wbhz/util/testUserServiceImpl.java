package com.wbhz.util;

import com.wbhz.service.Impl.UserServiceImpl;
import org.junit.Test;

public class testUserServiceImpl {
    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void testreturnTicket(){
        userService.returnTicket("12234");
    }
}
