
package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;


public class roomType {
    private int id;
    private String name;

    public roomType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public roomType(int id) {
        this.id=id;
    }
    
    

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
