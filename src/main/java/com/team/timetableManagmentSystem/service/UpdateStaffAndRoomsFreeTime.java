package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.FreeTime;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Room;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Staff;
import com.team.timetableManagmentSystem.database.connection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class UpdateStaffAndRoomsFreeTime extends Thread {

    private connection conn;
    private ArrayList<Staff> staff;
    private ArrayList<Room> rooms;

    public UpdateStaffAndRoomsFreeTime(ArrayList<Staff> staff, ArrayList<Room> rooms) {
        this.staff = staff;
        this.rooms = rooms;
    }

    public void UpdateTheFreeTimeForStaffAndRooms() {
        this.conn = new connection();
        StringBuilder staffStr = new StringBuilder(), staffIds = new StringBuilder(),
                roomsStr = new StringBuilder(), roomsIds = new StringBuilder();
        for (int j = 0; j < staff.size(); j++) {
            int staffId = staff.get(j).getId();
            staffIds.append(staffId).append(",");
            FreeTime freeTime = staff.get(j).getFreeTime();
            for (int i = 0; i < 6; i++) {
                if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                    staffStr.append("(").append(staffId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                    while (freeTime.dayStartEnd[i].next != null) {
                        freeTime.dayStartEnd[i] = freeTime.dayStartEnd[i].next;
                        if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                            staffStr.append("(").append(staffId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                        }
                    }
                }
            }
        }
        for (int j = 0; j < rooms.size(); j++) {
            int roomId = rooms.get(j).getId();
            roomsIds.append(rooms.get(j).getId()).append(",");
            FreeTime freeTime = rooms.get(j).getFreeTime();
            for (int i = 0; i < 6; i++) {
                if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                    roomsStr.append("(").append(roomId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                    while (freeTime.dayStartEnd[i].next != null) {
                        freeTime.dayStartEnd[i] = freeTime.dayStartEnd[i].next;
                        if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                            roomsStr.append("(").append(roomId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                        }
                    }
                }
            }
        }
        if (staffIds.length() > 0) {
            conn.execute("delete from freetimeforstaff where staffid in (" + staffIds.substring(0, staffIds.length() - 1) + ")");
        }
        if (staffIds.length() > 0) {
            conn.execute("delete from freetimeforrooms where roomid in (" + roomsIds.substring(0, roomsIds.length() - 1) + ")");
        }
        if (staffStr.length() > 1) {
            conn.execute("insert into freetimeforstaff (staffId, DayId, startingTime, enddingTime) values " + staffStr.substring(0, staffStr.length() - 1));
        }
        if (roomsStr.length() > 1) {
            conn.execute("insert into freetimeforrooms (RoomId, DayId, startingTime, enddingTime) values " + roomsStr.substring(0, roomsStr.length() - 1));
        }
        conn.close();
    }

    @Override
    public void run() {
        UpdateTheFreeTimeForStaffAndRooms();
    }

}
