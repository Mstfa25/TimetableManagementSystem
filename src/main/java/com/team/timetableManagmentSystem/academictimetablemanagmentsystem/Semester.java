package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import java.util.ArrayList;

public class Semester {

    private int id;
    private int number;
    private StudyPlan studyPlan;
    private ArrayList<Course> courses;
    private ArrayList<Branch> branchs;
    private FreeTime freeTime;
    private boolean splitted;
    private int[] numberOfRoomsInDay;

    public Semester(int id) {
        this.id = id;
        courses = new ArrayList<>();
        branchs = new ArrayList<>();
        freeTime = new FreeTime(1);
    }

    public Semester(int id, int number, int studyPlanId, String studyPlanName, int facultyId, String facultyName) {
        this.id = id;
        this.number = number;
        this.studyPlan = new StudyPlan(studyPlanId, studyPlanName, facultyId, facultyName);
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
     * @return the courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
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
     * @return the splitted
     */
    public boolean isSplitted() {
        return splitted;
    }

    /**
     * @param splitted the splitted to set
     */
    public void setSplitted(boolean splitted) {
        this.splitted = splitted;
    }

    void setTheNumberOfRoomsInDay() {
        setNumberOfRoomsInDay(new int[6]);
        for (int i = 0; i < 6; i++) {
            getNumberOfRoomsInDay()[i] = Integer.MAX_VALUE;
        }
        for (Branch branch : branchs) {
            getNumberOfRoomsInDay()[0] = Integer.min(branch.getNumberOfRoomsInDay()[0], getNumberOfRoomsInDay()[0]);
        }
        for (int i = 1; i < 6; i++) {
            getNumberOfRoomsInDay()[i] = getNumberOfRoomsInDay()[0];
        }
    }

    /**
     * @return the numberOfRoomsInDay
     */
    public int[] getNumberOfRoomsInDay() {
        return numberOfRoomsInDay;
    }

    /**
     * @param numberOfRoomsInDay the numberOfRoomsInDay to set
     */
    public void setNumberOfRoomsInDay(int[] numberOfRoomsInDay) {
        this.numberOfRoomsInDay = numberOfRoomsInDay;
    }

    boolean[] getIfWorkingDay() {
        boolean[] days = new boolean[6];
        for (int i = 0; i < 6; i++) {
            if (freeTime.getNumberOfFreeHouresInDay(i) < 9) {
                days[i] = true;
            }
        }
        return days;
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
