package com.team.timetableManagmentSystem.DTOs;

public class course {

    private int id;
    private String name;
    private String code;
    private int labHours;
    private int lectureHours;
    private LectureGroup lectuerGoup;
    private SectionGroup group;
    private Semester semester;
    private StudyPlan studyPlan;
    private Faculty faculty;

    public course(int id, String name, String code, int labHours, int lectureHours, int semesterId, int semesterNumber, int lectuerGoupId, String lectureGroupName, int sectionGroupId, String sectionGroupName, int studyPlanId, String studyPlanName, int facultyId, String facultyName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.labHours = labHours;
        this.lectureHours = lectureHours;
        this.lectuerGoup = new LectureGroup(lectuerGoupId, lectureGroupName);
        this.group = new SectionGroup(sectionGroupId, sectionGroupName);
        this.semester = new Semester(semesterId, semesterNumber, studyPlanId, studyPlanName, facultyId, facultyName);
        this.studyPlan = semester.getStudyPlan();
        this.faculty = studyPlan.getFaculty();
    }

    public course(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    public course(int id,String name,String code) {
        this.id=id;
        this.name=name;
        this.code=code;
    }
    

    public course(int id) {
        this.id = id;
    }
    
    

    public course() {
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the labHours
     */
    public int getLabHours() {
        return labHours;
    }

    /**
     * @param labHours the labHours to set
     */
    public void setLabHours(int labHours) {
        this.labHours = labHours;
    }

    /**
     * @return the lectureHours
     */
    public int getLectureHours() {
        return lectureHours;
    }

    /**
     * @param lectureHours the lectureHours to set
     */
    public void setLectureHours(int lectureHours) {
        this.lectureHours = lectureHours;
    }

    /**
     * @return the lectuerGoup
     */
    public LectureGroup getLectuerGoup() {
        return lectuerGoup;
    }

    /**
     * @param lectuerGoup the lectuerGoup to set
     */
    public void setLectuerGoup(LectureGroup lectuerGoup) {
        this.lectuerGoup = lectuerGoup;
    }

    /**
     * @return the group
     */
    public SectionGroup getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(SectionGroup group) {
        this.group = group;
    }

    /**
     * @return the semester
     */
    public Semester getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(Semester semester) {
        this.semester = semester;
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
