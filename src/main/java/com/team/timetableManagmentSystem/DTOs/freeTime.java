/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

/**
 *
 * @author Mostafa
 */
public class freeTime {
    
    private int Id;
    private ArrayList<node> dayStartEnd;

    public freeTime() {
        dayStartEnd = new ArrayList<>();
    }

    public freeTime(int Id, ArrayList<node> dayStartEnd) {
        this.Id = Id;
        this.dayStartEnd = dayStartEnd;
    }
    
    

    /**
     * @return the dayStartEnd
     */
    public ArrayList<node> getDayStartEnd() {
        return dayStartEnd;
    }

    /**
     * @param dayStartEnd the dayStartEnd to set
     */
    public void setDayStartEnd(ArrayList<node> dayStartEnd) {
        this.dayStartEnd = dayStartEnd;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

}
