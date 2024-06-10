package com.team.timetableManagmentSystem.DTOs;

public class freetimeForStaff {

    private int Id;
    private node dayStartEnd;
    private int staffId;
    private String staffName;

    public freetimeForStaff() {
    }

    public freetimeForStaff(int Id, node dayStartEnd, int roomId, String roomName) {
        this.Id = Id;
        this.dayStartEnd = dayStartEnd;
        this.staffId = roomId;
        this.staffName = roomName;
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
     * @return the staffId
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * @param roomId
     */
    public void setStaffId(int roomId) {
        this.staffId = roomId;
    }

    /**
     * @return the staffName
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setStaffName(String roomName) {
        this.staffName = roomName;
    }

}
