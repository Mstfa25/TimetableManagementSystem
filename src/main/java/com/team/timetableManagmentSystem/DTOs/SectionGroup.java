package com.team.timetableManagmentSystem.DTOs;

public class SectionGroup {

    public SectionGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SectionGroup() {
    }

    private int id;
    private String name;

    /**
     * @return the id
     */
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
