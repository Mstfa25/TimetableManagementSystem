package com.example.demo.academictimetablemanagmentsystem;


public class StudyPlan {

    private int id;
    private String name;
    private Faculty faculty;

    public StudyPlan(int id, String name, int facultyId,String facultyName) {
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
}
