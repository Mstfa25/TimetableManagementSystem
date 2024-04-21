package com.example.demo.DTOs;

public class Semester {

    private int id;
    private int number;
    private StudyPlan studyPlan;

    public Semester(int id, int number, int studyPlanId,String studyPlanName,int facultyId,String facultyName) {
        this.id = id;
        this.number = number;
        this.studyPlan = new StudyPlan(studyPlanId,studyPlanName,facultyId,facultyName);
    }

    public Semester() {
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

}
