package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class StudyPlan {

    private int id;
    private String name;
    private Faculty faculty;
    private ArrayList<Semester> semesters;

    public StudyPlan(int id, String name, int facultyId, String facultyName) {
        this.id = id;
        this.name = name;
        this.faculty = new Faculty(facultyId, facultyName);
    }

    public StudyPlan() {
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
     * @return the faculty
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * @return the semesters
     */
    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    /**
     * @param semesters the semesters to set
     */
    public void setSemesters(ArrayList<Semester> semesters) {
        this.semesters = semesters;
    }
}
