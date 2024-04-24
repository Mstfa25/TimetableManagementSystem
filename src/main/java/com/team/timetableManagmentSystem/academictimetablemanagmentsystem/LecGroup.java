package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import java.util.ArrayList;

/**
 * used in the operation of creating timeTable
 * @data_field id for representing the lecGroup 
 * @data_field name for representing the lecGroup name (ex:a,b)
 * @data_field lectuerGroup for representing the lectuerGroup which the lecGroup in
 * @data_field branches is for representing the branches in the lecGroup
 * @data_field freeTime for representing the freeTime for the lecGroup
 * @data_field remaining the number of remaining hours for the lecGroup 
 * @author Mostafa
 */
public class LecGroup {

    private int id;
    private String name;
    private LectureGroup lectuerGroup;
    private ArrayList<Branch> branches;
    private Course course;
    private FreeTime freeTime;
    int remaining;

    public LecGroup(int id) {
        this.id = id;
        branches = new ArrayList<>();
        freeTime = new FreeTime(1);
    }

    public LecGroup(int id, String name) {
        this.id = id;
        this.name = name;
        branches = new ArrayList<>();
        freeTime = new FreeTime(1);

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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
     * @return the lectuerGroup
     */
    public LectureGroup getLectuerGroup() {
        return lectuerGroup;
    }

    /**
     * @param lectuerGroup the lectuerGroup to set
     */
    public void setLectuerGroup(LectureGroup lectuerGroup) {
        this.lectuerGroup = lectuerGroup;
    }

    /**
     * @return the branches
     */
    public ArrayList<Branch> getBranchs() {
        return branches;
    }

    /**
     * @param branchs the branches to set
     */
    public void setBranchs(ArrayList<Branch> branchs) {
        this.branches = branchs;
    }

    /**
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
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

    /**
     * 
     * @param day rooms free in
     * @param hour the rooms free in 
     * @return array list of rooms the is free at as specific day and hour 
     */
    ArrayList<Room> getRoomsFreeAt(int day, int hour) {
        ArrayList<Room> r = new ArrayList<>();
        for (Branch branch : branches) {
            Room r1 = branch.getARoomFreeAt(day, hour);
            if (r1 != null) {
                r.add(r1);
            } else {
                return null;
            }
        }
        return r;
    }

    /**
     * 
     * @param day
     * @param hour
     * @return if all branches free at specific time day and hour
     */
    boolean IsAllBranchesFreeAt(int day, int hour) {
        for (int i = 0; i < branches.size(); i++) {
            if (!branches.get(i).getFreeTime().isFreeAt(day, hour)) {
                return false;
            }
        }
        return true;
    }

}
