package com.example.demo.academictimetablemanagmentsystem;

public class jobType {

    private int id;
    private String name;

    public jobType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public jobType() {
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
