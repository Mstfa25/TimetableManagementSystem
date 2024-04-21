package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.timeInTimetable;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertTimetable extends Thread {

    connection conn;
    timeInTimetable[] TimesIntimetable;

    public InsertTimetable(timeInTimetable[] TimesIntimetable) {

        this.TimesIntimetable = TimesIntimetable;
    }

    @Override
    public void run() {
        insertTimesInTimetable();
    }

    public void insertTimesInTimetable() {
        this.conn = new connection();
        for (timeInTimetable TimesIntimetable1 : TimesIntimetable) {
            StringBuilder s = new StringBuilder();
            s.append(TimesIntimetable1.getTimetable().getId()).append(",").append(TimesIntimetable1.getCourse().getId()).append(",").append(TimesIntimetable1.getStaff().getId()).append(",").append(TimesIntimetable1.getHostingRoom().getId()).append(",").append(TimesIntimetable1.getHostingBranch().getId()).append(",").append(TimesIntimetable1.getDay()).append(",").append(TimesIntimetable1.getStartingTime()).append(",").append(TimesIntimetable1.getEndingTime()).append(",").append((TimesIntimetable1.getLecGroup() != null) ? TimesIntimetable1.getLecGroup().getId() : null);
            conn.execute("insert into timesintimetable (timeTableId, courseId, StaffId, hostingRoomID, hostingBranchID, DayId, startingTime, enddingTime, lecGroupId) values (" + s.toString() + ")");
            if (TimesIntimetable1.getBranchs() != null) {
                int id = 0;
                try {
                    ResultSet rs = conn.select("SELECT id from timesintimetable where courseId = " + TimesIntimetable1.getCourse().getId() + " and staffId = " + TimesIntimetable1.getStaff().getId() + " and startingtime = " + TimesIntimetable1.getStartingTime() + " and dayId = " + TimesIntimetable1.getDay());
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                conn.execute("insert into roomsInTimeInTimetable (roomId) values " + TimesIntimetable1.getRooms().toString().substring(1, TimesIntimetable1.getRooms().toString().length() - 1));
                conn.execute("insert into BranchesInTimeInTimetable (BranchId) values " + TimesIntimetable1.getBranchs().toString().substring(1, TimesIntimetable1.getBranchs().toString().length() - 1));
                conn.execute("update roomsInTimeInTimetable set timeInTimetableId = " + id + " where timeInTimetableId=0;");
                conn.execute("update branchesintimeintimetable set branchesintimeintimetable.timeInTimetableId = " + id + " where timeInTimetableId=0;");

            }
        }
        conn.close();
    }

}
