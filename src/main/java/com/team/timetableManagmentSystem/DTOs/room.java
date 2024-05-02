package com.team.timetableManagmentSystem.DTOs;

public class room {

    private int id;
    private String name;
    private int capacity;
    private roomType roomtype;
    private Branch branch;

    public room(int id, String name, int capacity, int roomTypeid, String roomypename, int branchId, String branchName) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.roomtype = new roomType(roomTypeid, roomypename);
        this.branch = new Branch(branchId, branchName);
    }

    public room(int id) {
        this.id=id;
    }
    public room(int id,String name) {
        this.id=id;
        this.name=name;
    }

    public room() {
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
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the roomtype
     */
    public roomType getRoomtype() {
        return roomtype;
    }

    /**
     * @param roomtype the roomtype to set
     */
    public void setRoomtype(roomType roomtype) {
        this.roomtype = roomtype;
    }

    /**
     * @return the branch
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "("+id+")";
    }
}
