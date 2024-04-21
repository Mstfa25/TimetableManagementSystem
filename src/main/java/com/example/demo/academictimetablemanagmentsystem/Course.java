package com.example.demo.academictimetablemanagmentsystem;

import com.example.demo.database.connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Course {

    private int id;
    private int lectureHours;
    private int labHours;
    private StudyPlan studyPlan;
    private Faculty faculty;
    private Staff staff;
    private Semester semester;
    private String name;
    private String code;
    private LectureGroup lectureGoup;
    private SectionGroup group;
    private ArrayList<LecGroup> lecGroups;
    private ArrayList<Integer> roomsTypesForSection;

    public Course(int id) {
        this.id = id;
    }

    public Course(int id,
            String name,
            String code,
            int labHours,
            int lectureHours,
            int semesterId,
            int semesterNumber,
            int lectuerGoupId,
            String lectureGroupName,
            int sectionGroupId,
            String sectionGroupName,
            int studyPlanId,
            String studyPlanName,
            int facultyId,
            String facultyName) {
        
        this.id = id;
        this.name = name;
        this.code = code;
        this.labHours = labHours;
        this.lectureHours = lectureHours;
        this.lectureGoup = new LectureGroup(
                lectuerGoupId,
                lectureGroupName);
        this.group = new SectionGroup(
                sectionGroupId,
                sectionGroupName);
        this.semester = new Semester(
                semesterId,
                semesterNumber,
                studyPlanId,
                studyPlanName,
                facultyId,
                facultyName);
        this.studyPlan = semester.getStudyPlan();
        this.faculty = studyPlan.getFaculty();
    }

    public Course(int id,
            String name) {
        this.id = id;
        this.name = name;
    }

    public Course(int id,Staff staff) {
        this.id=id;
        this.staff=staff;
    }
    
    

    public Course(int id,
            String name,
            int semesterId) {
        this.id = id;
        this.name = name;
        semester=new Semester(
                semesterId);
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    void getTheDataForLectureTimetable() {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select "
                    + "lecHours,"
                    + "SemesterId,"
                    + "lectureGroupId"
                    + " from courses "
                    + "where id = " + id);
            if (rs.next()) {
                setLectureHours(
                        rs.getInt("lecHours")
                );
                semester = new Semester(
                        rs.getInt("SemesterId")
                );
                setLectureGoup(new LectureGroup(
                        rs.getInt("lectureGroupId")
                ));
                getLectureGoup()
                        .getTheLecGroups();
                lecGroups = getLectureGoup()
                        .getLecGroups();

            }
            rs = conn.select("select "
                    + "staffId,"
                    + "BranchId "
                    + "from coursesstaff "
                    + "where courseId = " + id + 
                    " and staffId in (select"
                            + " id "
                            + "from staff "
                            + "where JobTypeId = 1)");
            if (rs.next()) {
                staff = new Staff(
                        rs.getInt(1),
                        new Branch(
                                rs.getInt(2)
                        )
                );
                staff
                        .getBranch()
                        .getHostingRooms();
                staff
                        .getTheFreeTime();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
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
     * @return the lectuerGroup
     */
    public LectureGroup getLectuerGroup() {
        return getLectureGoup();
    }

    /**
     * @param lectuerGroup the lectuerGroup to set
     */
    public void setLectuerGroup(LectureGroup lectuerGroup) {
        this.setLectureGoup(lectuerGroup);
    }

    /**
     * @return the sectionGroup
     */
    public SectionGroup getSectionGroup() {
        return getGroup();
    }

    /**
     * @param sectionGroup the sectionGroup to set
     */
    public void setSectionGroup(SectionGroup sectionGroup) {
        this.setGroup(sectionGroup);
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
     * @return the lecGroups
     */
    public ArrayList<LecGroup> getLecGroups() {
        return lecGroups;
    }

    /**
     * @param lecGroups the lecGroups to set
     */
    public void setLecGroups(ArrayList<LecGroup> lecGroups) {
        this.lecGroups = lecGroups;
    }

    /**
     * @return the roomsTypesForSection
     */
    public ArrayList<Integer> getRoomsTypesForSection() {
        if (roomsTypesForSection == null) {
            roomsTypesForSection = new ArrayList<>();
        }
        return roomsTypesForSection;
    }

    /**
     * @param roomsTypesForSection the roomsTypesForSection to set
     */
    public void setRoomsTypesForSection(ArrayList<Integer> roomsTypesForSection) {
        this.roomsTypesForSection = roomsTypesForSection;
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

    /**
     * @return the lectureGoup
     */
    public LectureGroup getLectureGoup() {
        return lectureGoup;
    }

    /**
     * @param lectureGoup the lectureGoup to set
     */
    public void setLectureGoup(LectureGroup lectureGoup) {
        this.lectureGoup = lectureGoup;
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
