package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.timeInTimetable;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        try {
            conn.makeStatement();
            Statement stmt = conn.stat;
            for (timeInTimetable TimesIntimetable1 : TimesIntimetable) {

                StringBuilder s = new StringBuilder();
                s.append(TimesIntimetable1.getTimetable().getId()).append(",").append(TimesIntimetable1.getCourse().getId()).append(",").append(TimesIntimetable1.getStaff().getId()).append(",").append(TimesIntimetable1.getHostingRoom().getId()).append(",").append(TimesIntimetable1.getHostingBranch().getId()).append(",").append(TimesIntimetable1.getDay()).append(",").append(TimesIntimetable1.getStartingTime()).append(",").append(TimesIntimetable1.getEndingTime()).append(",").append((TimesIntimetable1.getLecGroup() != null) ? TimesIntimetable1.getLecGroup().getId() : null);
                // Execute insert statement with RETURN_GENERATED_KEYS
                stmt.executeUpdate("insert into timesintimetable (timeTableId, courseId, StaffId, hostingRoomID, hostingBranchID, DayId, startingTime, enddingTime, lecGroupId) values (" + s.toString() + ")", Statement.RETURN_GENERATED_KEYS);
                // Retrieve the auto-generated ID
                int id = 0;
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                // Further processing with the retrieved ID
                if (TimesIntimetable1.getBranchs() != null && !TimesIntimetable1.getBranchs().isEmpty()) {
                    // Update other tables using the retrieved ID
                    conn.execute("insert into roomsInTimeInTimetable (roomId) values " + TimesIntimetable1.getRooms().toString().substring(1, TimesIntimetable1.getRooms().toString().length() - 1));
                    conn.execute("insert into BranchesInTimeInTimetable (BranchId) values " + TimesIntimetable1.getBranchs().toString().substring(1, TimesIntimetable1.getBranchs().toString().length() - 1));
                    conn.execute("update roomsInTimeInTimetable set timeInTimetableId = " + id + " where timeInTimetableId=0;");
                    System.out.println("update roomsInTimeInTimetable set timeInTimetableId = " + id + " where timeInTimetableId=0;");
                    conn.execute("update branchesintimeintimetable set branchesintimeintimetable.timeInTimetableId = " + id + " where timeInTimetableId=0;");
                    System.out.println("update branchesintimeintimetable set branchesintimeintimetable.timeInTimetableId = " + id + " where timeInTimetableId=0;");
                }

                System.out.println("out");
            }
        } catch (Exception e) {
            System.out.println(e + "\nhere");
        } finally {
            conn.close(); // Close connection
        }
    }

}
