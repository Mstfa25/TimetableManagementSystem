package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.Branch;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class subAdminService {

    public Object getTimetablesToView(ArrayList<Branch> branches) {
        connection conn = new connection();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branches.size(); i++) {
            sb.append(branches.get(i).getId() + ",");
        }

        ResultSet rs = conn.select("select distinct timetable.id,timetable.name from timetable where timetable.id in (select timesintimetable.timeTableId from timesintimetable  where timesintimetable.hostingBranchID in (" + sb.substring(0, sb.length() - 1) + ") or timesintimetable.id in (select branchesintimeintimetable.timeInTimetableId from branchesintimeintimetable where branchesintimeintimetable.branchId in (" + sb.substring(0, sb.length() - 1) + ") ));");
        
        return null;
    }
}
