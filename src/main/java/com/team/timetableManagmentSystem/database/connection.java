package com.team.timetableManagmentSystem.database;

import java.sql.*;
import org.springframework.stereotype.Service;

@Service
public class connection {

    Connection con;
    Statement stat;

    public void makeConnection() {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/timetablemanagementsystem", "root", "12345");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void makeStatement() {
        try {
            makeConnection();
            stat = con.createStatement();
        } catch (SQLException ex) {
            stat = null;
        }
    }

    public void close() {
        try {
            
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            System.out.println(ex + "1");
        }
    }

    public void execute(String s) {
        try {
            makeStatement();
            stat.execute(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ResultSet select(String s) {

        try {
            execute(s);
            return stat.getResultSet();
        } catch (SQLException ex) {
            return null;
        }
    }

}
