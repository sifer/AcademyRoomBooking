package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class DbTestController {

    /*
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/dbtest", produces = "text/plain")
    @ResponseBody
    public String testDb() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1+1")) {
            rs.next();
            int two = rs.getInt(1);
            return "Database connectivity seems " + (two == 2 ? "OK." : "weird!");
        }
    }
    */

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/dbtest", produces = "text/plain")
    @ResponseBody
    public String testDb() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             // SELECT roomid, name FROM room
             // SELECT 1+1
             ResultSet rs = stmt.executeQuery("SELECT roomid, name FROM room")) {
                rs.next();
                String name = rs.getString(2);
            return "Database, name: " + name;
        }
    }


}
