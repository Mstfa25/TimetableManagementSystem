package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

/**
 * used in the operation of creating timeTable
 * @data_field name specify the name of the faculty
 * @data_field id id to specify the faculty
 * @author Mostafa
 */
public class Faculty {

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty() {
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
