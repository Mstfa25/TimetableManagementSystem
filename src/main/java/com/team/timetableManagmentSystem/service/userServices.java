package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.Branch;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class userServices {

    public int[] userRole(String username, String password) {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select(
                    "SELECT id, role "
                    + "FROM login "
                    + "WHERE BINARY username = \"" + username + "\" AND BINARY password = \"" + password + "\""
            );
            if (rs.next()) {
                return new int[]{rs.getInt("id"), rs.getInt("role")};
            }
        } catch (SQLException ex) {
            Logger.getLogger(userServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return new int[]{-1};
    }

    public Object getSubAdminBranches(int staffId) {
        connection conn = new connection();
        ArrayList<Branch> branches = new ArrayList<>();
        ResultSet rs = conn.select("select branchId from subadmin where id = " + staffId);
        try {
            while (rs.next()) {
                branches.add(new Branch(rs.getInt("branchId")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return branches;
    }

}
