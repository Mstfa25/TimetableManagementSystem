package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class FreeTimeForRooms {

    private int Id;
    private node dayStartEnd;
    private int roomId;
    private String roomName;

    public FreeTimeForRooms() {
    }

    public FreeTimeForRooms(int Id, node dayStartEnd, int roomId, String roomName) {
        this.Id = Id;
        this.dayStartEnd = dayStartEnd;
        this.roomId = roomId;
        this.roomName = roomName;
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

    /**
     * @return the dayStartEnd
     */
    public node getDayStartEnd() {
        return dayStartEnd;
    }

    /**
     * @param dayStartEnd the dayStartEnd to set
     */
    public void setDayStartEnd(node dayStartEnd) {
        this.dayStartEnd = dayStartEnd;
    }

    /**
     * @return the roomId
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
}
