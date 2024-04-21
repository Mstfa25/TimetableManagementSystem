package com.team.timetableManagmentSystem.controllers;

import com.team.timetableManagmentSystem.DTOs.*;
import com.team.timetableManagmentSystem.service.InsertTimetable;
import com.team.timetableManagmentSystem.service.adminService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class adminController {

    @Autowired
    private adminService adminService;

    public ResponseEntity<?> adminResponse(HttpSession session) {
        ArrayList<String> s = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals(1)) {
                s.add("done");
                return new ResponseEntity<>(s, HttpStatus.OK);
            } else {
                s.add("NotAdmin");
                return new ResponseEntity<>(s, HttpStatus.OK);
            }
        } else {
            s.add("login first");
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
    }

    @PostMapping("/addBranch")
    public ResponseEntity<?> addBranch(@RequestBody Branch branch, HttpSession session) {
        if (isadmin(session)) {
            adminService.insertNewBranch(branch.getName());
        }
        return adminResponse(session);

    }

    @PostMapping("/addBranches")
    public ResponseEntity<?> addBranches(HttpSession session, @RequestBody Branch... branchs) {
        if (isadmin(session)) {
            StringBuilder sb = new StringBuilder();
            for (Branch branch : branchs) {
                sb.append("('").append(branch.getName()).append("'),");
            }
            adminService.insertNewBranches(sb.toString().substring(0, sb.toString().length() - 1));
        }
        return adminResponse(session);

    }

    @PostMapping("getAllBranches")
    public Object getAllBranches(HttpSession session) {
        if (isadmin(session)) {
            ArrayList<Branch> branches = adminService.getAllBranches();
            return branches;
        }
        return adminResponse(session);
    }

    @PostMapping("editBranchName")
    public ResponseEntity<?> editBranchName(@RequestBody Branch branch, HttpSession session) {
        if (isadmin(session)) {
            adminService.editBranchName(branch.getId(), branch.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("removeBranch")
    public ResponseEntity<?> RemoveBranch(@RequestBody Branch branch, HttpSession session) {
        if (isadmin(session)) {
            adminService.removeBranch(branch.getId());
        }
        return adminResponse(session);
    }

    @GetMapping("getAllRoomsInAllBranches")
    public Object GetallRoomsInAllBranches(HttpSession session) {
        if (isadmin(session)) {
            return adminService.GetallRoomsInAllBranches();
        }
        return adminResponse(session);
    }

    @PostMapping("getAllRoomsInOneBranch")
    public Object GetallRoomsInOneBranch(HttpSession session, @RequestBody Branch branch) {
        if (isadmin(session)) {
            return adminService.GetallRoomsInoneBranch(branch.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("DeleteRoom")
    public ResponseEntity<?> DeleteRoom(HttpSession session, @RequestBody room room) {
        if (isadmin(session)) {
            adminService.removeRoom(room.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addRoom")
    public ResponseEntity<?> addRoom(HttpSession session, @RequestBody room room) {
        if (isadmin(session)) {
            adminService.addRoom(room.getName(), room.getCapacity(), room.getRoomtype().getId(), room.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addRooms")
    public ResponseEntity<?> addRooms(HttpSession session, @RequestBody room... rooms) {
        if (isadmin(session)) {
            StringBuilder sb = new StringBuilder();
            for (room room : rooms) {
                sb.append("( '").append(room.getName()).append("' ,").append(room.getCapacity()).append(",").append(room.getRoomtype().getId()).append(",").append(room.getBranch().getId()).append("),");
            }
            adminService.AddRooms(sb.toString().substring(0, sb.toString().length() - 1));
        }
        return adminResponse(session);
    }

    @PostMapping("addStaff")
    public ResponseEntity<?> addStaff(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            adminService.addStaff(staff.getName(), staff.getType().getId(), staff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addStaffs")
    public ResponseEntity<?> addStaff(HttpSession session, @RequestBody Staff... staffs) {
        if (isadmin(session)) {
            StringBuilder sb = new StringBuilder();
            for (Staff staff : staffs) {
                sb.append("('").append(staff.getName()).append("',").append(staff.getType().getId()).append(",").append(staff.getBranch().getId()).append("),");
            }
            adminService.addStaffs(sb.toString().substring(0, sb.length() - 1));
        }
        return adminResponse(session);
    }

    @PostMapping("getAllStaff")
    public Object getAllStaffInAllBranches(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getAllStaffInAllBranches();
        }
        return adminResponse(session);
    }

    @PostMapping("getAllStaffInOneBranch")
    public Object getAllStaffInOneBranch(HttpSession session, @RequestBody Branch branch) {
        if (isadmin(session)) {
            return adminService.getAllStaffInOneBranch(branch.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllStaffWithType")
    public Object getAllStaffWithType(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            return adminService.getAllStaffInAllBranchesWithType(staff.getType().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllStaffWithTypeInOneBranch")
    public Object getAllStaffWithTypeInOneBranch(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            return adminService.getAllStaffInAllBranchesWithTypeInOneBranch(staff.getBranch().getId(), staff.getType().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editStaffName")
    public ResponseEntity<?> editStaffName(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            adminService.EditStaffName(staff.getId(), staff.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("editStaffType")
    public ResponseEntity<?> editStaffType(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            adminService.editStaffType(staff.getId(), staff.getType().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editStaffBranch")
    public ResponseEntity<?> editStaffBranch(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            adminService.editStaffBranch(staff.getId(), staff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("removeStaff")
    public ResponseEntity<?> removeStaff(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            adminService.removeStaff(staff.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addFaculty")
    public ResponseEntity<?> addFaculty(@RequestBody Faculty faculty, HttpSession session) {
        if (isadmin(session)) {
            adminService.addFaculty(faculty.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("getFacultys")
    public Object getFacultys(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getFacultys();
        }
        return adminResponse(session);
    }

    @PostMapping("deleteFaculty")
    public ResponseEntity<?> deleteFaculty(@RequestBody Faculty faculty, HttpSession session) {
        if (isadmin(session)) {
            adminService.removeFaculty(faculty.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editFacultyName")
    public ResponseEntity<?> editFacultyName(HttpSession session, @RequestBody Faculty faculty) {
        if (isadmin(session)) {
            adminService.EditFacultyName(faculty.getId(), faculty.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("addStudyPlan")
    public ResponseEntity<?> addStudyPlan(HttpSession session, @RequestBody StudyPlan studyPlan) {
        if (isadmin(session)) {
            adminService.insertStudyPlan(studyPlan.getName(), studyPlan.getFaculty().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editStudyPlanName")
    public ResponseEntity<?> editStudyPlanName(HttpSession session, @RequestBody StudyPlan studyPlan) {
        if (isadmin(session)) {
            adminService.editStudyPlanName(studyPlan.getId(), studyPlan.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("editStudyFaculty")
    public ResponseEntity<?> editStudyPlanFaculty(@RequestBody StudyPlan studyPlan, HttpSession session) {
        if (isadmin(session)) {
            adminService.editStudyPlanfaculty(studyPlan.getId(), studyPlan.getFaculty().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("removeStudyPlan")
    public ResponseEntity<?> removeStudyPlan(@RequestBody StudyPlan studyPlan, HttpSession session) {
        if (isadmin(session)) {
            adminService.removeStudyPlans(studyPlan.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllStudyPlans")
    public Object getAllStudyPlans(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getAllStudyPlans();
        }
        return adminResponse(session);
    }

    @PostMapping("getStudyPlansInFaculty")
    public Object getStudyPlansInFaculty(HttpSession session, @RequestBody Faculty faculty) {
        if (isadmin(session)) {
            return adminService.getStudyPlansInFaculty(faculty.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addSemester")
    public ResponseEntity<?> addSemester(HttpSession session, @RequestBody Semester semester) {
        if (isadmin(session)) {
            adminService.addSemester(semester.getNumber(), semester.getStudyPlan().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editSemesterNumber")
    public ResponseEntity<?> editSemesterNumber(HttpSession session, @RequestBody Semester semester) {
        if (isadmin(session)) {
            adminService.editSemesterNumber(semester.getId(), semester.getNumber());
        }
        return adminResponse(session);
    }

    @PostMapping("editSemesterStudyPlan")
    public ResponseEntity<?> editSemesterStudyPlan(HttpSession session, @RequestBody Semester semester) {
        if (isadmin(session)) {
            adminService.editSemesterStudyPlan(semester.getId(), semester.getStudyPlan().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("removeSemester")
    public ResponseEntity<?> removeSemester(HttpSession session, @RequestBody Semester semester) {
        if (isadmin(session)) {
            adminService.removeSemester(semester.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllSemesters")
    public Object getAllSemesters(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getAllSemesters();
        }
        return adminResponse(session);
    }

    @PostMapping("getSemestersInStudyPlan")
    public Object getSemestersInStudyPlan(HttpSession session, @RequestBody StudyPlan studyPlan) {
        if (isadmin(session)) {
            return adminService.getAllSemestersInStudyPlan(studyPlan.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getSemestersInFaculty")
    public Object getSemestersInfaculty(HttpSession session, @RequestBody Faculty faculty) {
        if (isadmin(session)) {
            return adminService.getAllSemestersInFaculty(faculty.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("createUser")
    public Object addUser(@RequestBody user user, HttpSession session) {
        if (isadmin(session)) {
            if (user.getUsername() != null && user.getPassword() != null && user.getRole() != 0) {
                adminService.addUser(user.getUsername(), user.getPassword(), user.getRole());
            } else {
                System.out.println("username : " + user.getUsername() + "\npassword : " + user.getPassword() + "\nrole : " + user.getRole());
            }
        }
        return adminResponse(session);
    }

    @PostMapping("changeUserRole")
    public Object changRole(@RequestBody user user, HttpSession session) {
        if (isadmin(session)) {
            if (user.getId() != 0 && user.getRole() != 0) {
                adminService.changeUserRole(user.getId(), user.getRole());
            }
        }
        return adminResponse(session);
    }

    @PostMapping("getAllUsers")
    public Object getAllUsers(HttpSession session) {
        if (isadmin(session)) {
            return adminService.GetUsers();
        }
        return adminResponse(session);
    }

    @PostMapping("editUserPassword")
    public Object editUserPassword(@RequestBody user user, HttpSession session) {
        if (isadmin(session)) {
            adminService.changeUserPassword(user.getId(), user.getPassword());
        }
        return adminResponse(session);
    }

    @PostMapping("createLectuerTimetable")
    public Object createLectureTimetable(HttpSession session, @RequestBody course... courses) {
        if (isadmin(session)) {
            ArrayList<timeInTimetable> t = adminService.createLectureTimeTable(courses);
            InsertTimetable insertTimeTable = new InsertTimetable((timeInTimetable[]) t.toArray(new timeInTimetable[t.size()]));
            insertTimeTable.start();
            return t;
        }
        return adminResponse(session);
    }

    @PostMapping("viewTimetable")
    public Object viewTimetable(HttpSession session, @RequestBody Timetable timetable) {
        if (isadmin(session)) {
            return adminService.getLectuerTimesInTimetable(timetable.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getFreeTimeForStaff")
    public Object getFreeTimeForStaff(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            return adminService.getFreeTimeForStaff(staff.getId()).dayStartEnd;
        }
        return adminResponse(session);
    }

    @PostMapping("addFreeTimeForStaff")
    public ResponseEntity<?> addFreeTimeForStaff(HttpSession session, @RequestBody Staff staff, freeTime freeTime) {
        if (isadmin(session)) {
            adminService.addFreeTimeForStaff(staff.getId(), freeTime);
        }
        return adminResponse(session);
    }

    @PostMapping("addFreeTimeForRoom")
    public ResponseEntity<?> addFreeTimeForRoom(HttpSession session, @RequestBody room room, freeTime freeTime) {
        if (isadmin(session)) {
            adminService.addFreeTimeForRooms(room.getId(), freeTime);
        }
        return adminResponse(session);
    }

    @PostMapping("editFreeTimeForStaff")
    public ResponseEntity<?> editFreeTimeForStaff(HttpSession session, @RequestBody Staff staff, freeTime freeTime) {
        if (isadmin(session)) {
            adminService.updateFreeTimeForStaff(staff.getId(), freeTime);
        }
        return adminResponse(session);
    }

    @PostMapping("editFreeTimeForRoom")
    public ResponseEntity<?> editFreeTimeForRoom(HttpSession session, @RequestBody room room, freeTime freeTime) {
        if (isadmin(session)) {
            adminService.updateFreeTimeForRooms(room.getId(), freeTime);
        }
        return adminResponse(session);
    }

    @PostMapping("addLectureGroup")
    public ResponseEntity<?> addLectureGroup(HttpSession session, @RequestBody LectureGroup lectuerGoup) {
        if (isadmin(session)) {
            adminService.addLectureGroup(lectuerGoup.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("editLectuerGroupName")
    public ResponseEntity<?> editLectuerGroupName(HttpSession session, @RequestBody LectureGroup lectuerGroup) {
        if (isadmin(session)) {
            adminService.editLectuerGroupName(lectuerGroup.getId(), lectuerGroup.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("removeLectuerGroup")
    public ResponseEntity<?> removeLectuerGroup(HttpSession session, @RequestBody LectureGroup lectureGroup) {
        if (isadmin(session)) {
            adminService.removeLectuerGroup(lectureGroup.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllLectuerGroups")
    public Object getAllLectuerGroups(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getAllLectuerGroups();
        }
        return adminResponse(session);
    }

    @PostMapping("addLecGroupInALectuerGroup")
    public ResponseEntity<?> addLecGroupInALectuerGroup(HttpSession session, @RequestBody LecGroup lecGroup) {
        if (isadmin(session)) {
            adminService.addLecGroupInALectuerGroup(lecGroup.getLectuerGoup().getId(), lecGroup.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("editLecGroupInALectuerGroupName")
    public ResponseEntity<?> editLecGroupInALectuerGroupName(HttpSession session, @RequestBody LecGroup lecGroup) {
        if (isadmin(session)) {
            adminService.editLecGroupInALectuerGroupName(lecGroup.getId(), lecGroup.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("editLecGroupInALectuerGroupLectuerGroup")
    public ResponseEntity<?> editLecGroupInALectuerGroupLectuerGroup(HttpSession session, @RequestBody LecGroup lecGroup) {
        if (isadmin(session)) {
            adminService.editLecGroupInALectuerGroupLectuerGroup(lecGroup.getId(), lecGroup.getLectuerGoup().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("removeLecGroupInALectuerGroupLectuerGroup")
    public ResponseEntity<?> removeLecGroupInALectuerGroupLectuerGroup(HttpSession session, LecGroup lecGroup) {
        if (isadmin(session)) {
            adminService.removeLecGroupInALectuerGroupLectuerGroup(lecGroup.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addBranchInALecGroup")
    public ResponseEntity<?> addBranchInALecGroup(HttpSession session, @RequestBody LecGroup lecGroup) {
        if (isadmin(session)) {
            adminService.addBranchInALecGroup(lecGroup.getBranchs().get(0).getId(), lecGroup.getId());
        }
        return adminResponse(session);

    }

    @PostMapping("removeBranchFromLecGroup")
    public ResponseEntity<?> removeBranchFromLecGroup(HttpSession session, @RequestBody LecGroup lecGroup) {
        if (isadmin(session)) {
            adminService.removeBranchFromLecGroup(lecGroup.getBranchs().get(0).getId(), lecGroup.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllBranchesInLecGroup")
    public Object getAllBranchesInLecGroup(HttpSession session, @RequestBody LecGroup lecGroup) {
        if (isadmin(session)) {
            return adminService.getAllBranchesInLecGroup(lecGroup.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("addSectionGroup")
    public ResponseEntity<?> addSectionGroup(HttpSession session, @RequestBody SectionGroup sectionGroup) {
        if (isadmin(session)) {
            adminService.addSectionGroup(sectionGroup.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("editSectionGroupName")
    public ResponseEntity<?> editSectionGroupName(HttpSession session, @RequestBody SectionGroup sectionGroup) {
        if (isadmin(session)) {
            adminService.editSectionGroupName(sectionGroup.getId(), sectionGroup.getName());
        }
        return adminResponse(session);
    }

    @PostMapping("removeSectionGroup")
    public ResponseEntity<?> removeSectionGroup(HttpSession session, @RequestBody SectionGroup sectionGroup) {
        if (isadmin(session)) {
            adminService.removeSectionGroup(sectionGroup.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllSectionGroups")
    public Object getAllSectionGroups(HttpSession session) {
        if (isadmin(session)) {
            adminService.getAllSectionGroups();
        }
        return adminResponse(session);
    }

    @PostMapping("insertBranchInSectionGroup")
    public ResponseEntity<?> insertBranchInSectionGroup(HttpSession session, @RequestBody sectionGroupBranchs sectionGroupBranchs) {
        if (isadmin(session)) {
            adminService.insertBranchInSectionGroup(sectionGroupBranchs.getBranchWithNumberOfSectionGroupses().get(0).getBranch().getId(), sectionGroupBranchs.getSectionGroup().getId(), sectionGroupBranchs.getBranchWithNumberOfSectionGroupses().get(0).getNumberOfSectionGroups());
        }
        return adminResponse(session);
    }

    @PostMapping("editBranchSectionGroupnumberOfGroups")
    public ResponseEntity<?> editBranchSectionGroupnumberOfGroups(HttpSession session, @RequestBody sectionGroupBranchs sectionGroupBranchs) {
        if (isadmin(session)) {
            adminService.editBranchSectionGroupnumberOfGroups(sectionGroupBranchs.getBranchWithNumberOfSectionGroupses().get(0).getBranch().getId(), sectionGroupBranchs.getSectionGroup().getId(), sectionGroupBranchs.getBranchWithNumberOfSectionGroupses().get(0).getNumberOfSectionGroups());
        }
        return adminResponse(session);
    }

    @PostMapping("changeBranchSectionGroup")
    public ResponseEntity<?> changeBranchSectionGroup(HttpSession session, @RequestBody sectionGroupBranchs sectionGroupBranchs, @RequestBody sectionGroupBranchs sectionGroupBranchs1) {

        if (isadmin(session)) {
            adminService.changeBranchSectionGroup(sectionGroupBranchs.getBranchWithNumberOfSectionGroupses().get(0).getBranch().getId(), sectionGroupBranchs.getSectionGroup().getId(), sectionGroupBranchs1.getSectionGroup().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("removeBranchFromSectionGroup")
    public ResponseEntity<?> removeBranchFromSectionGroup(HttpSession session, sectionGroupBranchs sectionGroupBranchs) {

        if (isadmin(session)) {
            adminService.removeBranchFromSectionGroup(sectionGroupBranchs.getBranchWithNumberOfSectionGroupses().get(0).getBranch().getId(), sectionGroupBranchs.getSectionGroup().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getSectionGroupBranchs")
    public Object getSectionGroupBranchs(HttpSession session, @RequestBody SectionGroup sectionGroup) {
        if (isadmin(session)) {
            return adminService.getSectionGroupBranchs(sectionGroup.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("insertCourse")
    public ResponseEntity<?> insertCourse(HttpSession session, @RequestBody course course) {
        if (isadmin(session)) {
            adminService.insertCourse(course.getName(), course.getCode(), course.getLectureHours(), course.getLabHours(), course.getSemester().getId(), course.getStudyPlan().getId(), course.getFaculty().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("setLecGroupIdForCourse")
    public ResponseEntity<?> setLecGroupIdForCourse(HttpSession session, @RequestBody course course) {
        if (isadmin(session)) {
            adminService.setLecGroupIdForCourse(course.getId(), course.getLectuerGoup().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("setSectionGroupIdForCourse")
    public ResponseEntity<?> setSectionGroupIdForCourse(HttpSession session, course course) {
        if (isadmin(session)) {
            adminService.setSectionGroupIdForCourse(course.getId(), course.getGroup().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getALLCourses")
    public Object getALLCourses(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getALLCourses();
        }
        return adminResponse(session);
    }

    @PostMapping("getALLCoursesInSemester")
    public Object getALLCoursesInSemester(HttpSession session, @RequestBody Semester semester) {
        if (isadmin(session)) {
            return adminService.getALLCoursesInSemester(semester.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getALLCoursesInFaculty")
    public Object getALLCoursesInFaculty(HttpSession session, @RequestBody Faculty faculty) {
        if (isadmin(session)) {
            return adminService.getALLCoursesInFaculty(faculty.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getALLCoursesInStudyPlan")
    public Object getALLCoursesInStudyPlan(HttpSession session, @RequestBody StudyPlan studyPlan) {
        if (isadmin(session)) {
            return adminService.getALLCoursesInStudyPlan(studyPlan.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("insertCourseStaff")
    public ResponseEntity<?> insertCourseStaff(HttpSession session, @RequestBody CourseStaff courseStaff) {
        if (isadmin(session)) {
            adminService.insertCourseStaff(courseStaff.getCourse().getId(), courseStaff.getStaff().getId(), courseStaff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("deleteCourseStaff")
    public ResponseEntity<?> deleteCourseStaff(HttpSession session, @RequestBody CourseStaff courseStaff) {
        if (isadmin(session)) {
            adminService.deleteCourseStaff(courseStaff.getCourse().getId(), courseStaff.getStaff().getId(), courseStaff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editCourseForCourseStaffInABranch")
    public ResponseEntity<?> editCourseForCourseStaffInABranch(HttpSession session, @RequestBody CourseStaff courseStaff) {
        if (isadmin(session)) {
            adminService.editCourseForCourseStaffInABranch(courseStaff.getCourse().getId(), courseStaff.getStaff().getId(), courseStaff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editBranchForCourseStaffInCourse")
    public ResponseEntity<?> editBranchForCourseStaffInCourse(HttpSession session, @RequestBody CourseStaff courseStaff) {
        if (isadmin(session)) {
            adminService.editBranchForCourseStaffInCourse(courseStaff.getCourse().getId(), courseStaff.getStaff().getId(), courseStaff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("editCourseStaffInCourseForABranch")
    public ResponseEntity<?> editCourseStaffInCourseForABranch(HttpSession session, @RequestBody CourseStaff courseStaff) {
        if (isadmin(session)) {
            adminService.editCourseStaffInCourseForABranch(courseStaff.getCourse().getId(), courseStaff.getStaff().getId(), courseStaff.getBranch().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllCourseStaff")
    public Object getAllCourseStaff(HttpSession session) {
        if (isadmin(session)) {
            return adminService.getAllCourseStaff();
        }
        return adminResponse(session);
    }

    @PostMapping("getAllCourseStaffForACourse")
    public Object getAllCourseStaffForACourse(HttpSession session, @RequestBody course course) {
        if (isadmin(session)) {
            return adminService.getAllCourseStaffForACourse(course.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllCourseStaffInSemester")
    public Object getAllCourseStaffInSemester(HttpSession session, @RequestBody Semester semester) {
        if (isadmin(session)) {
            return adminService.getAllCourseStaffInSemester(semester.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllCourseStaffInStudyPlan")
    public Object getAllCourseStaffInStudyPlan(HttpSession session, StudyPlan studyPlan) {
        if (isadmin(session)) {
            return adminService.getAllCourseStaffInStudyPlan(studyPlan.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllCourseStaffInBranch")
    public Object getAllCourseStaffInBranch(HttpSession session, @RequestBody Branch branch) {
        if (isadmin(session)) {
            return adminService.getAllCourseStaffInBranch(branch.getId());
        }
        return adminResponse(session);
    }

    @PostMapping("getAllStaffTypeInCourseStaffInBranch")
    public Object getAllStaffTypeInCourseStaffInBranch(HttpSession session, @RequestBody Staff staff) {
        if (isadmin(session)) {
            return adminService.getAllStaffTypeInCourseStaffInBranch(staff.getBranch().getId(), staff.getType().getId());
        }
        return adminResponse(session);
    }

    @PostMapping("createSectionTimetable")
    public Object createSectionTimetable(HttpSession session, @RequestBody course[] courses) {

        if (isadmin(session)) {
            ArrayList<timeInTimetable> t = adminService.createSectionTimeTable(0, 1, courses);
            InsertTimetable insertTimeTable = new InsertTimetable((timeInTimetable[]) t.toArray(new timeInTimetable[t.size()]));
            insertTimeTable.start();
            return t;
        }
        return adminResponse(session);
    }

    boolean isadmin(HttpSession session) {
        return session.getAttribute("role") != null
                && session.getAttribute("role").equals(1);
    }

}
