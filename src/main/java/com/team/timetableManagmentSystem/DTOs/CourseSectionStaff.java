/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.timetableManagmentSystem.DTOs;

/**
 *
 * @author Mostafa
 */
public class CourseSectionStaff {

    private course course;
    private Staff staff;
    private Branch branch;
    private int groupNumber;

    public CourseSectionStaff(int courseid, String Coursename, int StaffId, String staffName,
            int branchId, String branchName, int groupNumber) {
        this.course = new course(courseid, Coursename);
        this.staff = new Staff(StaffId, staffName);
        this.branch = new Branch(branchId, branchName);
        this.groupNumber = groupNumber;
    }

    public CourseSectionStaff(int courseid, String Coursename, String code, int labHours, int lectureHours, int semesterId, int semesterNumber, int lectuerGoupId, String lectureGroupName, int sectionGroupId, String sectionGroupName, int studyPlanId, String studyPlanName, int facultyId, String facultyName,
            int StaffId, String staffName, int Stafftypeid, String StaffTypeName, int staffbranchId, String staffbranchName,
            int branchId, String branchName,
            int groupNumber) {
        this.course = new course(courseid, Coursename, code, labHours, lectureHours, semesterId, semesterNumber, lectuerGoupId, lectureGroupName, sectionGroupId, sectionGroupName, studyPlanId, studyPlanName, facultyId, facultyName);
        this.staff = new Staff(StaffId, staffName, Stafftypeid, StaffTypeName, staffbranchId, branchName);
        this.branch = new Branch(branchId, branchName);
        this.groupNumber = groupNumber;
    }

    public CourseSectionStaff() {
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
     * @return the groupNumber
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * @param groupNumber the groupNumber to set
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    

}
