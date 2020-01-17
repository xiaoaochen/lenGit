package com.wbhz.util;

import com.wbhz.controller.UserController;
import org.junit.Test;

import java.sql.SQLException;

public class testUserController {
    UserController userController = new UserController();
@Test
    public void testLogin() throws SQLException {
        userController.login();
    }
}
