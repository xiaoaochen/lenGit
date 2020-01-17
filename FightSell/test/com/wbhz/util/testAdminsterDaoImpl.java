package com.wbhz.util;

import com.wbhz.dao.Impl.AdminsterDaoImpl;
import com.wbhz.entity.Adminster;
import org.junit.Test;

import java.sql.SQLException;

public class testAdminsterDaoImpl {
    @Test
    public void testgetAdminsterByUsernameAndPassword() throws SQLException {
        AdminsterDaoImpl adminsterDao = new AdminsterDaoImpl();
        Adminster adminster = adminsterDao.getAdminsterByUsernameAndPassword("cwj","123");
        System.out.println(adminster);
    }
}
