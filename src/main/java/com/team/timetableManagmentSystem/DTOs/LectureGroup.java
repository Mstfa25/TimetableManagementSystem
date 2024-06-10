package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class LectureGroup {

    private int id;
    private String name;
    private ArrayList<LecGroup> lecgroups;

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

    /**
     * @return the lecgroups
     */
    public ArrayList<LecGroup> getLecgroups() {
        return lecgroups;
    }

    /**
     * @param lecgroups the lecgroups to set
     */
    public void setLecgroups(ArrayList<LecGroup> lecgroups) {
        this.lecgroups = lecgroups;
    }
}
