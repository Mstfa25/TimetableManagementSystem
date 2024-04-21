package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class timeInTimetable {

    private int id;
    private Staff staff;
    private course course;
    private ArrayList<Branch> branchs;
    private Branch hostingBranch;
    private ArrayList<room> rooms;
    private LecGroup lecGroup;
    private room hostingRoom;
    private int day;
    private int startingTime;
    private int endingTime;
    private String sectionGroupName;
    private Timetable timetable;

    public timeInTimetable(Staff staff, course course, ArrayList<Branch> branchs, Branch hostingBranch, ArrayList<room> rooms, room hostingRoom, int day, int startingTime, int endingTime, String sectionGroupName, LecGroup lecGroup, Timetable timetable) {
        this.staff = staff;
        this.course = course;
        this.branchs = branchs;
        this.day = day;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.sectionGroupName = sectionGroupName;
        this.lecGroup = lecGroup;
        this.timetable = timetable;
        this.rooms = rooms;
        this.hostingBranch = hostingBranch;
        this.hostingRoom = hostingRoom;
    }

    public timeInTimetable(int id, Staff staff, course course, ArrayList<Branch> branchs, Branch hostingBranch, ArrayList<room> rooms, room hostingRoom, int day, int startingTime, int endingTime, String sectionGroupName, LecGroup lecGroup, Timetable timetable) {
        this.id = id;
        this.staff = staff;
        this.course = course;
        this.branchs = branchs;
        this.hostingBranch = hostingBranch;
        this.rooms = rooms;
        this.lecGroup = lecGroup;
        this.hostingRoom = hostingRoom;
        this.day = day;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.sectionGroupName = sectionGroupName;
        this.timetable = timetable;
    }

    /**
     * @return the staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * @return the course
     */
    public course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(course course) {
        this.course = course;
    }

    /**
     * @return the branchs
     */
    public ArrayList<Branch> getBranchs() {
        return branchs;
    }

    /**
     * @param branchs the branchs to set
     */
    public void setBranchs(ArrayList<Branch> branchs) {
        this.branchs = branchs;
    }

    /**
     * @return the hostingBranch
     */
    public Branch getHostingBranch() {
        return hostingBranch;
    }

    /**
     * @param hostingBranch the hostingBranch to set
     */
    public void setHostingBranch(Branch hostingBranch) {
        this.hostingBranch = hostingBranch;
    }

    /**
     * @return the rooms
     */
    public ArrayList<room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(ArrayList<room> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the lecGroup
     */
    public LecGroup getLecGroup() {
        return lecGroup;
    }

    /**
     * @param lecGroup the lecGroup to set
     */
    public void setLecGroup(LecGroup lecGroup) {
        this.lecGroup = lecGroup;
    }

    /**
     * @return the hostingRoom
     */
    public room getHostingRoom() {
        return hostingRoom;
    }

    /**
     * @param hostingRoom the hostingRoom to set
     */
    public void setHostingRoom(room hostingRoom) {
        this.hostingRoom = hostingRoom;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the startingTime
     */
    public int getStartingTime() {
        return startingTime;
    }

    /**
     * @param startingTime the startingTime to set
     */
    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * @return the endingTime
     */
    public int getEndingTime() {
        return endingTime;
    }

    /**
     * @param endingTime the endingTime to set
     */
    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }

    /**
     * @return the sectionGroupName
     */
    public String getSectionGroupName() {
        return sectionGroupName;
    }

    /**
     * @param sectionGroupName the sectionGroupName to set
     */
    public void setSectionGroupName(String sectionGroupName) {
        this.sectionGroupName = sectionGroupName;
    }

    /**
     * @return the timetable
     */
    public Timetable getTimetable() {
        return timetable;
    }

    /**
     * @param timetable the timetable to set
     */
    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
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
}
