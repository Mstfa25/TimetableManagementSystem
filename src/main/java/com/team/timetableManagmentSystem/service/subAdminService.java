package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.*;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class subAdminService {

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
