package com.team.timetableManagmentSystem.DTOs;

public class TimeTableNameORIdAndBranch_WithCourses {

    private int id;
    private int branchId;
    private String name;
    private course[] courses;

    public TimeTableNameORIdAndBranch_WithCourses() {
    }

    public TimeTableNameORIdAndBranch_WithCourses(String name, course[] courses) {
        this.name = name;
        this.courses = courses;
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
     * @return the courses
     */
    public course[] getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(course[] courses) {
        this.courses = courses;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }

}
