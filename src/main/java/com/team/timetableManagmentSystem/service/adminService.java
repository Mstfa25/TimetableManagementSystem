package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.*;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Course;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Room;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.TimeInTimetable;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminService {

    @Autowired
    private connection conn;

    public void insertNewBranch(String name) {
        conn.execute("insert into branch (name) value ('" + name + "')");
        conn.close();
    }

    public void insertNewBranches(String values) {
        conn.execute("insert into branch (name) values " + values);
        conn.close();
    }

    public ArrayList<Branch> getAllBranches() {
        try {
            ArrayList<Branch> branchs = new ArrayList<>();
            ResultSet rs = conn.select("select * from branch");
            while (rs.next()) {
                branchs.add(new Branch(rs.getInt(1), rs.getString(2)));
            }

            return branchs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void editBranchName(int id, String newName) {
        conn.execute("update branch set name ='" + newName + "' where id =" + id);
        conn.close();
    }

    public void removeBranch(int id) {
        conn.execute("delete from branch where id =" + id);
        conn.close();
    }

    public ArrayList<room> GetallRoomsInAllBranches() {
        try {
            ArrayList<room> rooms = new ArrayList<>();

            ResultSet rs = conn.select("select rooms.id,rooms.name,rooms.capacity,"
                    + "rooms.TypeId,roomstypes.RoomType,rooms.branchId,"
                    + "branch.name from rooms"
                    + " inner join roomstypes on rooms.TypeId=roomstypes.id "
                    + "inner join branch on rooms.branchId=branch.id; ");
            while (rs.next()) {
                rooms.add(new room(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getString(7)));
            }
            return rooms;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<room> GetallRoomsInoneBranch(int branchId) {
        try {
            ArrayList<room> rooms = new ArrayList<>();

            ResultSet rs = conn.select("select rooms.id,rooms.name,rooms.capacity,"
                    + "rooms.TypeId,roomstypes.RoomType,rooms.branchId,"
                    + "branch.name from rooms"
                    + " inner join roomstypes on rooms.TypeId=roomstypes.id "
                    + "inner join branch on rooms.branchId=branch.id where rooms.branchid = " + branchId);
            while (rs.next()) {
                rooms.add(new room(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getString(7)));
            }
            return rooms;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void removeRoom(int roomId) {
        conn.execute("delete from branch where id =" + roomId);
        conn.close();
    }

    public void addRoom(String name, int capacity, int typeId, int branchId) {
        conn.execute("insert into rooms (name,capacity,typeId,branchId) value ('" + name + "'," + capacity + "'," + typeId + "'," + branchId + ")");
        conn.close();
    }

    public void AddRooms(String values) {
        conn.execute("insert into rooms (name,capacity,typeId,branchId) values " + values);
        conn.close();
    }

    public void addStaff(String name, int jobTypeId, int branchId) {
        conn.execute("insert into staff (name,JobTypeId,branchId) value ('" + name + "'," + jobTypeId + "," + branchId + ")");
        conn.close();
    }

    public void addStaffs(String values) {
        conn.execute("insert into staff (name,JobTypeId,branchId) values " + values);
        conn.close();
    }

    public ArrayList<Staff> getAllStaffInAllBranches() {
        try {
            ArrayList<Staff> staffs = new ArrayList<>();
            ResultSet rs = conn.select("   select staff.id,staff.name,staff.JobTypeId,jobtype.name as jobtypename,staff.branchId,branch.name as branchname from staff inner join jobtype on jobtype.Id=staff.JobTypeId inner join branch on branch.id=staff.branchId");
            while (rs.next()) {
                staffs.add(new Staff(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return staffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Staff> getAllStaffInOneBranch(int branchId) {
        try {
            ArrayList<Staff> staffs = new ArrayList<>();
            ResultSet rs = conn.select("   select staff.id,staff.name,staff.JobTypeId,jobtype.name as jobtypename,staff.branchId,branch.name as branchname from staff inner join jobtype on jobtype.Id=staff.JobTypeId inner join branch on branch.id=staff.branchId where staff.branchId = " + branchId);
            while (rs.next()) {
                staffs.add(new Staff(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return staffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Staff> getAllStaffInAllBranchesWithType(int type) {
        try {
            ArrayList<Staff> staffs = new ArrayList<>();
            ResultSet rs = conn.select("   select staff.id,staff.name,staff.JobTypeId,jobtype.name as jobtypename,staff.branchId,branch.name as branchname from staff inner join jobtype on jobtype.Id=staff.JobTypeId inner join branch on branch.id=staff.branchId where staff.JobTypeId=" + type);
            while (rs.next()) {
                staffs.add(new Staff(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return staffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Staff> getAllStaffInAllBranchesWithTypeInOneBranch(int branchId, int type) {
        try {
            ArrayList<Staff> staffs = new ArrayList<>();
            ResultSet rs = conn.select("   select staff.id,staff.name,staff.JobTypeId,jobtype.name as jobtypename,staff.branchId,branch.name as branchname from staff inner join jobtype on jobtype.Id=staff.JobTypeId inner join branch on branch.id=staff.branchId where staff.JobTypeId=" + type + " and staff.branchId = " + branchId);
            while (rs.next()) {
                staffs.add(new Staff(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return staffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void EditStaffName(int staffId, String staffName) {
        conn.execute("update staff set name = '" + staffName + "' where id = " + staffId);
        conn.close();
    }

    public void editStaffType(int staffId, int staffType) {
        conn.execute("upadte staff set JobtypeId = " + staffType + " where id = " + staffId);
        conn.close();
    }

    public void editStaffBranch(int staffId, int branchId) {
        conn.execute("update staff set branchId = " + branchId + " where id = " + staffId);
        conn.close();
    }

    public void removeStaff(int staffId) {
        conn.execute("delete from staff where id = " + staffId);
        conn.close();
    }

    public void addFaculty(String name) {
        conn.execute("insert into faculty (name) value +('" + name + "')");
        conn.close();
    }

    public ArrayList<Faculty> getFacultys() {
        try {
            ArrayList<Faculty> facultys = new ArrayList<>();
            ResultSet rs = conn.select("select * from faculty");
            while (rs.next()) {
                facultys.add(new Faculty(rs.getInt(1), rs.getString(2)));
            }
            return facultys;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void removeFaculty(int id) {
        conn.execute("delete from faculty where id = " + id);
        conn.close();
    }

    public void EditFacultyName(int facultyId, String facultyName) {
        conn.execute("update faculty set name ='" + facultyName + "' where id = " + facultyId);
        conn.close();
    }

    public void insertStudyPlan(String name, int facultyId) {
        conn.execute("insert into studyPlan (name,facultyId) value ('" + name + "', " + facultyId + ")");
        conn.close();
    }

    public void editStudyPlanName(int id, String name) {
        conn.execute("update studyPlan set name = '" + name + "' where id =" + id);
        conn.close();
    }

    public void editStudyPlanfaculty(int id, int facultyId) {
        conn.execute("update studyPlan set FacultyId = " + facultyId + " where id =" + id);
        conn.close();
    }

    public void removeStudyPlans(int id) {
        conn.execute("delete studyPlan where id = " + id);
        conn.close();
    }

    public ArrayList<StudyPlan> getAllStudyPlans() {
        try {
            ArrayList<StudyPlan> studyPlans = new ArrayList<>();
            ResultSet rs = conn.select("select studyplan.id,studyplan.name,studyplan.facultyId,faculty.name from studyplan inner join faculty on studyplan.facultyId=faculty.id");
            while (rs.next()) {
                studyPlans.add(new StudyPlan(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            return studyPlans;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<StudyPlan> getStudyPlansInFaculty(int facultyId) {
        try {
            ArrayList<StudyPlan> studyPlans = new ArrayList<>();
            ResultSet rs = conn.select("select studyplan.id,studyplan.name,studyplan.facultyId,faculty.name from studyplan inner join faculty on studyplan.facultyId=faculty.id where studyplan.facultyId = " + facultyId);
            while (rs.next()) {
                studyPlans.add(new StudyPlan(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            return studyPlans;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void addSemester(int number, int studyPlanId) {
        conn.execute("insert into semester (number,ptudyPlanId) value (" + number + "," + studyPlanId + ")");
        conn.close();
    }

    public void editSemesterNumber(int id, int number) {
        conn.execute("update Semester set number = " + number + " where id = " + id);
        conn.close();
    }

    public void editSemesterStudyPlan(int id, int StudyPlanId) {
        conn.execute("update Semester set StudyPlanId = " + StudyPlanId + " where id = " + id);
        conn.close();
    }

    public void removeSemester(int id) {
        conn.execute("delete Semester where id = " + id);
        conn.close();
    }

    public ArrayList<Semester> getAllSemesters() {
        try {
            ArrayList<Semester> semesters = new ArrayList<>();
            ResultSet rs = conn.select("select semester.id,semester.number,semester.StudyPlanId,studyplan.name,faculty.id,faculty.name from semester inner join studyplan on studyplan.id=semester.StudyPlanId inner join faculty on studyplan.facultyId=faculty.id");
            while (rs.next()) {
                semesters.add(new Semester(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return semesters;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Semester> getAllSemestersInStudyPlan(int studyPlanId) {
        try {
            ArrayList<Semester> semesters = new ArrayList<>();
            ResultSet rs = conn.select("select semester.id,semester.number,semester.StudyPlanId,studyplan.name,faculty.id,faculty.name from semester inner join studyplan on studyplan.id=semester.StudyPlanId inner join faculty on studyplan.facultyId=faculty.id where studyPlanId = " + studyPlanId);
            while (rs.next()) {
                semesters.add(new Semester(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return semesters;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Semester> getAllSemestersInFaculty(int facultyId) {
        try {
            ArrayList<Semester> semesters = new ArrayList<>();
            ResultSet rs = conn.select("select semester.id,semester.number,semester.StudyPlanId,studyplan.name,faculty.id,faculty.name from semester inner join studyplan on studyplan.id=semester.StudyPlanId inner join faculty on studyplan.facultyId=faculty.id where studyplan.facultyId = " + facultyId);
            while (rs.next()) {
                semesters.add(new Semester(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return semesters;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }
//----------------------------------------------------------------------------------------------//

    public void addLectureGroup(String name) {
        conn.execute("insert into lectureGroup (name) value ('" + name + "')");
        conn.close();
    }

    public void editLectuerGroupName(int id, String name) {
        conn.execute("update lectureGroup set name = '" + name + "' where id = " + id);
        conn.close();
    }

    public void removeLectuerGroup(int id) {
        conn.select("delete from lectureGroup where id = " + id);
    }

    public ArrayList<LectureGroup> getAllLectuerGroups() {
        try {
            ArrayList<LectureGroup> lectuerGoups = new ArrayList<>();
            ResultSet rs = conn.select("select * from lectuerGroup");
            while (rs.next()) {
                lectuerGoups.add(new LectureGroup(rs.getInt(1), rs.getString(1)));
            }
            return lectuerGoups;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void addLecGroupInALectuerGroup(int lectureGroupId, String name) {
        conn.execute("insert into lecGroups (name,lectureGroupId) value ('" + name + "'," + lectureGroupId + ")");
        conn.close();
    }

    public void editLecGroupInALectuerGroupName(int lecGroupId, String name) {
        conn.execute("update lecGroup set name = '" + name + "' where id = " + lecGroupId);
        conn.close();
    }

    public void editLecGroupInALectuerGroupLectuerGroup(int Id, int lectuerGroupId) {
        conn.execute("update lecGroup set lectuerGroupId = " + lectuerGroupId + " where id = " + Id);
        conn.close();
    }

    public void removeLecGroupInALectuerGroupLectuerGroup(int Id) {
        conn.execute("delete from lecGroup where id = " + Id);
        conn.close();
    }

    /*--------------------------------------------------------------------------------------------*/
    public void addBranchInALecGroup(int BranchId, int lecGroupId) {
        conn.execute("insert into lecgroupbranches  value (" + lecGroupId + "," + BranchId + ")");
        conn.close();
    }

    public void removeBranchFromLecGroup(int branchId, int lecGroupId) {
        conn.execute("delete from lecgroupbranches where lecGroupId = " + lecGroupId + " and branchId = " + branchId);
        conn.close();
    }

    public ArrayList<Branch> getAllBranchesInLecGroup(int lecGroupId) {
        try {
            LecGroup lecGroup = new LecGroup();
            lecGroup.setBranchs(new ArrayList<>());
            ResultSet rs = conn.select("select branchId from LecGroupBranches where lecGroupId = " + lecGroupId);
            while (rs.next()) {
                lecGroup.getBranchs().add(new Branch(rs.getInt(1)));
            }
            return lecGroup.getBranchs();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void addSectionGroup(String name) {
        conn.execute("insert into sectionsnumberofgroups (name) value ('" + name + "')");
        conn.close();
    }

    public void editSectionGroupName(int id, String name) {
        conn.execute("update sectionsnumberofgroups set name = '" + name + "' where id = " + id);
        conn.close();
    }

    public void removeSectionGroup(int id) {
        conn.select("delete from sectionsnumberofgroups where id = " + id);
        conn.close();
    }

    public ArrayList<SectionGroup> getAllSectionGroups() {
        try {
            ArrayList<SectionGroup> sectionGroup = new ArrayList<>();
            ResultSet rs = conn.select("select * from section");
            while (rs.next()) {
                sectionGroup.add(new SectionGroup(rs.getInt(1), rs.getString(1)));
            }
            return sectionGroup;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void insertBranchInSectionGroup(int branchId, int sectionsnumberofgroupsId, int numberOfGroups) {
        conn.execute("insert into section sectionsfgroups (sectionsnumberofgroupsId,branchId,numberOfGroups) value (" + sectionsnumberofgroupsId + "," + branchId + "," + numberOfGroups + ")");
        conn.close();
    }

    public void editBranchSectionGroupnumberOfGroups(int branchId, int sectionsnumberofgroupsId, int numberOfGroups) {
        conn.execute("edit sectionsfgroups set  numberOfGroups = " + numberOfGroups + " where bracnhId = " + branchId + " and sectionsnumberofgroupsId = " + sectionsnumberofgroupsId);
        conn.close();
    }

    public void changeBranchSectionGroup(int branchId, int sectionsnumberofgroupsId, int newsectionNumberOfGroups) {
        conn.execute("edit sectionsfgroups set  sectionsnumberofgroupsId = " + newsectionNumberOfGroups + " where bracnhId = " + branchId + " and sectionsnumberofgroupsId = " + sectionsnumberofgroupsId);
        conn.close();
    }

    public void removeBranchFromSectionGroup(int branchId, int sectionsnumberofgroupsId) {
        conn.execute("delete from sectionsfgroups where branchId = " + branchId + " and sectionsnumberofgroupsId = " + sectionsnumberofgroupsId);
        conn.close();

    }

    public sectionGroupBranchs getSectionGroupBranchs(int sectionGroupId) {
        try {
            sectionGroupBranchs s = new sectionGroupBranchs();
            s.setBranchWithNumberOfSectionGroupses(new ArrayList<>());
            ResultSet rs = conn.select("select sectionsfgroups.branchId,branch.name,sectionsfgroups.numberOfGroups from sectionsfgroups inner join branch on branch.id=sectionsfgroups.branchId where sectionsnumberofgroupsId = " + sectionGroupId);
            while (rs.next()) {
                s.getBranchWithNumberOfSectionGroupses().add(new BranchWithNumberOfSectionGroups(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            return s;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void insertCourse(String name, String code, int lecHour, int labHours, int SemesterId, int StudyPlanId, int facultyId) {
        conn.execute("insert into courses (name,code,lecHours,labHours,semesterId,facultyId,studyplanId,lectureGroupId,sectionsnumberofgroupsId) values('" + name + "','" + code + "'," + lecHour + "," + labHours + "," + SemesterId + "," + facultyId + "," + StudyPlanId + ",0,0)");
        conn.close();
    }

    public void setLecGroupIdForCourse(int courseId, int lecGroupId) {
        conn.execute("update courses set lectureGroupId = " + lecGroupId + " where id = " + courseId);
        conn.close();
    }

    public void setSectionGroupIdForCourse(int courseId, int SectionGroupId) {
        conn.execute("update courses set sectionsnumberofgroupsId = " + SectionGroupId + " where id = " + courseId);
        conn.close();
    }

    public ArrayList<course> getALLCourses() {
        try {
            ArrayList<course> courses = new ArrayList<>();
            ResultSet rs = conn.select("select courses.id,"
                    + "courses.name,"
                    + "courses.code,"
                    + "courses.labHours,"
                    + "courses.lecHours,"
                    + "courses.SemesterId,"
                    + "semester.number,"
                    + "courses.lectureGroupId,"
                    + "lecturegroups.name,"
                    + "courses.SectionsNumberOfGroupsID,"
                    + "   sectionsnumberofgroups.name,"
                    + "courses.StudyplanId,"
                    + "studyplan.name,"
                    + "courses.facultyId,"
                    + "faculty.name from courses "
                    + "inner join sectionsnumberofgroups on courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id inner join lecturegroups on lecturegroups.id=courses.lectureGroupId  inner join semester on semester.id=courses.SemesterId inner join studyplan on studyplan.id=courses.StudyplanId inner join faculty on faculty.id=courses.facultyId");
            while (rs.next()) {
                courses.add(new course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15)));
            }
            return courses;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<course> getALLCoursesInSemester(int semesterId) {
        try {
            ArrayList<course> courses = new ArrayList<>();
            ResultSet rs = conn.select("select courses.id,courses.name,courses.code,courses.labHours,courses.lecHours,courses.SemesterId,semester.number,courses.lectureGroupId,lecturegroups.name,courses.SectionsNumberOfGroupsID,   sectionsnumberofgroups.name,courses.StudyplanId,studyplan.name,courses.facultyId,faculty.name from courses inner join sectionsnumberofgroups on courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id inner join lecturegroups on lecturegroups.id=courses.lectureGroupId  inner join semester on semester.id=courses.SemesterId inner join studyplan on studyplan.id=courses.StudyplanId inner join faculty on faculty.id=courses.facultyId where courses.semesterId = " + semesterId);
            while (rs.next()) {
                courses.add(new course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15)));
            }
            return courses;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<course> getALLCoursesInFaculty(int FacultyId) {
        try {
            ArrayList<course> courses = new ArrayList<>();
            ResultSet rs = conn.select("select courses.id,courses.name,courses.code,courses.labHours,courses.lecHours,courses.SemesterId,semester.number,courses.lectureGroupId,lecturegroups.name,courses.SectionsNumberOfGroupsID,   sectionsnumberofgroups.name,courses.StudyplanId,studyplan.name,courses.facultyId,faculty.name from courses inner join sectionsnumberofgroups on courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id inner join lecturegroups on lecturegroups.id=courses.lectureGroupId  inner join semester on semester.id=courses.SemesterId inner join studyplan on studyplan.id=courses.StudyplanId inner join faculty on faculty.id=courses.facultyId where courses.facultyId = " + FacultyId);
            while (rs.next()) {
                courses.add(new course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15)));
            }
            return courses;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<course> getALLCoursesInStudyPlan(int studyPlanId) {
        try {
            ArrayList<course> courses = new ArrayList<>();
            ResultSet rs = conn.select("select courses.id,courses.name,courses.code,courses.labHours,courses.lecHours,courses.SemesterId,semester.number,courses.lectureGroupId,lecturegroups.name,courses.SectionsNumberOfGroupsID,   sectionsnumberofgroups.name,courses.StudyplanId,studyplan.name,courses.facultyId,faculty.name from courses inner join sectionsnumberofgroups on courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id inner join lecturegroups on lecturegroups.id=courses.lectureGroupId  inner join semester on semester.id=courses.SemesterId inner join studyplan on studyplan.id=courses.StudyplanId inner join faculty on faculty.id=courses.facultyId where courses.studyPlanId = " + studyPlanId);
            while (rs.next()) {
                courses.add(new course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15)));
            }
            return courses;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public void insertCourseStaff(int courseId, int staffId, int branchId) {
        conn.execute("insert into courseStaff (courseId,staffId,branchId) value (" + courseId + "," + staffId + "," + branchId + ")");
        conn.close();
    }

    public void deleteCourseStaff(int courseId, int staffId, int branchId) {
        conn.execute("delete from courseStaff where courseId = " + courseId + " and staffId = " + staffId + " and branchId = " + branchId + ")");
        conn.close();
    }

    public void editCourseForCourseStaffInABranch(int courseId, int staffId, int branchId) {
        conn.execute("update courseStaff set courseId = " + courseId + " where staffId = " + staffId + " and branchId = " + branchId + ")");
        conn.close();
    }

    public void editBranchForCourseStaffInCourse(int courseId, int staffId, int branchId) {
        conn.execute("update courseStaff set branchId = " + branchId + " where staffId = " + staffId + " and courseId = " + courseId + ")");
        conn.close();
    }

    public void editCourseStaffInCourseForABranch(int courseId, int staffId, int branchId) {
        conn.execute("update courseStaff set staffId = " + staffId + " where courseId= " + courseId + " and branchId = " + branchId + ")");
        conn.close();
    }

    public ArrayList<CourseStaff> getAllCourseStaff() {
        try {
            ArrayList<CourseStaff> courseStaffs = new ArrayList<>();
            ResultSet rs = conn.select("SELECT courses.id as courseId, courses.name as courseName,courses.code, courses.labHours, lecHours, courses.SemesterId, semester.number, courses.lectureGroupId, lecturegroups.name, courses.SectionsNumberOfGroupsID, sectionsnumberofgroups.name, courses.StudyplanId, studyplan.name, courses.facultyId, faculty.name, coursesstaff.staffId,staff.name,staff.JobTypeId,jobtype.name,staff.branchId,branch1.name,branch2.id,branch2.name   FROM coursesstaff  INNER JOIN courses ON courses.id=coursesstaff.courseId  INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id  INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId    INNER JOIN semester ON semester.id=courses.SemesterId   INNER JOIN studyplan ON studyplan.id=courses.StudyplanId   INNER JOIN faculty ON faculty.id=courses.facultyId  INNER JOIN staff ON staff.id=coursesstaff.staffId  INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId   INNER JOIN branch AS branch1 ON branch1.id=staff.branchId  INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId");
            while (rs.next()) {
                courseStaffs.add(new CourseStaff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getInt(20), rs.getString(21), rs.getInt(22), rs.getString(23)));

            }
            return courseStaffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<CourseStaff> getAllCourseStaffForACourse(int courseId) {
        try {
            ArrayList<CourseStaff> courseStaffs = new ArrayList<>();
            ResultSet rs = conn.select("SELECT courses.id as courseId, courses.name as courseName,courses.code, courses.labHours, lecHours, courses.SemesterId, semester.number, courses.lectureGroupId, lecturegroups.name, courses.SectionsNumberOfGroupsID, sectionsnumberofgroups.name, courses.StudyplanId, studyplan.name, courses.facultyId, faculty.name, coursesstaff.staffId,staff.name,staff.JobTypeId,jobtype.name,staff.branchId,branch1.name,branch2.id,branch2.name   FROM coursesstaff  INNER JOIN courses ON courses.id=coursesstaff.courseId  INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id  INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId    INNER JOIN semester ON semester.id=courses.SemesterId   INNER JOIN studyplan ON studyplan.id=courses.StudyplanId   INNER JOIN faculty ON faculty.id=courses.facultyId  INNER JOIN staff ON staff.id=coursesstaff.staffId  INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId   INNER JOIN branch AS branch1 ON branch1.id=staff.branchId  INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId where coursesstaff.courseId = " + courseId);
            while (rs.next()) {
                courseStaffs.add(new CourseStaff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getInt(20), rs.getString(21), rs.getInt(22), rs.getString(23)));

            }
            return courseStaffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<CourseStaff> getAllCourseStaffInSemester(int semesterId) {
        try {
            ArrayList<CourseStaff> courseStaffs = new ArrayList<>();
            ResultSet rs = conn.select("SELECT courses.id as courseId, courses.name as courseName,courses.code, courses.labHours, lecHours, courses.SemesterId, semester.number, courses.lectureGroupId, lecturegroups.name, courses.SectionsNumberOfGroupsID, sectionsnumberofgroups.name, courses.StudyplanId, studyplan.name, courses.facultyId, faculty.name, coursesstaff.staffId,staff.name,staff.JobTypeId,jobtype.name,staff.branchId,branch1.name,branch2.id,branch2.name   FROM coursesstaff  INNER JOIN courses ON courses.id=coursesstaff.courseId  INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id  INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId    INNER JOIN semester ON semester.id=courses.SemesterId   INNER JOIN studyplan ON studyplan.id=courses.StudyplanId   INNER JOIN faculty ON faculty.id=courses.facultyId  INNER JOIN staff ON staff.id=coursesstaff.staffId  INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId   INNER JOIN branch AS branch1 ON branch1.id=staff.branchId  INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId where semester.id = " + semesterId);
            while (rs.next()) {
                courseStaffs.add(new CourseStaff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getInt(20), rs.getString(21), rs.getInt(22), rs.getString(23)));

            }
            return courseStaffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<CourseStaff> getAllCourseStaffInStudyPlan(int StudyPlanId) {
        try {
            ArrayList<CourseStaff> courseStaffs = new ArrayList<>();
            ResultSet rs = conn.select("SELECT courses.id as courseId, courses.name as courseName,courses.code, courses.labHours, lecHours, courses.SemesterId, semester.number, courses.lectureGroupId, lecturegroups.name, courses.SectionsNumberOfGroupsID, sectionsnumberofgroups.name, courses.StudyplanId, studyplan.name, courses.facultyId, faculty.name, coursesstaff.staffId,staff.name,staff.JobTypeId,jobtype.name,staff.branchId,branch1.name,branch2.id,branch2.name   FROM coursesstaff  INNER JOIN courses ON courses.id=coursesstaff.courseId  INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id  INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId    INNER JOIN semester ON semester.id=courses.SemesterId   INNER JOIN studyplan ON studyplan.id=courses.StudyplanId   INNER JOIN faculty ON faculty.id=courses.facultyId  INNER JOIN staff ON staff.id=coursesstaff.staffId  INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId   INNER JOIN branch AS branch1 ON branch1.id=staff.branchId  INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId where studyPlan.id = " + StudyPlanId);
            while (rs.next()) {
                courseStaffs.add(new CourseStaff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getInt(20), rs.getString(21), rs.getInt(22), rs.getString(23)));

            }
            return courseStaffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<CourseStaff> getAllCourseStaffInBranch(int branchId) {
        try {
            ArrayList<CourseStaff> courseStaffs = new ArrayList<>();
            ResultSet rs = conn.select("SELECT courses.id as courseId,"
                    + " courses.name as courseName,"
                    + "courses.code,"
                    + " courses.labHours,"
                    + " lecHours,"
                    + " courses.SemesterId,"
                    + " semester.number,"
                    + " courses.lectureGroupId,"
                    + " lecturegroups.name,"
                    + " courses.SectionsNumberOfGroupsID,"
                    + " sectionsnumberofgroups.name,"
                    + " courses.StudyplanId,"
                    + " studyplan.name,"
                    + " courses.facultyId,"
                    + " faculty.name,"
                    + " coursesstaff.staffId,"
                    + "staff.name,"
                    + "staff.JobTypeId,"
                    + "jobtype.name,"
                    + "staff.branchId,"
                    + "branch1.name,"
                    + "branch2.id,"
                    + "branch2.name"
                    + "   FROM coursesstaff "
                    + " INNER JOIN courses ON courses.id=coursesstaff.courseId  "
                    + " INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id "
                    + " INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId  "
                    + " INNER JOIN semester ON semester.id=courses.SemesterId  "
                    + " INNER JOIN studyplan ON studyplan.id=courses.StudyplanId  "
                    + " INNER JOIN faculty ON faculty.id=courses.facultyId "
                    + " INNER JOIN staff ON staff.id=coursesstaff.staffId "
                    + " INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId  "
                    + " INNER JOIN branch AS branch1 ON branch1.id=staff.branchId "
                    + " INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId "
                    + " where branch2.id = " + branchId);
            while (rs.next()) {
                courseStaffs.add(new CourseStaff(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getInt(18),
                        rs.getString(19),
                        rs.getInt(20),
                        rs.getString(21),
                        rs.getInt(22),
                        rs.getString(23)));

            }
            return courseStaffs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<CourseStaff> getAllStaffTypeInCourseStaffInBranch(int branchId, int type) {
        try {
            ArrayList<CourseStaff> StaffJobTypeId = new ArrayList<>();
            ResultSet rs = conn.select(""
                    + "SELECT courses.id as courseId, courses.name as courseName,courses.code,"
                    + " courses.labHours, lecHours,"
                    + " courses.SemesterId, semester.number,"
                    + " courses.lectureGroupId, lecturegroups.name,"
                    + " courses.SectionsNumberOfGroupsID, sectionsnumberofgroups.name,"
                    + " courses.StudyplanId, studyplan.name,"
                    + " courses.facultyId, faculty.name,"
                    + " coursesstaff.staffId,staff.name,staff.JobTypeId,jobtype.name,staff.branchId,branch1.name,"
                    + "branch2.id,branch2.name "
                    + "  FROM coursesstaff "
                    + " INNER JOIN courses ON courses.id=coursesstaff.courseId "
                    + " INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id "
                    + " INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId   "
                    + " INNER JOIN semester ON semester.id=courses.SemesterId  "
                    + " INNER JOIN studyplan ON studyplan.id=courses.StudyplanId  "
                    + " INNER JOIN faculty ON faculty.id=courses.facultyId "
                    + " INNER JOIN staff ON staff.id=coursesstaff.staffId "
                    + " INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId  "
                    + " INNER JOIN branch AS branch1 ON branch1.id=staff.branchId "
                    + " INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId"
                    + " where branch2.id = " + branchId + " and staff.JobTypeId = " + type);
            while (rs.next()) {
                StaffJobTypeId.add(new CourseStaff(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getInt(18),
                        rs.getString(19),
                        rs.getInt(20),
                        rs.getString(21),
                        rs.getInt(22),
                        rs.getString(23)));

            }
            return StaffJobTypeId;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    /*------------------------------------------------------------------------------------------------*/
    public void addUser(String username, String password, int role) {
        conn.execute("insert into login (username, password, role) value('" + username + "','" + password + "'," + role + ")");
        conn.close();
    }

    public void changeUserPassword(int id, String password) {
        conn.execute("update login set password = '" + password + "' where id = " + id);
        conn.close();
    }

    public void changeUserRole(int id, int role) {
        conn.execute("update login set role= '" + role + "' where id = " + id);
        conn.close();
    }

    public ArrayList<user> GetUsers() {
        try {
            ArrayList<user> users = new ArrayList<>();
            ResultSet rs = conn.select("select * from login");
            while (rs.next()) {
                users.add(new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));

            }
            return users;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<user> GetOneUser(int userId) {
        try {
            ArrayList<user> users = new ArrayList<>();
            ResultSet rs = conn.select("select * from login where id = " + userId);
            while (rs.next()) {
                users.add(new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));

            }
            return users;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<timeInTimetable> createLectureTimeTable(course[] courses) {
        Integer[] arr = new Integer[courses.length];
        for (int i = 0; i < courses.length; i++) {
            arr[i] = courses[i].getId();
        }
        com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable t = new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable();
        t.createLectureTimetable(arr);
        ArrayList<com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Staff> staff = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        addStaffAndRooms(t.getTimesInTimetable(), rooms, staff);
        UpdateStaffAndRoomsFreeTime update = new UpdateStaffAndRoomsFreeTime(staff, rooms);
        update.start();
        return convert(t.getTimesInTimetable());
    }

    public ArrayList<timeInTimetable> createSectionTimeTable(int timetableId, int branchId, course[] courses) {
        Integer[] arr = new Integer[courses.length];
        for (int i = 0; i < courses.length; i++) {
            arr[i] = courses[i].getId();
        }
        com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable t = new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable(timetableId);
        t.createSectionTimetable(t, branchId, arr);
        ArrayList<com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Staff> staff = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        addStaffAndRooms(t.getTimesInTimetable(), rooms, staff);
        UpdateStaffAndRoomsFreeTime update = new UpdateStaffAndRoomsFreeTime(staff, rooms);
        update.start();
        return convert(t.getTimesInTimetable());
    }

    static void addStaffAndRooms(ArrayList<TimeInTimetable> t, ArrayList<Room> rooms, ArrayList<com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Staff> staff) {
        for (int i = 0; i < t.size(); i++) {
            for (int j = 0; j <= rooms.size(); j++) {
                if (j == rooms.size()) {
                    rooms.add(t.get(i).getHostingRoom());
                    break;
                } else if (rooms.get(j).getId() == t.get(i).getHostingRoom().getId()) {
                    break;
                }
            }

            if (t.get(i).getRooms() != null && t.get(i).getRooms().size() > 0) {
                for (int k = 0; k < t.get(i).getRooms().size(); k++) {
                    if (!rooms.contains(t.get(i).getRooms().get(k))) {
                        rooms.add(t.get(i).getRooms().get(k));
                    }

                }
            }

            for (int j = 0; j <= staff.size(); j++) {
                if (j == staff.size()) {
                    staff.add(t.get(i).getStaff());
                    break;

                } else if (staff.get(j).getId() == t.get(i).getStaff().getId()) {
                    break;
                }
            }
        }
    }

    public ArrayList<timeInTimetable> getLectuerTimesInTimetable(int timeTableId) {
        ArrayList<timeInTimetable> t = new ArrayList<>();
        try {
            ResultSet rs = conn.select("""
                                       select timesintimetable.id,
                                       timesintimetable.timetableId, timetable.name,
                                       timesintimetable.courseId,courses.name,
                                       timesintimetable.staffId, staff.name,
                                       timesintimetable.hostingRoomId,rooms.name,
                                       timesintimetable.hostingBranchId,branch.name,
                                       timesintimetable.dayId,timesintimetable.startingTime,timesintimetable.enddingTime,
                                       timesintimetable.lecGroupId,lecgroup.name,
                                       branchesInTimeInTimetable.branchId ,b2.name,
                                       roomsintimeintimetable.roomId,r2.name
                                        from timesintimetable 
                                           inner join roomsInTimeInTimetable on timesintimetable.id=roomsInTimeInTimetable.timeInTimetableId
                                           inner join branchesInTimeInTimetable on timesintimetable.id=branchesInTimeInTimetable.timeInTimetableId
                                           inner join timetable on timesintimetable.timeTableId=timetable.id
                                           inner join courses on timesintimetable.courseId=courses.id
                                           inner join staff on timesintimetable.StaffId=staff.id
                                           inner join rooms on timesintimetable.hostingRoomID=rooms.id
                                           inner join branch on timesintimetable.hostingbranchId=branch.id
                                           inner join lecgroup on timesintimetable.lecGroupId=lecgroup.id
                                           inner join branch b2 on b2.id=branchesInTimeInTimetable.branchId 
                                           inner join rooms r2 on r2.Id in (select id from rooms where rooms.branchId=b2.id)
                                           where timeTableId = """ + timeTableId);
            while (rs.next()) {
                boolean found = false;
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).getId() == rs.getInt(1)) {
                        found = true;
                        boolean roomFound = false, branchFound = false;
                        for (int j = 0; j < t.get(i).getBranchs().size(); j++) {
                            if (t.get(i).getBranchs().get(j).getId() == rs.getInt(17) || t.get(i).getHostingBranch().getId() == rs.getInt(17)) {
                                branchFound = true;
                                break;
                            }
                        }
                        for (int j = 0; j < t.get(i).getRooms().size(); j++) {
                            if (t.get(i).getRooms().get(j).getId() == rs.getInt(19) || t.get(i).getHostingRoom().getId() == rs.getInt(19)) {
                                roomFound = true;
                                break;
                            }
                        }
                        if (!roomFound) {
                            t.get(i).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                        }
                        if (!branchFound) {
                            t.get(i).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                        }
                    }
                }
                if (!found) {
                    t.add(new timeInTimetable(
                            rs.getInt(1),
                            new Staff(rs.getInt(6), rs.getString(7)),
                            new course(rs.getInt(4), rs.getString(5)),
                            new ArrayList<Branch>(),
                            new Branch(rs.getInt(10), rs.getString(11)),
                            new ArrayList<room>(),
                            new room(rs.getInt(8), rs.getString(9)),
                            rs.getInt(12),
                            rs.getInt(13),
                            rs.getInt(14),
                            null,
                            new LecGroup(rs.getInt(15), rs.getString(16)),
                            new Timetable(timeTableId, rs.getString(3))));
                    t.get(t.size() - 1).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                    t.get(t.size() - 1).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                }
            }
            return t;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<TimeInTimetable> getLectuerTimesInTimetable1(int timeTableId) {
        ArrayList<TimeInTimetable> t = new ArrayList<>();
        try {
            ResultSet rs = conn.select("""
                                       select timesintimetable.id,
                                       timesintimetable.timetableId, timetable.name,
                                       timesintimetable.courseId,courses.name,
                                       timesintimetable.staffId, staff.name,
                                       timesintimetable.hostingRoomId,rooms.name,
                                       timesintimetable.hostingBranchId,branch.name,
                                       timesintimetable.dayId,timesintimetable.startingTime,timesintimetable.enddingTime,
                                       timesintimetable.lecGroupId,lecgroup.name,
                                       branchesInTimeInTimetable.branchId ,b2.name,
                                       roomsintimeintimetable.roomId,r2.name
                                        from timesintimetable 
                                           inner join roomsInTimeInTimetable on timesintimetable.id=roomsInTimeInTimetable.timeInTimetableId
                                           inner join branchesInTimeInTimetable on timesintimetable.id=branchesInTimeInTimetable.timeInTimetableId
                                           inner join timetable on timesintimetable.timeTableId=timetable.id
                                           inner join courses on timesintimetable.courseId=courses.id
                                           inner join staff on timesintimetable.StaffId=staff.id
                                           inner join rooms on timesintimetable.hostingRoomID=rooms.id
                                           inner join branch on timesintimetable.hostingbranchId=branch.id
                                           inner join lecgroup on timesintimetable.lecGroupId=lecgroup.id
                                           inner join branch b2 on b2.id=branchesInTimeInTimetable.branchId 
                                           inner join rooms r2 on r2.Id in (select id from rooms where rooms.branchId=b2.id)
                                           where timeTableId = """ + timeTableId);
            while (rs.next()) {
                boolean found = false;
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).getId() == rs.getInt(1)) {
                        found = true;
                        boolean roomFound = false, branchFound = false;
                        for (int j = 0; j < t.get(i).getBranchs().size(); j++) {
                            if (t.get(i).getBranchs().get(j).getId() == rs.getInt(17) || t.get(i).getHostingBranch().getId() == rs.getInt(17)) {
                                branchFound = true;
                                break;
                            }
                        }
                        for (int j = 0; j < t.get(i).getRooms().size(); j++) {
                            if (t.get(i).getRooms().get(j).getId() == rs.getInt(19) || t.get(i).getHostingRoom().getId() == rs.getInt(19)) {
                                roomFound = true;
                                break;
                            }
                        }
                        if (!roomFound) {
                            t.get(i).getRooms().add(new Room(rs.getInt(19), rs.getString(20)));
                        }
                        if (!branchFound) {
                            t.get(i).getBranchs().add(new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Branch(rs.getInt(17), rs.getString(18)));
                        }
                    }
                }
                if (!found) {
                    t.add(new TimeInTimetable(
                            rs.getInt(1),
                            new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Staff(rs.getInt(6), rs.getString(7)),
                            new Course(rs.getInt(4), rs.getString(5)),
                            new ArrayList<com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Branch>(),
                            new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Branch(rs.getInt(10), rs.getString(11)),
                            new ArrayList<Room>(),
                            new Room(rs.getInt(8), rs.getString(9)),
                            rs.getInt(12),
                            rs.getInt(13),
                            rs.getInt(14),
                            null,
                            new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.LecGroup(rs.getInt(15), rs.getString(16)),
                            new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable(timeTableId, rs.getString(3))));
                    t.get(t.size() - 1).getBranchs().add(new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Branch(rs.getInt(17), rs.getString(18)));
                    t.get(t.size() - 1).getRooms().add(new Room(rs.getInt(19), rs.getString(20)));
                }
            }
            return t;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<timeInTimetable> getLectuerTimesInTimetableForCourses(int timeTableId, int[] coursesIds) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coursesIds.length; i++) {
            sb.append(coursesIds[i] + ",");
        }
        ArrayList<timeInTimetable> t = new ArrayList<>();
        try {
            ResultSet rs = conn.select("""
                                       select timesintimetable.id,
                                       timesintimetable.timetableId, timetable.name,
                                       timesintimetable.courseId,courses.name,
                                       timesintimetable.staffId, staff.name,
                                       timesintimetable.hostingRoomId,rooms.name,
                                       timesintimetable.hostingBranchId,branch.name,
                                       timesintimetable.dayId,timesintimetable.startingTime,timesintimetable.enddingTime,
                                       timesintimetable.lecGroupId,lecgroup.name,
                                       branchesInTimeInTimetable.branchId ,b2.name,
                                       roomsintimeintimetable.roomId,r2.name
                                        from timesintimetable 
                                           inner join roomsInTimeInTimetable on timesintimetable.id=roomsInTimeInTimetable.timeInTimetableId
                                           inner join branchesInTimeInTimetable on timesintimetable.id=branchesInTimeInTimetable.timeInTimetableId
                                           inner join timetable on timesintimetable.timeTableId=timetable.id
                                           inner join courses on timesintimetable.courseId=courses.id
                                           inner join staff on timesintimetable.StaffId=staff.id
                                           inner join rooms on timesintimetable.hostingRoomID=rooms.id
                                           inner join branch on timesintimetable.hostingbranchId=branch.id
                                           inner join lecgroup on timesintimetable.lecGroupId=lecgroup.id
                                           inner join branch b2 on b2.id=branchesInTimeInTimetable.branchId 
                                           inner join rooms r2 on r2.Id in (select id from rooms where rooms.branchId=b2.id)
                                           where timeTableId = """ + timeTableId + " and courseId in (" + sb.toString().substring(0, sb.toString().length() - 1) + ")");
            while (rs.next()) {
                boolean found = false;
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).getId() == rs.getInt(1)) {
                        found = true;
                        boolean roomFound = false, branchFound = false;
                        for (int j = 0; j < t.get(i).getBranchs().size(); j++) {
                            if (t.get(i).getBranchs().get(j).getId() == rs.getInt(17) || t.get(i).getHostingBranch().getId() == rs.getInt(17)) {
                                branchFound = true;
                                break;
                            }
                        }
                        for (int j = 0; j < t.get(i).getRooms().size(); j++) {
                            if (t.get(i).getRooms().get(j).getId() == rs.getInt(19) || t.get(i).getHostingRoom().getId() == rs.getInt(19)) {
                                roomFound = true;
                                break;
                            }
                        }
                        if (!roomFound) {
                            t.get(i).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                        }
                        if (!branchFound) {
                            t.get(i).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                        }
                    }
                }
                if (!found) {
                    t.add(new timeInTimetable(
                            rs.getInt(1),
                            new Staff(rs.getInt(6), rs.getString(7)),
                            new course(rs.getInt(4), rs.getString(5)),
                            new ArrayList<Branch>(),
                            new Branch(rs.getInt(10), rs.getString(11)),
                            new ArrayList<room>(),
                            new room(rs.getInt(8), rs.getString(9)),
                            rs.getInt(12),
                            rs.getInt(13),
                            rs.getInt(14),
                            null,
                            new LecGroup(rs.getInt(15), rs.getString(16)),
                            new Timetable(timeTableId, rs.getString(3))));
                    t.get(t.size() - 1).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                    t.get(t.size() - 1).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                }
            }
            return t;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<timeInTimetable> getLectuerTimesInTimetableWithBranches(int timeTableId, int[] branchesIds) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branchesIds.length; i++) {
            sb.append(branchesIds[i]).append(",");
        }
        ArrayList<timeInTimetable> t = new ArrayList<>();
        try {
            ResultSet rs = conn.select("""
                                       select timesintimetable.id,
                                       timesintimetable.timetableId, timetable.name,
                                       timesintimetable.courseId,courses.name,
                                       timesintimetable.staffId, staff.name,
                                       timesintimetable.hostingRoomId,rooms.name,
                                       timesintimetable.hostingBranchId,branch.name,
                                       timesintimetable.dayId,timesintimetable.startingTime,timesintimetable.enddingTime,
                                       timesintimetable.lecGroupId,lecgroup.name,
                                       branchesInTimeInTimetable.branchId ,b2.name,
                                       roomsintimeintimetable.roomId,r2.name
                                        from timesintimetable 
                                           inner join roomsInTimeInTimetable on timesintimetable.id=roomsInTimeInTimetable.timeInTimetableId
                                           inner join branchesInTimeInTimetable on timesintimetable.id=branchesInTimeInTimetable.timeInTimetableId
                                           inner join timetable on timesintimetable.timeTableId=timetable.id
                                           inner join courses on timesintimetable.courseId=courses.id
                                           inner join staff on timesintimetable.StaffId=staff.id
                                           inner join rooms on timesintimetable.hostingRoomID=rooms.id
                                           inner join branch on timesintimetable.hostingbranchId=branch.id
                                           inner join lecgroup on timesintimetable.lecGroupId=lecgroup.id
                                           inner join branch b2 on b2.id=branchesInTimeInTimetable.branchId 
                                           inner join rooms r2 on r2.Id in (select id from rooms where rooms.branchId=b2.id)
                                           where timeTableId = """ + timeTableId + " and ( hostingbarnchId in (" + sb.substring(0, sb.length() - 1) + ") or b2.id in (" + sb.substring(0, sb.length() - 1) + " ) )");
            while (rs.next()) {
                boolean found = false;
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).getId() == rs.getInt(1)) {
                        found = true;
                        boolean roomFound = false, branchFound = false;
                        for (int j = 0; j < t.get(i).getBranchs().size(); j++) {
                            if (t.get(i).getBranchs().get(j).getId() == rs.getInt(17) || t.get(i).getHostingBranch().getId() == rs.getInt(17)) {
                                branchFound = true;
                                break;
                            }
                        }
                        for (int j = 0; j < t.get(i).getRooms().size(); j++) {
                            if (t.get(i).getRooms().get(j).getId() == rs.getInt(19) || t.get(i).getHostingRoom().getId() == rs.getInt(19)) {
                                roomFound = true;
                                break;
                            }
                        }
                        if (!roomFound) {
                            t.get(i).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                        }
                        if (!branchFound) {
                            t.get(i).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                        }
                    }
                }
                if (!found) {
                    t.add(new timeInTimetable(
                            rs.getInt(1),
                            new Staff(rs.getInt(6), rs.getString(7)),
                            new course(rs.getInt(4), rs.getString(5)),
                            new ArrayList<Branch>(),
                            new Branch(rs.getInt(10), rs.getString(11)),
                            new ArrayList<room>(),
                            new room(rs.getInt(8), rs.getString(9)),
                            rs.getInt(12),
                            rs.getInt(13),
                            rs.getInt(14),
                            null,
                            new LecGroup(rs.getInt(15), rs.getString(16)),
                            new Timetable(timeTableId, rs.getString(3))));
                    t.get(t.size() - 1).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                    t.get(t.size() - 1).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                }
            }
            return t;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<timeInTimetable> getLectuerTimesInTimetableWithStaff(int timeTableId, int[] staffIds) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < staffIds.length; i++) {
            sb.append(staffIds[i]).append(",");
        }
        ArrayList<timeInTimetable> t = new ArrayList<>();
        try {
            ResultSet rs = conn.select("""
                                       select timesintimetable.id,
                                       timesintimetable.timetableId, timetable.name,
                                       timesintimetable.courseId,courses.name,
                                       timesintimetable.staffId, staff.name,
                                       timesintimetable.hostingRoomId,rooms.name,
                                       timesintimetable.hostingBranchId,branch.name,
                                       timesintimetable.dayId,timesintimetable.startingTime,timesintimetable.enddingTime,
                                       timesintimetable.lecGroupId,lecgroup.name,
                                       branchesInTimeInTimetable.branchId ,b2.name,
                                       roomsintimeintimetable.roomId,r2.name
                                        from timesintimetable 
                                           inner join roomsInTimeInTimetable on timesintimetable.id=roomsInTimeInTimetable.timeInTimetableId
                                           inner join branchesInTimeInTimetable on timesintimetable.id=branchesInTimeInTimetable.timeInTimetableId
                                           inner join timetable on timesintimetable.timeTableId=timetable.id
                                           inner join courses on timesintimetable.courseId=courses.id
                                           inner join staff on timesintimetable.StaffId=staff.id
                                           inner join rooms on timesintimetable.hostingRoomID=rooms.id
                                           inner join branch on timesintimetable.hostingbranchId=branch.id
                                           inner join lecgroup on timesintimetable.lecGroupId=lecgroup.id
                                           inner join branch b2 on b2.id=branchesInTimeInTimetable.branchId 
                                           inner join rooms r2 on r2.Id in (select id from rooms where rooms.branchId=b2.id)
                                           where timeTableId = """ + timeTableId + " and staffId in (" + sb.substring(0, sb.length() - 1) + ")");
            while (rs.next()) {
                boolean found = false;
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).getId() == rs.getInt(1)) {
                        found = true;
                        boolean roomFound = false, branchFound = false;
                        for (int j = 0; j < t.get(i).getBranchs().size(); j++) {
                            if (t.get(i).getBranchs().get(j).getId() == rs.getInt(17) || t.get(i).getHostingBranch().getId() == rs.getInt(17)) {
                                branchFound = true;
                                break;
                            }
                        }
                        for (int j = 0; j < t.get(i).getRooms().size(); j++) {
                            if (t.get(i).getRooms().get(j).getId() == rs.getInt(19) || t.get(i).getHostingRoom().getId() == rs.getInt(19)) {
                                roomFound = true;
                                break;
                            }
                        }
                        if (!roomFound) {
                            t.get(i).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                        }
                        if (!branchFound) {
                            t.get(i).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                        }
                    }
                }
                if (!found) {
                    t.add(new timeInTimetable(
                            rs.getInt(1),
                            new Staff(rs.getInt(6), rs.getString(7)),
                            new course(rs.getInt(4), rs.getString(5)),
                            new ArrayList<Branch>(),
                            new Branch(rs.getInt(10), rs.getString(11)),
                            new ArrayList<room>(),
                            new room(rs.getInt(8), rs.getString(9)),
                            rs.getInt(12),
                            rs.getInt(13),
                            rs.getInt(14),
                            null,
                            new LecGroup(rs.getInt(15), rs.getString(16)),
                            new Timetable(timeTableId, rs.getString(3))));
                    t.get(t.size() - 1).getBranchs().add(new Branch(rs.getInt(17), rs.getString(18)));
                    t.get(t.size() - 1).getRooms().add(new room(rs.getInt(19), rs.getString(20)));
                }
            }
            return t;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            conn.close();
        }
        return null;
    }

    static ArrayList<timeInTimetable> convert(ArrayList<TimeInTimetable> t) {
        ArrayList<timeInTimetable> t1 = new ArrayList<>();
        ArrayList<room> rooms = null;
        ArrayList<Branch> branchs = null;
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getRooms() != null) {
                rooms = new ArrayList<>();
                branchs = new ArrayList<>();
                for (int j = 0; j < t.get(i).getBranchs().size(); j++) {
                    rooms.add(new room(t.get(i).getRooms().get(j).getId()));
                    branchs.add(new Branch(t.get(i).getBranchs().get(j).getId()));
                }
            }
            t1.add(new timeInTimetable(new Staff(t.get(i).getStaff().getId()),
                    new course(t.get(i).getCourse().getId()),
                    branchs, new Branch(t.get(i).getHostingBranch().getId()),
                    rooms, new room(t.get(i).getHostingRoom().getId()),
                    t.get(i).getDay(), t.get(i).getStartingTime(), t.get(i).getEndingTime(),
                    t.get(i).getSectionGroupName(),
                    (t.get(i).getLecGroup() != null) ? new LecGroup(t.get(i).getLecGroup().getId()) : null,
                    new com.team.timetableManagmentSystem.DTOs.Timetable(t.get(i).getTimetable().getId())));
        }
        return t1;
    }

    public void addFreeTimeForStaff(int staffId, freeTime freeTime) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                sb.append("(").append(staffId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                while (freeTime.dayStartEnd[i].next != null) {
                    freeTime.dayStartEnd[i] = freeTime.dayStartEnd[i].next;
                    if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                        sb.append("(").append(staffId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                    }
                }
            }
        }
        conn.execute("insert into freetimeforsatff values " + sb.substring(0, sb.length() - 1));
        conn.close();
    }

    public freeTime getFreeTimeForStaff(int staffId) {
        freeTime f = new freeTime();
        try {
            ResultSet rs;
            rs = conn.select("select * from freetimeforstaff where StaffId =" + staffId);
            while (rs.next()) {
                f.addFreeTime(rs.getInt(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(5 + "" + e);
        } finally {
            conn.close();
        }
        return f;
    }

    public void updateFreeTimeForStaff(int staffId, freeTime freeTime) {
        conn.execute("delete from freetimeforstaff where StaffId = " + staffId);
        addFreeTimeForStaff(staffId, freeTime);
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public void addFreeTimeForRooms(int RoomId, freeTime freeTime) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                sb.append("(").append(RoomId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                while (freeTime.dayStartEnd[i].next != null) {
                    freeTime.dayStartEnd[i] = freeTime.dayStartEnd[i].next;
                    if (freeTime.dayStartEnd[i] != null && freeTime.dayStartEnd[i].startSession != null) {
                        sb.append("(").append(RoomId).append(",").append(i).append(",").append(freeTime.dayStartEnd[i].startSession).append(",").append(freeTime.dayStartEnd[i].endSession).append("),");
                    }
                }
            }
        }
        conn.execute("insert into freetimeforrooms values " + sb.substring(0, sb.length() - 1));
        conn.close();
    }

    public freeTime getFreeTimeForRooms(int RoomId) {
        freeTime f = new freeTime();
        try {
            ResultSet rs;
            rs = conn.select("select * from freetimeforRooms where StaffId =" + RoomId);
            while (rs.next()) {
                f.addFreeTime(rs.getInt(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(5 + "" + e);
        } finally {
            conn.close();
        }
        return f;
    }

    public void updateFreeTimeForRooms(int RoomsId, freeTime freeTime) {
        conn.execute("delete from freetimeforstaff where StaffId = " + RoomsId);
        addFreeTimeForRooms(RoomsId, freeTime);
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

}
