package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * used in the operation of creating timeTable
 * @data_field id is for representing the lectuerGroup
 * @data_field name is for representing the name
 * @data_field lecGroups is for representing the lecGroups in the LectuerGroup
 * @author Mostafa
 */
public class LectureGroup {

    private int id;
    private String name;
    private ArrayList<LecGroup> lecGroups;

    public LectureGroup(int id) {
        this.id = id;
        lecGroups = new ArrayList<>();
    }
    
    public LectureGroup() {
    }

    public LectureGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lecGroups
     */
    public ArrayList<LecGroup> getLecGroups() {
        return lecGroups;
    }

    /**
     * @param lecGroups the lecGroups to set
     */
    public void setLecGroups(ArrayList<LecGroup> lecGroups) {
        this.lecGroups = lecGroups;
    }

    
    /**
     * get the lecGroups from the database by the lectuerGroupId
     */
    void getTheLecGroups() {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select distinct lecgroup.id,"
                    + "lecgroupbranches.branchId "
                    + "from lecgroup "
                    + "inner join lecgroupbranches on lecgroupbranches.lecGroupId=lecgroup.id "
                    + "where lecturegroupId = " + id);
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                if (sb.indexOf("--" + rs.getInt(1) + "--") == -1) {
                    sb.append("--").append(rs.getInt(1)).append("--");
                    LecGroup lecg = new LecGroup(rs.getInt("id"));
                    lecGroups.add(lecg);
                }
                for (int i = 0; i < lecGroups.size(); i++) {
                    if (lecGroups.get(i).getId() == rs.getInt(1)) {
                        lecGroups.get(i).getBranchs().add(new Branch(rs.getInt(2)));
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("get the lecgroups" + e);
        } finally {
            conn.close();
        }
    }

}
