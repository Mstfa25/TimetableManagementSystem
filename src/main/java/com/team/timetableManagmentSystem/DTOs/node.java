/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.timetableManagmentSystem.DTOs;

/**
 *
 * @author Mostafa
 */
public class node {

    private Integer startSession;
    private Integer endSession;
    private Integer dayId;

    public node() {
    }


    public node(Integer dayId, Integer startSession, Integer endSession) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.dayId = dayId;
    }
    
    

    /**
     * @return the startSession
     */
    public Integer getStartSession() {
        return startSession;
    }

    /**
     * @param startSession the startSession to set
     */
    public void setStartSession(Integer startSession) {
        this.startSession = startSession;
    }

    /**
     * @return the endSession
     */
    public Integer getEndSession() {
        return endSession;
    }

    /**
     * @param endSession the endSession to set
     */
    public void setEndSession(Integer endSession) {
        this.endSession = endSession;
    }

    /**
     * @return the dayId
     */
    public Integer getDayId() {
        return dayId;
    }

    /**
     * @param dayId the dayId to set
     */
    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

}
