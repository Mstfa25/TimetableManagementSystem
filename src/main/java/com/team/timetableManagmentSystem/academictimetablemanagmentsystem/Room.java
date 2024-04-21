package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

public class Room {

    private int id;
    private roomType roomtype;
    private int capacity;
    private Branch branch;
    private String name;
    private FreeTime freeTime;

    public Room(int id, int typeId) {
        this.id = id;
        roomtype = new roomType(typeId);
        freeTime = new FreeTime();
    }

    public Room(int id, String name, int capacity, int roomTypeid, String roomypename, int branchId, String branchName) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.roomtype = new roomType(roomTypeid, roomypename);
        this.branch = new Branch(branchId, branchName);
    }

    public Room(int id) {
        this.id = id;
    }

    public Room(int id, String name) {
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
     * @return the freeTime
     */
    public FreeTime getFreeTime() {
        return freeTime;
    }

    /**
     * @param freeTime the freeTime to set
     */
    public void setFreeTime(FreeTime freeTime) {
        this.freeTime = freeTime;
    }

    void getTheFreeTime() {
        freeTime.getDataForRooms(id);
    }

    @Override
    public String toString() {
        return id + " ";
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

}
