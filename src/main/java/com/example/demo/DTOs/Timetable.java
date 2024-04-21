
package com.example.demo.DTOs;

import java.util.ArrayList;

public class Timetable {
    private int id;
    private String name;
    private ArrayList<timeInTimetable> timesInTimetable;

    public Timetable(int id, String name, ArrayList<timeInTimetable> timesInTimetable) {
        this.id = id;
        this.name = name;
        this.timesInTimetable = timesInTimetable;
    }

    public Timetable() {
    }
    
    public Timetable(int id) {
        this.id=id;
    }

    public Timetable(int id, String name) {
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
     * @return the timesInTimetable
     */
    public ArrayList<timeInTimetable> getTimesInTimetable() {
        return timesInTimetable;
    }

    /**
     * @param timesInTimetable the timesInTimetable to set
     */
    public void setTimesInTimetable(ArrayList<timeInTimetable> timesInTimetable) {
        this.timesInTimetable = timesInTimetable;
    }
}
