

package com.team.timetableManagmentSystem.DTOs;


public class LectureGroup {

    private int id;
    private String name;

    public LectureGroup() {
    }

    public LectureGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
}
