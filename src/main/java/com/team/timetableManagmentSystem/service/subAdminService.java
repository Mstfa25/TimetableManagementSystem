package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.Branch;
import com.team.timetableManagmentSystem.DTOs.LecGroup;
import com.team.timetableManagmentSystem.DTOs.Staff;
import com.team.timetableManagmentSystem.DTOs.course;
import com.team.timetableManagmentSystem.DTOs.room;
import com.team.timetableManagmentSystem.DTOs.timeInTimetable;
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
            sb.append(branches.get(i).getId()).append(",");
        }
        ResultSet rs = conn.select("select distinct timetable.id,timetable.name from timetable where timetable.id in (select timesintimetable.timeTableId from timesintimetable  where timesintimetable.hostingBranchID in (" + sb.substring(0, sb.length() - 1) + ") or timesintimetable.id in (select branchesintimeintimetable.timeInTimetableId from branchesintimeintimetable where branchesintimeintimetable.branchId in (" + sb.substring(0, sb.length() - 1) + ") ));");

        return null;
    }

    public ArrayList<timeInTimetable> getATimetable(int timetableId,ArrayList<Branch> branches) {
        StringBuilder sb1=new StringBuilder();
        for (int i = 0; i < branches.size(); i++) {
            sb1.append(branches.get(i).getId()).append(",");
        }
        connection conn = new connection();
        ArrayList<timeInTimetable> tims = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        ResultSet rs = conn.select("select timesintimetable.id,"
                + "timesintimetable.staffId,"
                + " staff.name,"
                + " timesintimetable.courseId,"
                + " courses.name,"
                + "courses.code,"
                + "timesintimetable.hostingBranchId,"
                + "branch.name,"
                + "timesintimetable.hostingRoomId,"
                + "rooms.name,"
                + "timesintimetable.dayid,"
                + "timesintimetable.startingTime,"
                + "timesintimetable.enddingTime,"
                + "timesintimetable.lecGroupId, "
                + "lecgroup.name "
                + "from timesintimetable "
                + "LEFT JOIN staff on staff.id=timesintimetable.staffId "
                + "left join courses on courses.id=timesintimetable.courseId "
                + "left join branch on branch.id=timesintimetable.hostingbranchId "
                + "left join rooms on rooms.id=timesintimetable.hostingRoomId "
                + "left join lecgroup on lecgroup.id=timesintimetable.lecgroupId "
                + "order by timesintimetable.id");
        try {
            while (rs.next()) {
                sb.append(rs.getInt(1)).append(",");
                timeInTimetable t = new timeInTimetable(rs.getInt(1),
                        new Staff(rs.getInt(2), rs.getString(3)),
                        new course(rs.getInt(4), rs.getString(5), rs.getString(6), 0, 0, 0, 0, 0, null, 0, null, 0, null, 0, null),
                        new ArrayList<Branch>(),
                        new Branch(rs.getInt(7), rs.getString(8)),
                        new ArrayList<room>(),
                        new room(rs.getInt(9), rs.getString(10)),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), null,
                        new LecGroup(rs.getInt(14), rs.getString(15)), null);
                tims.add(t);
            }
            rs = conn.select("select timeInTimetableId, branchId ,branch.name from  branchesintimeintimetable left join branch on branch.id=branchId where timeInTimetableId in (" + sb.substring(0, sb.length() - 1) + ") order by timeInTimetableId");
            int i = 0;
            while (rs.next()) {
                for (int j = i; j < tims.size(); j++) {
                    if (rs.getInt(1) == tims.get(j).getId()) {
                        i = j;
                        tims.get(j).getBranchs().add(new Branch(rs.getInt(2), rs.getString(3)));
                        break;
                    }
                }
            }

            rs = conn.select("select timeInTimetableId, RoomId ,rooms.name,rooms.branchId from  roomsintimeintimetable left join rooms on rooms.id=roomid where timeInTimetableId in (" + sb.substring(0, sb.length() - 1) + ") order by timeInTimetableId");
            i = 0;
            while (rs.next()) {
                for (int j = i; j < tims.size(); j++) {
                    if (rs.getInt(1) == tims.get(j).getId()) {
                        i = j;
                        tims.get(j).getRooms().add(new room(rs.getInt(2), rs.getString(3), 0, 0, null, rs.getInt(4), null));
                        break;
                    }
                }
            }

            rs = conn.select("select * from sectiongroupnamefortimeintimetable order by timeInTimetableId");
            i = 0;
            while (rs.next()) {
                for (int j = i; j < tims.size(); j++) {
                    if (rs.getInt(1) == tims.get(j).getId()) {
                        i = j;
                        tims.get(j).setSectionGroupName(rs.getString(2));
                        break;
                    }
                }
            }
            return tims;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

}
