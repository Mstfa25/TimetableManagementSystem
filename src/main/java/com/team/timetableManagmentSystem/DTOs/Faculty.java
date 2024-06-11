package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class Faculty {

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty() {
    }

    private int id;
    private String name;
    private ArrayList<StudyPlan> studyPlans;

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
     * @return the studyPlans
     */
    public ArrayList<StudyPlan> getStudyPlans() {
        return studyPlans;
    }

    /**
     * @param studyPlans the studyPlans to set
     */
    public void setStudyPlans(ArrayList<StudyPlan> studyPlans) {
        this.studyPlans = studyPlans;
    }
}
