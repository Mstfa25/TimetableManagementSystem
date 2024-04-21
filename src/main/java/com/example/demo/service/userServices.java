package com.example.demo.service;

import com.example.demo.database.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServices {

    @Autowired
    private connection conn;

    public Object getSubAdminBranches() {
        try {
            ResultSet rs = conn.select("");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public int userRole(String username, String password) {
        try {
            ResultSet rs = conn.select("select username,"
                    + "role"
                    + " from login "
                    + "where username like '" + username + "' and password like '" + password + "'");
            if (rs.next()) {
                return rs.getInt(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return -1;
    }

}
