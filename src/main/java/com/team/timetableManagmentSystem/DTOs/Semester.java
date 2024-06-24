package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class Semester {

    private int id;
    private int number;
    private StudyPlan studyPlan;
    private ArrayList<course> courses;

    public Semester(int id, int number, int studyPlanId, String studyPlanName, int facultyId, String facultyName) {
        this.id = id;
        this.number = number;
        this.studyPlan = new StudyPlan(studyPlanId, studyPlanName, facultyId, facultyName);
        this.courses=new ArrayList<>();
    }

    public Semester() {
        this.courses=new ArrayList<>();
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
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the studyPlan
     */
    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    /**
     * @param studyPlan the studyPlan to set
     */
    public void setStudyPlan(StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }

    /**
     * @return the courses
     */
    public ArrayList<course> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(ArrayList<course> courses) {
        this.courses = courses;
    }

}
