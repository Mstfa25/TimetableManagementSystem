package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import java.util.ArrayList;

public class LecGroup {

    private int id;
    private String name;
    private LectureGroup lectuerGroup;
    private ArrayList<Branch> branchs;
    private Course course;
    private FreeTime freeTime;
    int remaining;

    public LecGroup(int id) {
        this.id = id;
        branchs = new ArrayList<>();
        freeTime = new FreeTime(1);
    }

    public LecGroup(int id, String name) {
        this.id = id;
        this.name = name;
        branchs = new ArrayList<>();
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

    ArrayList<Room> getRoomsFreeAt(int day, int hour) {
        ArrayList<Room> r = new ArrayList<>();
        for (Branch branch : branchs) {
            Room r1 = branch.getARoomFreeAt(day, hour);
            if (r1 != null) {
                r.add(r1);
            } else {
                return null;
            }
        }
        return r;
    }

    boolean IsAllBranchesFreeAt(int day, int hour) {
        for (int i = 0; i < branchs.size(); i++) {
            if (!branchs.get(i).getFreeTime().isFreeAt(day, hour)) {
                return false;
            }
        }
        return true;
    }

}
