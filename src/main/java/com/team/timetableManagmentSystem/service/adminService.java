package com.team.timetableManagmentSystem.service;

import com.team.timetableManagmentSystem.DTOs.*;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Course;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Room;
import com.team.timetableManagmentSystem.academictimetablemanagmentsystem.TimeInTimetable;
import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class adminService {

    public boolean branchNameExist(String name) {
        connection conn = new connection();
        ResultSet rs = conn.select("select id from branch where name like '" + name + "'");
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void insertNewBranch(String name) {
        connection conn = new connection();

        conn.execute("insert into branch (name) value ('" + name + "')");
        conn.close();
    }

    public void insertNewBranches(String values) {

        connection conn = new connection();

        conn.execute("insert into branch (name) values " + values);
        conn.close();
    }

    public void updateUser(int userId, String username, int role, String password) {
        connection conn = new connection();

        System.out.println("update login set username = '" + username + "' ,password= '" + password + "', role = " + role + " where id = " + username);
        conn.execute("update login set username = '" + username + "' ,password= '" + password + "', role = " + role + " where id = " + username);
        conn.close();
    }

    public void UpdateStudyPlan(int studyPlanId, int facultyId, String name) {
        connection conn = new connection();
        conn.execute("update studyPlan set facultyId = " + facultyId + " , name ='" + name + "' where id = " + studyPlanId);
        conn.close();
    }

    public void UpdateSemester(int semesterId, int semesterNumber, int studyPlanId) {
        connection conn = new connection();
        conn.execute("update semester set number = " + semesterNumber + " ,studyPlanId = " + studyPlanId + " where id = " + semesterId);
        conn.close();
    }

    public ArrayList<jobType> getAllJobTypes() {
        connection conn = new connection();
        ArrayList<jobType> j = new ArrayList<>();
        ResultSet rs = conn.select("select * from jobtype");
        try {
            while (rs.next()) {
                j.add(new jobType(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return j;
    }

    public ArrayList<userRole> getAllRoles() {
        connection conn = new connection();

        try {
            ArrayList<userRole> roles = new ArrayList<>();
            ResultSet rs = conn.select("   select * from roletype ");
            while (rs.next()) {
                roles.add(new userRole(rs.getInt(1), rs.getString(2)));
            }
            return roles;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Branch> getAllBranches() {
        connection conn = new connection();

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
        connection conn = new connection();
        conn.execute("update branch set name ='" + newName + "' where id =" + id);
        conn.close();
    }

    public void removeBranch(int id) {
        connection conn = new connection();
        conn.execute("delete from branch where id =" + id);
        conn.close();
    }

    public void updateRoom(int id, String name, int capacity, int branchId, int typeId) {
        connection conn = new connection();
        conn.execute("update rooms set name = '" + name + "' , capacity = " + capacity + " , TypeId = " + typeId + ", branchId = " + branchId + " where id =" + id);
        conn.close();
    }

    public ArrayList<room> GetallRoomsInAllBranches() {
        connection conn = new connection();
        try {
            ArrayList<room> rooms = new ArrayList<>();

            ResultSet rs = conn.select("select rooms.id,rooms.name as name,rooms.capacity,"
                    + "rooms.TypeId,roomstypes.RoomType as troom,rooms.branchId,"
                    + "branch.name as bname from rooms"
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
        connection conn = new connection();
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
        connection conn = new connection();

        conn.execute("delete from rooms where id =" + roomId);
        conn.close();
    }

    public ArrayList<roomType> getallRoomType() {
        connection conn = new connection();
        ArrayList<roomType> r = new ArrayList<>();
        ResultSet rs = conn.select("select * from roomstypes");
        try {
            while (rs.next()) {
                r.add(new roomType(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return r;
    }

    public void addRoom(String name, int capacity, int typeId, int branchId) {
        connection conn = new connection();

        conn.execute("insert into rooms (name,capacity,typeId,branchId) value ('" + name + "'," + capacity + "," + typeId + "," + branchId + ")");
        conn.close();
    }

    public void AddRooms(String values) {
        connection conn = new connection();

        conn.execute("insert into rooms (name,capacity,typeId,branchId) values " + values);
        conn.close();
    }

    public void addStaff(String name, int jobTypeId, int branchId) {
        connection conn = new connection();

        conn.execute("insert into staff (name,JobTypeId,branchId) value ('" + name + "'," + jobTypeId + "," + branchId + ")");
        conn.close();
    }

    public void addStaffs(String values) {
        connection conn = new connection();

        conn.execute("insert into staff (name,JobTypeId,branchId) values " + values);
        conn.close();
    }

    public ArrayList<Staff> getAllStaffInAllBranches() {
        connection conn = new connection();

        try {
            ArrayList<Staff> staffs = new ArrayList<>();
            ResultSet rs = conn.select("   select staff.id,"
                    + "staff.name,"
                    + "staff.JobTypeId,"
                    + "jobtype.name as jobtypename,"
                    + "staff.branchId,"
                    + "branch.name as branchname"
                    + " from staff "
                    + "inner join jobtype on jobtype.Id=staff.JobTypeId "
                    + "inner join branch on branch.id=staff.branchId");
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

    public ArrayList<Staff> getAllStaffInAllBranchesWithOutExistingUsers() {
        connection conn = new connection();

        try {
            ArrayList<Staff> staffs = new ArrayList<>();
            ResultSet rs = conn.select("   select staff.id,"
                    + "staff.name,"
                    + "staff.JobTypeId,"
                    + "jobtype.name as jobtypename,"
                    + "staff.branchId,"
                    + "branch.name as branchname"
                    + " from staff "
                    + "inner join jobtype on jobtype.Id=staff.JobTypeId "
                    + "inner join branch on branch.id=staff.branchId where staff.id not in (select id from login)");
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
        connection conn = new connection();

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
        connection conn = new connection();

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
        connection conn = new connection();

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

    public void updateStaff(int staffId, String staffName, int branchId, int jobTypeId) {
        connection conn = new connection();

        conn.execute("update staff set name = '" + staffName + "',branchid =" + branchId + ",JobTypeId = " + jobTypeId + " where id = " + staffId + ";");
        conn.close();
    }

    public void editStaffType(int staffId, int staffType) {
        connection conn = new connection();

        conn.execute("upadte staff set JobtypeId = " + staffType + " where id = " + staffId);
        conn.close();
    }

    public void editStaffBranch(int staffId, int branchId) {
        connection conn = new connection();

        conn.execute("update staff set branchId = " + branchId + " where id = " + staffId);
        conn.close();
    }

    public void removeStaff(int staffId) {
        connection conn = new connection();

        conn.execute("delete from staff where id = " + staffId);
        conn.close();
    }

    public void addFaculty(String name) {
        connection conn = new connection();

        conn.execute("insert into faculty (name) value ('" + name + "')");
        conn.close();
    }

    public ArrayList<Faculty> getFacultys() {
        connection conn = new connection();

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
        connection conn = new connection();

        conn.execute("delete from faculty where id = " + id);
        conn.close();
    }

    public void EditFacultyName(int facultyId, String facultyName) {
        connection conn = new connection();

        conn.execute("update faculty set name ='" + facultyName + "' where id = " + facultyId);
        conn.close();
    }

    public void insertStudyPlan(String name, int facultyId) {
        connection conn = new connection();

        conn.execute("insert into studyPlan (name,facultyId) value ('" + name + "', " + facultyId + ")");
        conn.close();
    }

    public void editStudyPlanName(int id, String name) {
        connection conn = new connection();

        conn.execute("update studyPlan set name = '" + name + "' where id =" + id);
        conn.close();
    }

    public void editStudyPlanfaculty(int id, int facultyId) {
        connection conn = new connection();

        conn.execute("update studyPlan set FacultyId = " + facultyId + " where id =" + id);
        conn.close();
    }

    public void removeStudyPlans(int id) {
        connection conn = new connection();
        conn.execute("delete from studyPlan where id = " + id);
        conn.close();
    }

    public ArrayList<StudyPlan> getAllStudyPlans() {
        connection conn = new connection();

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
        connection conn = new connection();

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
        connection conn = new connection();

        conn.execute("insert into semester (number,studyPlanId) value (" + number + "," + studyPlanId + ")");
        conn.close();
    }

    public void deleteUser(int id) {

        connection conn = new connection();
        conn.execute("delete from login where id = " + id);
        conn.close();
    }

    public void editSemesterNumber(int id, int number) {
        connection conn = new connection();

        conn.execute("update Semester set number = " + number + " where id = " + id);
        conn.close();
    }

    public void editSemesterStudyPlan(int id, int StudyPlanId) {
        connection conn = new connection();

        conn.execute("update Semester set StudyPlanId = " + StudyPlanId + " where id = " + id);
        conn.close();
    }

    public void removeSemester(int id) {
        connection conn = new connection();

        conn.execute("delete from Semester where id = " + id);
        conn.close();
    }

    public ArrayList<Semester> getAllSemesters() {
        connection conn = new connection();

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
        connection conn = new connection();

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
        connection conn = new connection();

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
        connection conn = new connection();

        conn.execute("insert into lectureGroup (name) value ('" + name + "')");
        conn.close();
    }

    public void editLectuerGroupName(int id, String name) {
        connection conn = new connection();

        conn.execute("update lectureGroup set name = '" + name + "' where id = " + id);
        conn.close();
    }

    public void removeLectuerGroup(int id) {
        connection conn = new connection();

        conn.select("delete from lectureGroup where id = " + id);
    }

    public ArrayList<LectureGroup> getAllLectuerGroups() {
        connection conn = new connection();

        try {
            ArrayList<LectureGroup> lectuerGoups = new ArrayList<>();
            ResultSet rs = conn.select("select * from lecturegroups");
            while (rs.next()) {
                lectuerGoups.add(new LectureGroup(rs.getInt(1), rs.getString(2)));
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
        connection conn = new connection();

        conn.execute("insert into lecGroups (name,lectureGroupId) value ('" + name + "'," + lectureGroupId + ")");
        conn.close();
    }

    public void editLecGroupInALectuerGroupName(int lecGroupId, String name) {
        connection conn = new connection();

        conn.execute("update lecGroup set name = '" + name + "' where id = " + lecGroupId);
        conn.close();
    }

    public void editLecGroupInALectuerGroupLectuerGroup(int Id, int lectuerGroupId) {
        connection conn = new connection();

        conn.execute("update lecGroup set lectuerGroupId = " + lectuerGroupId + " where id = " + Id);
        conn.close();
    }

    public void removeLecGroupInALectuerGroupLectuerGroup(int Id) {
        connection conn = new connection();

        conn.execute("delete from lecGroup where id = " + Id);
        conn.close();
    }

    /*--------------------------------------------------------------------------------------------*/
    public void addBranchInALecGroup(int BranchId, int lecGroupId) {
        connection conn = new connection();

        conn.execute("insert into lecgroupbranches  value (" + lecGroupId + "," + BranchId + ")");
        conn.close();
    }

    public void removeBranchFromLecGroup(int branchId, int lecGroupId) {
        connection conn = new connection();

        conn.execute("delete from lecgroupbranches where lecGroupId = " + lecGroupId + " and branchId = " + branchId);
        conn.close();
    }

    public Object getAllLecGroups() {
        connection conn = new connection();
        ArrayList<LecGroup> lecs = new ArrayList<>();
        try {
            ResultSet rs = conn.select("select lecgroup.id,lecgroup.name,lecturegroupId,lecturegroups.name from lecgroup inner join  lecturegroups on lecturegroups.id=lecturegroupId;");
            while (rs.next()) {
                lecs.add(new LecGroup(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            return lecs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getAllLecGroupbranchs() {
        connection conn = new connection();
        ArrayList<lectureGroupBranchs> lecbranches = new ArrayList<>();
        try {
            ResultSet rs = conn.select("select lecgroup.id,lecgroup.name,lecturegroupId,lecturegroups.name,branchId,branch.name from lecgroupbranches inner join branch on branchId=branch.id inner join lecgroup on lecGroupId=lecgroup.id inner join  lecturegroups on lecturegroups.id=lecturegroupId;");
            while (rs.next()) {
                lecbranches.add(new lectureGroupBranchs(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            return lecbranches;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Branch> getAllBranchesInLecGroup(int lecGroupId) {
        connection conn = new connection();

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
        connection conn = new connection();

        conn.execute("insert into sectionsnumberofgroups (name) value ('" + name + "')");
        conn.close();
    }

    public void editSectionGroupName(int id, String name) {
        connection conn = new connection();

        conn.execute("update sectionsnumberofgroups set name = '" + name + "' where id = " + id);
        conn.close();
    }

    public void removeSectionGroup(int id) {
        connection conn = new connection();

        conn.select("delete from sectionsnumberofgroups where id = " + id);
        conn.close();
    }

    public ArrayList<SectionGroup> getAllSectionGroups() {
        connection conn = new connection();

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
        connection conn = new connection();

        conn.execute("insert into section sectionsfgroups (sectionsnumberofgroupsId,branchId,numberOfGroups) value (" + sectionsnumberofgroupsId + "," + branchId + "," + numberOfGroups + ")");
        conn.close();
    }

    public void editBranchSectionGroupnumberOfGroups(int branchId, int sectionsnumberofgroupsId, int numberOfGroups) {
        connection conn = new connection();

        conn.execute("edit sectionsfgroups set  numberOfGroups = " + numberOfGroups + " where bracnhId = " + branchId + " and sectionsnumberofgroupsId = " + sectionsnumberofgroupsId);
        conn.close();
    }

    public void changeBranchSectionGroup(int branchId, int sectionsnumberofgroupsId, int newsectionNumberOfGroups) {
        connection conn = new connection();

        conn.execute("edit sectionsfgroups set  sectionsnumberofgroupsId = " + newsectionNumberOfGroups + " where bracnhId = " + branchId + " and sectionsnumberofgroupsId = " + sectionsnumberofgroupsId);
        conn.close();
    }

    public void removeBranchFromSectionGroup(int branchId, int sectionsnumberofgroupsId) {
        connection conn = new connection();

        conn.execute("delete from sectionsfgroups where branchId = " + branchId + " and sectionsnumberofgroupsId = " + sectionsnumberofgroupsId);
        conn.close();

    }

    public void insertCourse(String name, String code, int lecHour, int labHours, int SemesterId, int StudyPlanId, int facultyId) {
        connection conn = new connection();

        conn.execute("insert into courses (name,code,lecHours,labHours,semesterId,facultyId,studyplanId,lectureGroupId,sectionsnumberofgroupsId) values('" + name + "','" + code + "'," + lecHour + "," + labHours + "," + SemesterId + "," + facultyId + "," + StudyPlanId + ",0,0)");
        conn.close();
    }

    public void setLecGroupIdForCourse(int courseId, int lecGroupId) {
        connection conn = new connection();

        conn.execute("update courses set lectureGroupId = " + lecGroupId + " where id = " + courseId);
        conn.close();
    }

    public void setSectionGroupIdForCourse(int courseId, int SectionGroupId) {
        connection conn = new connection();

        conn.execute("update courses set sectionsnumberofgroupsId = " + SectionGroupId + " where id = " + courseId);
        conn.close();
    }

    public ArrayList<course> getALLCourses() {
        connection conn = new connection();
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
        connection conn = new connection();
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
        connection conn = new connection();
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
        connection conn = new connection();

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
        connection conn = new connection();

        conn.execute("insert into courseStaff (courseId,staffId,branchId) value (" + courseId + "," + staffId + "," + branchId + ")");
        conn.close();
    }

    public void deleteCourseStaff(int id) {
        connection conn = new connection();

        conn.execute("delete from courseStaff where id = " + id);
        conn.close();
    }

    public void editCourseForCourseStaffInABranch(int courseId, int id) {

        connection conn = new connection();
        conn.execute("update courseStaff set courseId = " + courseId + " where id = " + id);
        conn.close();
    }

    public void editBranchForCourseStaffInCourse(int id, int branchId) {
        connection conn = new connection();
        conn.execute("update courseStaff set branchId = " + branchId + " where id = " + id);
        conn.close();
    }

    public void editCourseStaffInCourseForABranch(int staffId, int id) {
        connection conn = new connection();
        conn.execute("update courseStaff set staffId = " + staffId + " where id= " + id);
        conn.close();
    }

    public ArrayList<CourseStaff> getAllCourseStaff() {
        connection conn = new connection();

        try {
            ArrayList<CourseStaff> courseStaffs = new ArrayList<>();
            ResultSet rs = conn.select("SELECT courses.id as courseId, courses.name as courseName,courses.code, courses.labHours, lecHours, courses.SemesterId, semester.number, courses.lectureGroupId, lecturegroups.name, courses.SectionsNumberOfGroupsID, sectionsnumberofgroups.name, courses.StudyplanId, studyplan.name, courses.facultyId, faculty.name, coursesstaff.staffId,staff.name,staff.JobTypeId,jobtype.name,staff.branchId,branch1.name,branch2.id,branch2.name,courseStaff.id   FROM coursesstaff  INNER JOIN courses ON courses.id=coursesstaff.courseId  INNER JOIN sectionsnumberofgroups ON courses.SectionsNumberOfGroupsID=sectionsnumberofgroups.id  INNER JOIN lecturegroups ON lecturegroups.id=courses.lectureGroupId    INNER JOIN semester ON semester.id=courses.SemesterId   INNER JOIN studyplan ON studyplan.id=courses.StudyplanId   INNER JOIN faculty ON faculty.id=courses.facultyId  INNER JOIN staff ON staff.id=coursesstaff.staffId  INNER JOIN jobtype ON jobtype.Id=staff.JobTypeId   INNER JOIN branch AS branch1 ON branch1.id=staff.branchId  INNER JOIN branch AS branch2 ON branch2.id=coursesstaff.BranchId");
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
        connection conn = new connection();

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

        connection conn = new connection();
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
        connection conn = new connection();
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

        connection conn = new connection();
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

        connection conn = new connection();
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
    public void addUser(int id, String username, String password, int role) {
        connection conn = new connection();
        conn.execute("insert into login (id,username, password, role) value(" + id + ",'" + username + "','" + password + "'," + role + ")");
        conn.close();
    }

    public void changeUserPassword(int id, String password) {
        connection conn = new connection();
        conn.execute("update login set password = '" + password + "' where id = " + id);
        conn.close();
    }

    public void editUser(int id, String username, int role) {
        connection conn = new connection();
        conn.execute("update login set username = '" + username + "', role = " + role + " where id = " + id);
        conn.close();
    }

    public void changeUserRole(int id, int role) {
        connection conn = new connection();
        conn.execute("update login set role= '" + role + "' where id = " + id);
        conn.close();
    }

    public ArrayList<user> GetUsers() {
        connection conn = new connection();
        try {
            ArrayList<user> users = new ArrayList<>();
            ResultSet rs = conn.select("select login.id,"
                    + " login.username,"
                    + " login.role,"
                    + " roletype.RoleName,"
                    + " staff.name,"
                    + " staff.JobTypeId,"
                    + " jobtype.name,"
                    + " staff.branchId,"
                    + " branch.name "
                    + "from login "
                    + "inner join staff on staff.id=login.id "
                    + "inner join branch on branch.id=staff.branchId "
                    + "inner join jobtype on jobtype.Id=staff.JobTypeId "
                    + "inner join roletype on roletype.id=login.role; ");
            while (rs.next()) {
                users.add(new user(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        new Staff(rs.getInt(1),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getString(7),
                                rs.getInt(8),
                                rs.getString(9))
                ));
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
        connection conn = new connection();
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

    public ArrayList<timeInTimetable> createLectureTimeTable(String timetableName, course[] courses) {
        Integer[] arr = new Integer[courses.length];
        for (int i = 0; i < courses.length; i++) {
            arr[i] = courses[i].getId();
        }
        com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable t
                = createTimeTable(timetableName);
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
        connection conn = new connection();

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
        connection conn = new connection();

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
        connection conn = new connection();
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
        connection conn = new connection();

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
        connection conn = new connection();

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
            t1.add(
                    new timeInTimetable(
                            new Staff(t.get(i).getStaff().getId()),
                            new course(t.get(i).getCourse().getId()),
                            branchs,
                            new Branch(t.get(i).getHostingBranch().getId()),
                            rooms,
                            new room(t.get(i).getHostingRoom().getId()),
                            t.get(i).getDay(),
                            t.get(i).getStartingTime(),
                            t.get(i).getEndingTime(),
                            t.get(i).getSectionGroupName(),
                            (t.get(i).getLecGroup() != null) ? new LecGroup(t.get(i).getLecGroup().getId()) : null,
                            new com.team.timetableManagmentSystem.DTOs.Timetable(t.get(i).getTimetable().getId())));
        }
        return t1;
    }

    public void addFreeTimeForStaff(freeTime freeTime) {
        connection conn = new connection();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freeTime.getDayStartEnd().size(); i++) {
            sb.append("(").append(freeTime.getId()).append(",").append(freeTime.getDayStartEnd().get(i).getDayId()).append(",").append(freeTime.getDayStartEnd().get(i).getStartSession()).append(",").append(freeTime.getDayStartEnd().get(i).getEndSession()).append("),");
        }
        conn.execute("insert into freetimeforsatff (staffid,dayid,startingtime,enddingtime) values " + sb.substring(0, sb.length() - 1));
        conn.close();
    }

    public Object getFreeTimeForStaff() {

        connection conn = new connection();
        ArrayList<freetimeForStaff> f = new ArrayList<>();
        try {
            ResultSet rs;
            rs = conn.select("select  staff.id as satffId,staff.name,freetimeforstaff.DayId,freetimeforstaff.startingTime,freetimeforstaff.enddingTime,freetimeforstaff.id from freetimeforstaff inner join staff on staff.id = freetimeforstaff.staffid;");
            while (rs.next()) {
                f.add(new freetimeForStaff(rs.getInt("id"), new node(rs.getInt("DayId"), rs.getInt("startingTime"), rs.getInt("enddingTime")), rs.getInt("satffId"), rs.getString("name")));
            }
        } catch (Exception e) {
            System.out.println(5 + "" + e);
        } finally {
            conn.close();
        }
        return f;
    }

    public void updateFreeTimeForStaff(freeTime freeTime) {
        connection conn = new connection();
        conn.execute("delete from freetimeforstaff where StaffId = " + freeTime.getId());
        addFreeTimeForStaff(freeTime);
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public void addFreeTimeForRooms(int RoomId, freeTime freeTime) {
        connection conn = new connection();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freeTime.getDayStartEnd().size(); i++) {
            sb.append("(").append(freeTime.getId()).append(",").append(freeTime.getDayStartEnd().get(i).getDayId()).append(",").append(freeTime.getDayStartEnd().get(i).getStartSession()).append(",").append(freeTime.getDayStartEnd().get(i).getEndSession()).append("),");
        }
        conn.execute("insert into freetimeforrooms(roomid,dayid,startingtime,enddingtime) values " + sb.substring(0, sb.length() - 1));
        conn.close();
    }

    public Object getFreeTimeForRooms() {
        connection conn = new connection();
        ArrayList<FreeTimeForRooms> f = new ArrayList<>();
        try {
            ResultSet rs;
            rs = conn.select("select  rooms.id as roomId,rooms.name,freetimeforrooms.DayId,freetimeforrooms.startingTime,freetimeforrooms.enddingTime,freetimeforrooms.id from freetimeforrooms inner join rooms on rooms.id = freetimeforrooms.RoomId;");
            while (rs.next()) {
                f.add(new FreeTimeForRooms(rs.getInt("id"), new node(rs.getInt("DayId"), rs.getInt("startingTime"), rs.getInt("enddingTime")), rs.getInt("roomId"), rs.getString("name")));
            }
            return f;
        } catch (Exception e) {
            System.out.println(5 + "" + e);
        } finally {
            conn.close();
        }
        return f;
    }

    public freeTime getFreeTimeForARoom(int RoomId) {
        connection conn = new connection();
        freeTime f = new freeTime();
        try {
            ResultSet rs;
            rs = conn.select("select * from freetimeforRooms where RoomId =" + RoomId);
            while (rs.next()) {
                f.getDayStartEnd().add(new node(rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
            System.out.println(5 + "" + e);
        } finally {
            conn.close();
        }
        return f;
    }

    public void updateFreeTimeForRooms(int RoomsId, freeTime freeTime) {
        connection conn = new connection();
        conn.execute("delete from freetimeforstaff where StaffId = " + RoomsId);
        addFreeTimeForRooms(RoomsId, freeTime);
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<timeInTimetable> getATimetable(int timetableId) {
        connection conn = new connection();
        ArrayList<timeInTimetable> tims = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        ResultSet rs = conn.select("select timesintimetable.id,"
                + "timesintimetable.staffId,"
                + " staff.name,"
                + " timesintimetable.courseId,"
                + " courses.name,"
                + "courses.code,"
                + "timesintimetable.hostingBranchId,"
                + "branch.name,"
                + "timesintimetable.hostingRoomId,"
                + "rooms.name,"
                + "timesintimetable.dayid,"
                + "timesintimetable.startingTime,"
                + "timesintimetable.enddingTime,"
                + "timesintimetable.lecGroupId, "
                + "lecgroup.name "
                + "from timesintimetable "
                + "LEFT JOIN staff on staff.id=timesintimetable.staffId "
                + "left join courses on courses.id=timesintimetable.courseId "
                + "left join branch on branch.id=timesintimetable.hostingbranchId "
                + "left join rooms on rooms.id=timesintimetable.hostingRoomId "
                + "left join lecgroup on lecgroup.id=timesintimetable.lecgroupId "
                + "order by timesintimetable.id");
        try {
            while (rs.next()) {
                sb.append(rs.getInt(1)).append(",");
                timeInTimetable t = new timeInTimetable(rs.getInt(1),
                        new Staff(rs.getInt(2), rs.getString(3)),
                        new course(rs.getInt(4), rs.getString(5), rs.getString(6), 0, 0, 0, 0, 0, null, 0, null, 0, null, 0, null),
                        new ArrayList<Branch>(),
                        new Branch(rs.getInt(7), rs.getString(8)),
                        new ArrayList<room>(),
                        new room(rs.getInt(9), rs.getString(10)),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), null,
                        new LecGroup(rs.getInt(14), rs.getString(15)), null);
                tims.add(t);
            }
            rs = conn.select("select timeInTimetableId, branchId ,branch.name from  branchesintimeintimetable left join branch on branch.id=branchId where timeInTimetableId in (" + sb.substring(0, sb.length() - 1) + ") order by timeInTimetableId");
            int i = 0;
            while (rs.next()) {
                for (int j = i; j < tims.size(); j++) {
                    if (rs.getInt(1) == tims.get(j).getId()) {
                        i = j;
                        tims.get(j).getBranchs().add(new Branch(rs.getInt(2), rs.getString(3)));
                        break;
                    }
                }
            }

            rs = conn.select("select timeInTimetableId, RoomId ,rooms.name,rooms.branchId from  roomsintimeintimetable left join rooms on rooms.id=roomid where timeInTimetableId in (" + sb.substring(0, sb.length() - 1) + ") order by timeInTimetableId");
            i = 0;
            while (rs.next()) {
                for (int j = i; j < tims.size(); j++) {
                    if (rs.getInt(1) == tims.get(j).getId()) {
                        i = j;
                        tims.get(j).getRooms().add(new room(rs.getInt(2), rs.getString(3), 0, 0, null, rs.getInt(4), null));
                        break;
                    }
                }
            }

            rs = conn.select("select * from sectiongroupnamefortimeintimetable order by timeInTimetableId");
            i = 0;
            while (rs.next()) {
                for (int j = i; j < tims.size(); j++) {
                    if (rs.getInt(1) == tims.get(j).getId()) {
                        i = j;
                        tims.get(j).setSectionGroupName(rs.getString(2));
                        break;
                    }
                }
            }
            return tims;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable createTimeTable(String timetableName) {
        connection conn = new connection();
        conn.makeStatement();
        com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable t = new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Timetable();
        try {
            int affectedRows = conn.stat.executeUpdate("insert into timetable (name) values ('" + timetableName + "')", Statement.RETURN_GENERATED_KEYS);

            if (affectedRows > 0) {
                try (ResultSet rs = conn.stat.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setId(rs.getInt(1));
                    }
                }
            }
            return t;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Object getCourseStaff() {
        ArrayList<CourseStaff> cs = new ArrayList<>();
        connection conn = new connection();
        ResultSet rs = conn.select("select coursesstaff.courseId ,courses.name as courseName,coursesstaff.staffId,staff.name as staffName,coursesstaff.BranchId,branch.name as branchName "
                + "from coursesstaff "
                + "inner join courses on courses.id=coursesstaff.courseId "
                + "inner join staff on staff.id=coursesstaff.staffId "
                + "inner join branch on branch.id=coursesstaff.BranchId;");
        try {
            while (rs.next()) {
                cs.add(new CourseStaff(rs.getInt("courseId"), rs.getString("courseName"), rs.getInt("staffId"), rs.getString("staffName"), rs.getInt("BranchId"), rs.getString("branchName")));
            }
            return cs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getCourseSectionStaff() {
        ArrayList<CourseSectionStaff> cs = new ArrayList<>();
        connection conn = new connection();
        ResultSet rs = conn.select("select sectiongroupsstaffs.courseId,courses.name as courseName,sectiongroupsstaffs.staffId,staff.name as staffName,sectiongroupsstaffs.branchId,branch.name as branchName,sectiongroupsstaffs.GroupNumber "
                + "from sectiongroupsstaffs "
                + "inner join courses on courses.id= sectiongroupsstaffs.courseId "
                + "inner join staff on staff.id = sectiongroupsstaffs.staffId "
                + "inner join branch on branch.id = sectiongroupsstaffs.branchId;");
        try {
            while (rs.next()) {
                cs.add(new CourseSectionStaff(rs.getInt("courseId"), rs.getString("courseName"), rs.getInt("staffId"), rs.getString("staffName"), rs.getInt("BranchId"), rs.getString("branchName"), rs.getInt("GroupNumber")));
            }
            return cs;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getSectionGroups() {
        connection conn = new connection();
        ResultSet rs = conn.select("SELECT id,name FROM sectionsnumberofgroups;");
        try {
            ArrayList<SectionGroup> sg = new ArrayList<>();
            while (rs.next()) {
                sg.add(new SectionGroup(rs.getInt("id"), rs.getString("name")));
            }
            return sg;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getSectionGroupsBranches() {
        connection conn = new connection();
        ResultSet rs = conn.select("select sectionsnumberofgroupsId,sectionsnumberofgroups.name as sectionGroupName,sectionsfgroups.branchId,branch.name as branhName,sectionsfgroups.numberOfGroups "
                + "from sectionsfgroups "
                + "inner join branch on branch.id=sectionsfgroups.branchId "
                + "inner join sectionsnumberofgroups on sectionsnumberofgroupsId=sectionsnumberofgroups.id;");
        try {
            ArrayList<sectionGroupBranchs> sgb = new ArrayList<>();
            while (rs.next()) {
                sgb.add(new sectionGroupBranchs(rs.getInt("numberOfGroups"), rs.getInt("branchId"), rs.getString("branhName"), rs.getInt("sectionsnumberofgroupsId"), rs.getString("sectionGroupName")));
            }
            return sgb;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getCourseNames() {
        connection conn = new connection();
        ResultSet rs = conn.select("select id,name from courses;");
        try {
            ArrayList<Course> c = new ArrayList<>();
            while (rs.next()) {
                c.add(new Course(rs.getInt("id"), rs.getString("name")));

            }
            return c;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getStaffNames() {
        connection conn = new connection();
        ResultSet rs = conn.select("select id ,name from staff;");
        try {
            ArrayList<Staff> staff = new ArrayList<>();
            while (rs.next()) {
                staff.add(new Staff(rs.getInt("id"), rs.getString("name")));

            }
            return staff;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getAllLectuerGroupsWithLecgroups() {
        ArrayList<LectureGroup> lec = new ArrayList<>();
        ArrayList<LecGroup> lecs = (ArrayList<LecGroup>) getAllLecGroups();
        if (lecs != null && lecs.size() > 0) {
            for (int i = 0; i < lecs.size(); i++) {
                for (int j = 0; j <= lec.size(); j++) {
                    if (j == lec.size()) {
                        LectureGroup l1 = new LectureGroup(lecs.get(i).getLectuerGoup().getId(), lecs.get(i).getLectuerGoup().getName());
                        l1.setLecgroups(new ArrayList<>());
                        l1.getLecgroups().add(new LecGroup(lecs.get(i).getId(), lecs.get(i).getName()));
                        lec.add(l1);
                        break;
                    } else if (lecs.get(i).getLectuerGoup().getId() == lec.get(j).getId()) {
                        lec.get(j).getLecgroups().add(new LecGroup(lecs.get(i).getId(), lecs.get(i).getName()));
                        break;
                    }
                }
            }
            return lec;
        }
        return null;
    }

    public Object getAllFacultysWithThereStudyPlansAndStemesters() {
        ArrayList<Semester> semesters = getAllSemesters();
        Map<Integer, Faculty> facultyMap = new HashMap<>();
        Map<Integer, StudyPlan> studyPlanMap = new HashMap<>();

        if (semesters != null && !semesters.isEmpty()) {
            for (Semester semester : semesters) {
                StudyPlan originalStudyPlan = semester.getStudyPlan();
                Faculty originalFaculty = originalStudyPlan.getFaculty();

                Faculty faculty;
                StudyPlan studyPlan;
                Semester newSemester = new Semester();

                // Manually copy fields from the original Semester
                newSemester.setId(semester.getId());
                newSemester.setNumber(semester.getNumber());
                // Copy other necessary fields...

                if (!facultyMap.containsKey(originalFaculty.getId())) {
                    faculty = new Faculty();
                    // Manually copy fields from the original Faculty
                    faculty.setId(originalFaculty.getId());
                    faculty.setName(originalFaculty.getName());
                    // Copy other necessary fields...
                    faculty.setStudyPlans(new ArrayList<>());
                    facultyMap.put(faculty.getId(), faculty);
                } else {
                    faculty = facultyMap.get(originalFaculty.getId());
                }

                if (!studyPlanMap.containsKey(originalStudyPlan.getId())) {
                    studyPlan = new StudyPlan();
                    // Manually copy fields from the original StudyPlan
                    studyPlan.setId(originalStudyPlan.getId());
                    studyPlan.setName(originalStudyPlan.getName());
                    // Copy other necessary fields...
                    studyPlan.setSemesters(new ArrayList<>());
                    studyPlanMap.put(studyPlan.getId(), studyPlan);
                    faculty.getStudyPlans().add(studyPlan);
                } else {
                    studyPlan = studyPlanMap.get(originalStudyPlan.getId());
                }

                studyPlan.getSemesters().add(newSemester);
            }

            return new ArrayList<>(facultyMap.values());
        }

        return null;
    }

    public Object getTimetableNames() {
        connection conn = new connection();
        ResultSet rs = conn.select("select id, name from timetable");
        try {
            ArrayList<Timetable> timetables = new ArrayList<>();
            while (rs.next()) {
                timetables.add(new Timetable(rs.getInt("id"), rs.getString("name")));
            }
            return timetables;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    public Object getFacutlysWithStudyPlansWithSemesters() {
        ArrayList<Semester> semesters = getAllSemesters();
        HashMap<Integer, Faculty> facultyMap = new HashMap<>();
        HashMap<Integer, StudyPlan> studyPlanMap = new HashMap<>();

        if (semesters != null && !semesters.isEmpty()) {
            for (Semester semester : semesters) {
                int facultyId = semester.getStudyPlan().getFaculty().getId();
                int studyPlanId = semester.getStudyPlan().getId();

                Faculty faculty = facultyMap.getOrDefault(facultyId,
                        new Faculty(facultyId, semester.getStudyPlan().getFaculty().getName()));
                StudyPlan studyPlan = studyPlanMap.getOrDefault(studyPlanId,
                        new StudyPlan(studyPlanId, semester.getStudyPlan().getName(), 0, null));

                Semester s = new Semester(semester.getId(), semester.getNumber(), 0, null, 0, null);
                studyPlan.getSemesters().add(s);

                if (!faculty.getStudyPlans().contains(studyPlan)) {
                    faculty.getStudyPlans().add(studyPlan);
                }

                facultyMap.put(facultyId, faculty);
                studyPlanMap.put(studyPlanId, studyPlan);
            }
        }

        return new ArrayList<>(facultyMap.values());
    }

}
