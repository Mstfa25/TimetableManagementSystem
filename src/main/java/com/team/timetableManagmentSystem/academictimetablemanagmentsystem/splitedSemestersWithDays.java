package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import java.util.ArrayList;

public class splitedSemestersWithDays {

    private ArrayList<ArrayList<Course>> courses;

    public splitedSemestersWithDays() {
        courses = new ArrayList<>();
        courses.add(new ArrayList<>());
        courses.add(new ArrayList<>());
        courses.add(new ArrayList<>());
        courses.add(new ArrayList<>());
        courses.add(new ArrayList<>());
        courses.add(new ArrayList<>());
    }

    void add(ArrayList<Course> courses, int day) {
        for (Course course : courses) {

            this.getCourses().get(day).add(course);
            if (course.getStaff().getIssymmetric()[day]) {
                this.getCourses().get((day + 3) % 6).add(course);
            }

        }

    }

    /**
     * @return the courses
     */
    public ArrayList<ArrayList<Course>> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(ArrayList<ArrayList<Course>> courses) {
        this.courses = courses;
    }
}
