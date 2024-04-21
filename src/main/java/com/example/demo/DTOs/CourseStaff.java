package com.example.demo.DTOs;

public class CourseStaff {

    private course course;
    private Staff staff;
    private Branch branch;

    public CourseStaff(int courseid, String Coursename, String code, int labHours, int lectureHours, int semesterId, int semesterNumber, int lectuerGoupId, String lectureGroupName, int sectionGroupId, String sectionGroupName, int studyPlanId, String studyPlanName, int facultyId, String facultyName,
            int StaffId, String staffName, int Stafftypeid, String StaffTypeName, int staffbranchId, String staffbranchName,
            int branchId, String branchName) {
        this.course = new course(courseid, Coursename, code, labHours, lectureHours, semesterId, semesterNumber, lectuerGoupId, lectureGroupName, sectionGroupId, sectionGroupName, studyPlanId, studyPlanName, facultyId, facultyName);
        this.staff = new Staff(StaffId, staffName, Stafftypeid, StaffTypeName, staffbranchId, branchName);
        this.branch = new Branch(branchId, branchName);
    }

    public CourseStaff() {
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

}
