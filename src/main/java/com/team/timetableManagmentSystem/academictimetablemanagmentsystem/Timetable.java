package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tomcat.util.digester.ArrayStack;

/**
 * used in the operation of creating timeTable
 *
 * @data_field id to specify the timetable
 * @data_field name to specify the name of the timetable
 * @data_field timesInTimetable to specify the timesInTimetable in the timetable
 * @author Mostafa
 */
public class Timetable {

    private int id;
    private String name;
    private ArrayList<TimeInTimetable> timesInTimetable;

    public Timetable() {
        timesInTimetable = new ArrayList<>();
    }

    public Timetable(int id, String name, ArrayList<TimeInTimetable> timesInTimetable) {
        this.id = id;
        this.name = name;
        this.timesInTimetable = timesInTimetable;
    }

    public Timetable(int id) {
        this.id = id;
        timesInTimetable = new ArrayList<>();

    }

    public Timetable(int id, String name) {
        this.id = id;
        this.name = name;
        timesInTimetable = new ArrayList<>();

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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
     * @return the timesInTimetable
     */
    public ArrayList<TimeInTimetable> getTimesInTimetable() {
        return timesInTimetable;
    }

    /**
     * @param timesInTimetable the timesInTimetable to set
     */
    public void setTimesInTimetable(ArrayList<TimeInTimetable> timesInTimetable) {
        this.timesInTimetable = timesInTimetable;
    }

    /**
     * create lecture timetable with input there Ids
     *
     * @param coursesIds
     */
    public void createLectureTimetable(Integer... coursesIds) {
        Course[] courses = new Course[coursesIds.length];
        List<Integer> a = Arrays.asList(coursesIds);
        ArrayList<Branch> allBranchesWithHostingRooms = Branch.getAllBranchesWithHostingRooms();
        courses = getAllCoursesDataForLectuer(a, courses, allBranchesWithHostingRooms);
        ArrayList<Branch> branchsWithRooms = Branch.getAllBranchesWithAllRooms();
        makeTheLecGroupBranchesRefrence(courses, branchsWithRooms);
        makeTheStaffRefrance(courses);
        ArrayList<Staff> staff = getStaffFreeTime(courses);
        setHostingRoomsInDaysForStaff(staff);
        makeHostingRoomsReference(courses);
        getRoomsFreeTime(branchsWithRooms);
        ArrayList<Semester> semesters = getSemesters(courses);
        makeBranchesForEachSemester(semesters, branchsWithRooms);
        assineValuesToStaffIsSymmetricAndFreeTimeInDays(staff);
        RemoveHostingBranchesFromLecGroups(courses);
        makeTheLecFreeTimeRefranceInEachSemester(semesters);
        for (Semester semester : semesters) {
            semester.getCourses().sort((o1, o2) -> {
                return o1.getStaff().getFreeTime().getNumberOfDays() - o2.getStaff().getFreeTime().getNumberOfDays();
            });
        }
        semesters.sort((o1, o2) -> {
            return o1.getCourses().get(0).getStaff().getFreeTime().getNumberOfDays() - o2.getCourses().get(0).getStaff().getFreeTime().getNumberOfDays();
        });

        splitedSemestersWithDays s = splitTheSemesterIntoDays(semesters);
        if (!cheekIfallSemestersWereAddAndRemoveTheAddedSemesters(s, semesters)) {
            System.out.println("not all the semesters were added");
        }
        addTheLectureCoursesToTheTimeTable(s);
    }

    /*
            System.out.println(timesInTimetable.size());
        System.out.println(courses.length);
        System.out.println("            ----------------------                  ");

        System.out.println("");
     */
    boolean cheekIfallSemestersWereAddAndRemoveTheAddedSemesters(splitedSemestersWithDays s, ArrayList<Semester> semesters) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < s.getCourses().get(i).size(); j++) {
                semesters.remove(s.getCourses().get(i).get(j).getSemester());
            }
        }
        for (int i = 0; i < semesters.size(); i++) {
            System.out.println(semesters.get(i).getId() + "\t" + semesters.get(i).getCourses().toString());
        }
        return semesters.isEmpty();
    }

    /**
     * get the freeTime for rooms from the database
     *
     * @param branchs
     */
    static void getRoomsFreeTime(ArrayList<Branch> branchs) {
        ArrayList<Room> rooms = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branchs.size(); i++) {
            for (int j = 0; j < branchs.get(i).getRooms().size(); j++) {
                rooms.add(branchs.get(i).getRooms().get(j));
                sb.append(branchs.get(i).getRooms().get(j).getId()).append(", ");
            }
        }
        rooms.sort((o1, o2) -> {
            return o1.getId() - o2.getId();
        });
        connection conn = new connection();
        try {
            int j = 0;
            ResultSet rs = conn.select("select * from freetimeforrooms where roomid in (" + sb.substring(0, sb.length() - 2) + ") order by roomId");
            while (rs.next()) {
                for (int i = j; i < rooms.size(); i++) {
                    if (rooms.get(i).getId() == rs.getInt(1)) {
                        rooms.get(i).getFreeTime().addFreeTime(rs.getInt(2), rs.getInt(3), rs.getInt(4));
                        j = i;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            conn.close();
        }
    }

    /**
     * get the freeTime for rooms in one branch
     *
     * @param branch
     */
    static void getRoomsFreeTime(Branch branch) {
        for (int j = 0; j < branch.getRooms().size(); j++) {
            branch.getRooms().get(j).getTheFreeTime();
        }
    }

    /**
     * create lecture timetable by getting the courses
     *
     * @param courses
     */
    public void createLectureTimetable(Course... courses) {
        ArrayList<Branch> allBranchesWithHostingRooms = Branch.getAllBranchesWithHostingRooms();
        courses = getAllCoursesDataForLectuer(courses, allBranchesWithHostingRooms);
        ArrayList<Branch> branchsWithRooms = Branch.getAllBranchesWithAllRooms();
        branchsWithRooms.forEach(branch -> {
            branch.getRooms().sort((Room o1, Room o2) -> {
                // Get the IDs of the rooms
                int id1 = o1.getRoomtype().getId();
                int id2 = o2.getRoomtype().getId();

                // Determine if the IDs are odd or even
                boolean isOdd1 = id1 % 2 != 0;
                boolean isOdd2 = id2 % 2 != 0;

                // Compare based on odd/even
                if (isOdd1 && !isOdd2) {
                    return -1; // o1 is odd, o2 is even, so o1 should come before o2
                } else if (!isOdd1 && isOdd2) {
                    return 1; // o1 is even, o2 is odd, so o2 should come before o1
                } else {
                    return id1 - id2; // Both are either odd or even, compare IDs directly
                }
            });
        });
        makeTheLecGroupBranchesRefrence(courses, branchsWithRooms);
        makeTheStaffRefrance(courses);
        ArrayList<Staff> staff = getStaffFreeTime(courses);
        setHostingRoomsInDaysForStaff(staff);
        makeHostingRoomsReference(courses);
        getRoomsFreeTime(branchsWithRooms);
        ArrayList<Semester> semesters = getSemesters(courses);
        makeBranchesForEachSemester(semesters, branchsWithRooms);
        assineValuesToStaffIsSymmetricAndFreeTimeInDays(staff);
        RemoveHostingBranchesFromLecGroups(courses);
        makeTheLecFreeTimeRefranceInEachSemester(semesters);
        splitedSemestersWithDays s = splitTheSemesterIntoDays(semesters);
        if (!cheekIfallSemestersWereAddAndRemoveTheAddedSemesters(s, semesters)) {
            System.out.println("not all the semesters were added");
        }
        addTheLectureCoursesToTheTimeTable(s);
        System.out.println(timesInTimetable.size());
        System.out.println(courses.length);
        System.out.println("            ----------------------                  ");
    }

    /**
     * make the lecGroups in one semester reference to make them all point to
     * each other
     *
     * @param semesters
     */
    void makeTheLecFreeTimeRefranceInEachSemester(ArrayList<Semester> semesters) {
        for (Semester semester : semesters) {
            makeTheLecGroupFreeTimeRefrence(semester);
        }
    }

    /**
     * remove the hosting branches from the lecGroup as it will be in all the
     * lecGroups in this course
     *
     * @param courses
     */
    void RemoveHostingBranchesFromLecGroups(Course[] courses) {
        for (Course course : courses) {
            for (LecGroup lecGroup : course.getLecGroups()) {
                for (int i = 0; i < lecGroup.getBranchs().size(); i++) {
                    if (lecGroup.getBranchs().get(i).getId() == course.getStaff().getBranch().getId()) {
                        lecGroup.getBranchs().remove(i);
                    }
                }
            }
        }
    }

    /**
     * get the timesInTheTimetable made before to interact with it from the
     * database
     *
     * @param timeTableId
     * @return
     */
    public ArrayList<TimeInTimetable> getThePreTimesInTimetable(int timeTableId) {
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
                                       roomsintimeintimetable.roomId,r2.name,courses.SemesterId
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
                            if (t.get(i).getBranchs().get(j).getId() == rs.getInt(17)
                                    || t.get(i).getHostingBranch().getId() == rs.getInt(17)) {
                                branchFound = true;
                                break;
                            }
                        }
                        for (int j = 0; j < t.get(i).getRooms().size(); j++) {
                            if (t.get(i).getRooms().get(j).getId() == rs.getInt(19)
                                    || t.get(i).getHostingRoom().getId() == rs.getInt(19)) {
                                roomFound = true;
                                break;
                            }
                        }
                        if (!roomFound) {
                            t.get(i).getRooms().add(
                                    new Room(rs.getInt(19), rs.getString(20)));
                        }
                        if (!branchFound) {
                            t.get(i).getBranchs().add(
                                    new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Branch(rs.getInt(17), rs.getString(18)));
                        }
                    }
                }
                if (!found) {
                    t.add(new TimeInTimetable(
                            rs.getInt(1),
                            new com.team.timetableManagmentSystem.academictimetablemanagmentsystem.Staff(rs.getInt(6), rs.getString(7)),
                            new Course(rs.getInt(4), rs.getString(5), rs.getInt(21)),
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

    /**
     * create section timetable for specific branch
     *
     * @param timetable
     * @param branchId
     * @param coursesIds
     */
    public void createSectionTimetable(Timetable timetable, Integer branchId, Integer... coursesIds) {
        Course[] courses = new Course[coursesIds.length];
        List<Integer> a = Arrays.asList(coursesIds);
        timetable.setTimesInTimetable(getThePreTimesInTimetable(timetable.getId()));
        courses = getAllCoursesDataForSections(a, courses, branchId);
        courses = getTheSectionsWithMoreThanOneStaff(a, courses, branchId);
        Branch branch = new Branch(branchId);
        branch.getAllRooms();
        getRoomsFreeTime(branch);
        ArrayList<Semester> semesters = getSemesters(courses);
        subtractTheSemesterFreeTimeFromTheLectureTimetable(timetable, semesters);
        makeTheStaffRefrance(courses);
        ArrayList<Staff> staff = getStaffFreeTime(courses);
        makeSectionGroupsFreeTimeRefranceInASemster(semesters);
        makeTheBranchRefranceToStaff(branch, courses);
        //      SubtractFreeTimeForRoomsFromTheTimetable(branch, timetable);
        getTheRoomTypeForCourses(a, courses);
        timesInTimetable = new ArrayList<>();
        getTheSemesterWorkingDaysAndTryToAddThem(semesters);
        addTheRemainingCoursesToSectionTimeTable(semesters);
        timesInTimetable.sort((o1, o2) -> {
            return o1.getDay() - o2.getDay();
        });
        System.out.println(timesInTimetable.size());
        System.out.println(courses.length);
        System.out.println("            ----------------------                  ");
    }

    /**
     * get the room types for the course in the section
     *
     * @param coursesIds
     * @param courses
     */
    void getTheRoomTypeForCourses(List<Integer> coursesIds, Course[] courses) {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select courses.id , coursesectionroomtypes.roomTypeId"
                    + " from coursesectionroomtypes "
                    + "inner join courses on coursesectionroomtypes.courseId=courses.id"
                    + " where courses.id in "
                    + "(" + coursesIds.toString().substring(1, coursesIds.toString().length() - 1) + ")");
            while (rs.next()) {
                for (int i = 0; i < coursesIds.size(); i++) {
                    if (courses[i].getId() == rs.getInt(1)) {
                        courses[i].getRoomsTypesForSection().add(rs.getInt(2));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }

    /**
     * subtract the freeTime for rooms if not getted from the database
     *
     * @param branch
     * @param timetable
     */
    void SubtractFreeTimeForRoomsFromTheTimetable(Branch branch, Timetable timetable) {
        for (int i = 0; i < timetable.getTimesInTimetable().size(); i++) {
            if (timetable.getTimesInTimetable().get(i).getRooms() != null) {
                for (int j = 0; j < timetable.getTimesInTimetable().get(i).getRooms().size(); j++) {
                    for (int k = 0; k < branch.getRooms().size(); k++) {
                        if (branch.getRooms().get(k).getId()
                                == timetable.getTimesInTimetable().get(i).getRooms().get(j).getId()) {
                            branch.getRooms().get(k).getFreeTime()
                                    .removeAnHour(timetable.getTimesInTimetable().get(i).getDay(),
                                            timetable.getTimesInTimetable().get(i).getStartingTime());
                        }
                    }
                }
            }
        }
    }

    /**
     * make the staff point to the same branch in the section timetable
     *
     * @param branch
     * @param courses
     */
    void makeTheBranchRefranceToStaff(Branch branch, Course[] courses) {
        for (Course course : courses) {
            course.getStaff().setBranch(branch);
        }
    }

    /**
     * compute the semester freeTime from the pretimeTable
     *
     * @param timetable
     * @param semesters
     */
    void subtractTheSemesterFreeTimeFromTheLectureTimetable(Timetable timetable, ArrayList<Semester> semesters) {
        for (int i = 0; i < timetable.getTimesInTimetable().size(); i++) {
            for (int j = 0; j < semesters.size(); j++) {
                if (timesInTimetable.get(i).getBranchs() != null) {
                    if (timetable.getTimesInTimetable().get(i).getCourse().getSemester().getId() == semesters.get(j).getId()) {
                        semesters.get(j).getFreeTime()
                                .removeAnHour(timetable.getTimesInTimetable().get(i).getDay(), timetable.getTimesInTimetable().get(i).getStartingTime());
                        break;
                    }
                }
            }
        }
    }

    /**
     * iterate throw the splited semesters and add them to the timeInTimetable
     *
     * @param splitedSemesters
     */
    void addTheLectureCoursesToTheTimeTable(splitedSemestersWithDays splitedSemesters) {
        for (int i = 0; i < 6; i++) {
            ArrayList<Semester> semesters = getSemesters(splitedSemesters.getCourses().get(i));
            for (Semester semester : semesters) {
                addLectuerToTimeInTimetable(i, semester, splitedSemesters);
            }
        }
    }

    /**
     * get the semester working days from the preTimetable and try to add
     * sections to this days
     *
     * @param semesters
     */
    void getTheSemesterWorkingDaysAndTryToAddThem(ArrayList<Semester> semesters) {
        /*    for (int i = 0; i < 6; i++) {
            for (int k = 8; k < 17; k++) {
                for (Semester semester : semesters) {
                    if (semester.getIfWorkingDay()[i]) {
                        //addSectionsToTimeInTimetable(i, semester);
                        for (Course course : semester.getCourses()) {
                            for (sectionGroups group : course.getGroup().getGroups()) {
                                for (int l = 0; l < course.getRoomsTypesForSection().size(); l++) {
                                    Room room = course.getStaff().getBranch().getARoomWithATypeFreeAt(i, k, course.getRoomsTypesForSection().get(l));
                                    if (room != null
                                            && course.getStaff().getFreeTime().isFreeAt(i, k)
                                            && semester.getFreeTime().isFreeAt(i, k)
                                            && group.getFreeTime().isFreeAt(1, k)
                                            && group.remaining > 0) {
                                        timesInTimetable.add(new TimeInTimetable(course.getStaff(),
                                                course,
                                                null,
                                                course.getStaff().getBranch(),
                                                null,
                                                room,
                                                1,
                                                k,
                                                k + 1,
                                                group.getName(),
                                                null,
                                                this));
                                        group.getFreeTime().removeAnHour(i, k);
                                        course.getStaff().getFreeTime().removeAnHour(i, k);
                                        room.getFreeTime().removeAnHour(i, k);
                                        group.remaining--;

                                    }
                                }

                            }
                        }
                    }
                }
            }
        }*/
        for (int i = 0; i < 6; i++) {
            for (Semester semester : semesters) {
                if (semester.getIfWorkingDay()[i]) {
                    addSectionsToTimeInTimetable(i, semester);

                }

            }
        }
    }

    /**
     * add the remaining courses that haven`t been added to the timetable on the
     * days they are working in
     *
     * @param semesters
     */
    void addTheRemainingCoursesToSectionTimeTable(ArrayList<Semester> semesters) {
        for (int i = 0; i < 6; i++) {
            for (Semester semester : semesters) {

                addSectionsToTimeInTimetable(i, semester);

            }
        }

        /*      for (int i = 0; i < 6; i++) {
                    for (int k = 8; k < 17; k++) {
                        for (Semester semester : semesters) {

                            for (Course course : semester.getCourses()) {
                                for (sectionGroups group : course.getGroup().getGroups()) {
                                    for (int l = 0; l < course.getRoomsTypesForSection().size(); l++) {
                                        Room room = course.getStaff().getBranch().getARoomWithATypeFreeAt(i, k, course.getRoomsTypesForSection().get(l));
                                        if (room != null
                                                && course.getStaff().getFreeTime().isFreeAt(i, k)
                                                && semester.getFreeTime().isFreeAt(i, k)
                                                && group.getFreeTime().isFreeAt(1, k)
                                                && group.remaining > 0) {
                                            timesInTimetable.add(new TimeInTimetable(course.getStaff(),
                                                    course,
                                                    null,
                                                    course.getStaff().getBranch(),
                                                    null,
                                                    room,
                                                    1,
                                                    k,
                                                    k + 1,
                                                    group.getName(),
                                                    null,
                                                    this));
                                            group.getFreeTime().removeAnHour(i, k);
                                            course.getStaff().getFreeTime().removeAnHour(i, k);
                                            room.getFreeTime().removeAnHour(i, k);
                                            group.remaining--;

                                        }
                                    }

                                }
                            }
                        }
                    }
                }
         */
    }

    /**
     * try to add the semester courses in a day
     *
     * @param day
     * @param semester
     */
    void addSectionsToTimeInTimetable(int day, Semester semester) {
        for (int i = 0; i < semester.getCourses().size(); i++) {
            for (int k = 8; k < 17; k++) {
                for (sectionGroups sectionGroupBranche : semester.getCourses().get(i).getSectionGroup().getGroups()) {
                    for (int l = 0; l < semester.getCourses().get(i).getRoomsTypesForSection().size(); l++) {
                        Room room = semester.getCourses().get(i).getStaff().getBranch()
                                .getARoomWithATypeFreeAt(day, k, semester.getCourses().get(i)
                                        .getRoomsTypesForSection().get(l));
                        if (room != null
                                && semester.getCourses().get(i).getStaff().getFreeTime().isFreeAt(day, k)
                                && semester.getFreeTime().isFreeAt(day, k)
                                && sectionGroupBranche.getFreeTime().isFreeAt(day, k)
                                && sectionGroupBranche.remaining > 0) {
                            timesInTimetable.add(new TimeInTimetable(semester.getCourses().get(i).getStaff(),
                                    semester.getCourses().get(i),
                                    null,
                                    semester.getCourses().get(i).getStaff().getBranch(),
                                    null,
                                    room,
                                    day,
                                    k, k + 1,
                                    sectionGroupBranche.getName(),
                                    null,
                                    this));
                            sectionGroupBranche.getFreeTime().removeAnHour(day, k);
                            semester.getCourses().get(i).getStaff().getFreeTime().removeAnHour(day, k);
                            room.getFreeTime().removeAnHour(day, k);
                            sectionGroupBranche.remaining--;

                        }
                    }
                }
            }
        }
    }

    /**
     * try to add the lecture courses to the timeInTimeTable
     *
     * @param day
     * @param semester
     * @param splitedSemesters
     */
    void addLectuerToTimeInTimetable(int day, Semester semester, splitedSemestersWithDays splitedSemesters) {
        int pri = 0;
        for (int k = 8; k < 17; k++) {
            for (int i = 0 + pri(pri); i < semester.getCourses().size() + pri(pri); i++) {
                for (int j = 0; j < semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().size(); j++) {
                    if (semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getFreeTime().isFreeAt(day, k)
                            && semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).remaining > 0) {
                        Room hostingRoom = semester.getCourses().get(i % semester.getCourses().size()).getStaff().getBranch().getAHostingRoomFreeAt(day, k);
                        ArrayList<Room> rooms = semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getRoomsFreeAt(day, k);
                        boolean lecGroupBranches = semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).IsAllBranchesFreeAt(day, k),
                                otherBranches = true,
                                staff = semester.getCourses().get(i % semester.getCourses().size()).getStaff().getFreeTime().isFreeAt(day, k);
                        assineLecGroupsToSemester(semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster());
                        for (int l = 0; l < semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().size(); l++) {
                            for (int m = 0; m < semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().get(l).getAllLecGroups().size(); m++) {
                                otherBranches = otherBranches && semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().get(l).getAllLecGroups().get(m).IsAllBranchesFreeAt(day, k);
                            }
                        }
                        if (hostingRoom != null && rooms != null && lecGroupBranches && staff && otherBranches) {
                            if (semester.getCourses().get(i % semester.getCourses().size()).getStaff().getIssymmetric()[day % 3]) {
                                rooms = semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getRoomsFreeAtTwoTimesInTwoDays(day, k, (day + 3) % 6);
                                if (rooms != null) {
                                    TimeInTimetable t = new TimeInTimetable(semester.getCourses().get(i % semester.getCourses().size()).getStaff(), semester.getCourses().get(i % semester.getCourses().size()), semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getBranchs(), semester.getCourses().get(i % semester.getCourses().size()).getStaff().getBranch(), rooms, hostingRoom, (day) % 6, k, k + 1, null, semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j), this),
                                            t1 = new TimeInTimetable(semester.getCourses().get(i % semester.getCourses().size()).getStaff(), semester.getCourses().get(i % semester.getCourses().size()), semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getBranchs(), semester.getCourses().get(i % semester.getCourses().size()).getStaff().getBranch(), rooms, hostingRoom, (day + 3) % 6, k, k + 1, null, semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j), this);
                                    timesInTimetable.add(t);
                                    timesInTimetable.add(t1);
                                    hostingRoom.getFreeTime().removeAnHour(day, k);
                                    hostingRoom.getFreeTime().removeAnHour((day + 3) % 6, k);
                                    for (int l = 0; l < rooms.size(); l++) {
                                        rooms.get(l).getFreeTime().removeAnHour(day, k);
                                        rooms.get(l).getFreeTime().removeAnHour((day + 3) % 6, k);
                                    }
                                    semester.getCourses().get(i % semester.getCourses().size()).getStaff().getFreeTime().removeAnHour(day, k);
                                    for (int l = 0; l < semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().size(); l++) {
                                        for (int m = 0; m < semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().get(l).getAllLecGroups().size(); m++) {
                                            for (int n = 0; n < semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getBranchs().size(); n++) {
                                                for (int o = 0; o < semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().get(l).getAllLecGroups().get(m).getBranchs().size(); o++) {
                                                    if (semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().get(l).getAllLecGroups().get(m).getBranchs().get(o).getId() == semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getBranchs().get(n).getId()) {
                                                        semester.getCourses().get(i % semester.getCourses().size()).getOtherSemster().get(l).getAllLecGroups().get(m).getFreeTime().removeAnHour(day, k);
                                                    }
                                                }

                                            }
                                        }
                                    }
                                    semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getFreeTime().removeAnHour(day, k);
                                    semester.getCourses().get(i % semester.getCourses().size()).getStaff().getFreeTime().removeAnHour((day + 3) % 6, k);
                                    semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getFreeTime().removeAnHour((day + 3) % 6, k);
                                }
                            } else {
                                TimeInTimetable t = new TimeInTimetable(semester.getCourses().get(i % semester.getCourses().size()).getStaff(), semester.getCourses().get(i % semester.getCourses().size()), semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getBranchs(), semester.getCourses().get(i % semester.getCourses().size()).getStaff().getBranch(), rooms, hostingRoom, (day) % 6, k, k + 2, null, semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j), this);
                                timesInTimetable.add(t);
                                hostingRoom.getFreeTime().removeAnHour(day, k);
                                hostingRoom.getFreeTime().removeAnHour((day) % 6, k + 1);
                                for (int l = 0; l < rooms.size(); l++) {
                                    rooms.get(l).getFreeTime().removeAnHour(day, k);
                                    rooms.get(l).getFreeTime().removeAnHour((day) % 6, k + 1);
                                }
                                semester.getCourses().get(i % semester.getCourses().size()).getStaff().getFreeTime().removeAnHour(day, k);
                                semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getFreeTime().removeAnHour(day, k);
                                semester.getCourses().get(i % semester.getCourses().size()).getStaff().getFreeTime().removeAnHour((day) % 6, k + 1);
                                semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).getFreeTime().removeAnHour(day % 6, k + 1);
                            }

                            splitedSemesters.getCourses().get(day).remove(semester.getCourses().get(i % semester.getCourses().size()));
                            splitedSemesters.getCourses().get((day + 3) % 6).remove(semester.getCourses().get(i % semester.getCourses().size()));
                            semester.getCourses().get(i % semester.getCourses().size()).getLecGroups().get(j).remaining -= 2;
                            pri++;
                            i++;
                            break;
                        }
                    }
                }
            }
        }
    }

    int pri(int pri
    ) {
        return pri;
    }

//    void IfcanBeConsecutiveAddThem(int day, splitedSemestersWithDays s, Course... courses) {
//        boolean canBeConsecutive = false, times[] = new boolean[9];
//        int startingTime = 0;
//
//        for (int i = 8; i < 17; i++) {
//            for (int j = 0; j < courses.length; j++) {
//                for (int k = 0; k < courses[j].getLecGroups().size(); k++) {
//                    Room hostingRoom = courses[j].getStaff().getBranch().getAHostingRoomFreeAt(day, i);
//                    ArrayList<Room> rooms = courses[j].getLecGroups().get(0).getRoomsFreeAt(day, i);
//                    boolean lecGroupBranches = courses[j].getLecGroups().get(0).IsAllBranchesFreeAt(day, i),
//                            staff = courses[j].getStaff().getFreeTime().isFreeAt(day, i);
//                    times[i] = times[i] && hostingRoom != null && rooms != null && lecGroupBranches && staff;
//                }
//            }
//        }
//        if (courses.length == 3) {
//            for (int i = 0; i < 7; i++) {
//                if (times[i] && times[i + 1] && times[i + 2]) {
//                    
//                }
//            }
//        }
//    }
    /**
     * try to split the semesters into days
     *
     * @param semesters
     * @return
     */
    splitedSemestersWithDays splitTheSemesterIntoDays(ArrayList<Semester> semesters) {
        splitedSemestersWithDays splitedSemesters = new splitedSemestersWithDays();
        for (Semester semester : semesters) {
            splitSemester(semester, splitedSemesters);
        }
        return splitedSemesters;
    }

    /**
     * try to split each semester into days
     *
     * @param semester
     * @param splitedSemesters
     */
    void splitSemester(Semester semester, splitedSemestersWithDays splitedSemesters) {
        semester.setTheNumberOfRoomsInDay();

        if (semester.getCourses().size() <= 3) {
            Course[] c = semester.getCourses().toArray(new Course[semester.getCourses().size()]);

            if (canBeInOneDay(splitedSemesters, c) != null) {
                addCoursesToTheLecuterTimeSplitedSemester(
                        splitedSemesters,
                        canBeInOneDay(splitedSemesters, c)
                );
            } else if (semester.getCourses().size() == 2) {
                if (canBeInOneDay(splitedSemesters, semester.getCourses().get(0)) != null) {
                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                            canBeInOneDay(splitedSemesters, semester.getCourses().get(0)));
                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(1)) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(1)));
                    } else {
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(0));

                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(1)));
                        if (canBeInOneDay(splitedSemesters, semester.getCourses().get(0)) != null) {
                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    canBeInOneDay(splitedSemesters, semester.getCourses().get(0)));
                            return;
                        } else {
                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    semester.getCourses().get(1));
                        }

                    }
                }
            } else if (semester.getCourses().size() == 3) {
                if (canBeInOneDay(splitedSemesters, semester.getCourses().get(0), semester.getCourses().get(1)) != null
                        && canBeInOneDay(splitedSemesters, semester.getCourses().get(2)) != null) {
                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(0), semester.getCourses().get(1)));
                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(2)) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(2)));
                        return;
                    } else {
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(0));
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(1));
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(2)));
                        if (canBeInOneDay(splitedSemesters, semester.getCourses().get(0), semester.getCourses().get(1)) != null) {
                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    canBeInOneDay(splitedSemesters, semester.getCourses().get(2)));
                            return;
                        } else {
                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    semester.getCourses().get(2));
                        }
                    }
                } else if (canBeInOneDay(splitedSemesters,
                        semester.getCourses().get(0),
                        semester.getCourses().get(2)) != null
                        && canBeInOneDay(splitedSemesters, semester.getCourses().get(1)) != null) {
                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                            canBeInOneDay(splitedSemesters,
                                    semester.getCourses().get(0),
                                    semester.getCourses().get(2)));
                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(1)) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(1)));
                        return;
                    } else {
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(0));
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(2));
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(1)));
                        if (canBeInOneDay(splitedSemesters,
                                semester.getCourses().get(0),
                                semester.getCourses().get(2)) != null) {
                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    canBeInOneDay(splitedSemesters, semester.getCourses().get(1)));
                            return;
                        } else {
                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    semester.getCourses().get(1));
                        }
                    }
                } else if (canBeInOneDay(splitedSemesters,
                        semester.getCourses().get(2), semester.getCourses().get(1)) != null
                        && canBeInOneDay(splitedSemesters,
                                semester.getCourses().get(0)) != null) {
                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                            canBeInOneDay(splitedSemesters,
                                    semester.getCourses().get(2), semester.getCourses().get(1)));
                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(0)) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(0)));
                        //       System.out.println("fetted " + semester.getId() + "    " + semester.getCourses().get(0).getId());
                        return;
                    } else {
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(1));
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(2));
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(0)));
                        if (canBeInOneDay(splitedSemesters, semester.getCourses().get(2), semester.getCourses().get(1)) != null) {
                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(2), semester.getCourses().get(1)));
                            return;
                        } else {
                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                    semester.getCourses().get(0));
                        }
                    }

                }
                if (canBeInOneDay(splitedSemesters, semester.getCourses().get(0)) != null) {
                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(0)));
                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(1)) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(1)));
                    } else {
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(0));
                        return;
                    }
                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(2)) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                canBeInOneDay(splitedSemesters, semester.getCourses().get(2)));
                        return;
                    } else {
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(0));
                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                semester.getCourses().get(1));
                    }

                }

            }
        } else if (semester.getCourses().size() <= 6) {
            switch (semester.getCourses().size()) {
                case 4 -> {
                    for (int i = 0; i < semester.getCourses().size(); i++) {
                        for (int j = i + 1; j < semester.getCourses().size(); j++) {
                            int arr[] = getTheOtherTwocourses(i, j);
                            if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i), semester.getCourses().get(j)) != null
                                    && canBeInOneDay(splitedSemesters, semester.getCourses().get(arr[0]), semester.getCourses().get(arr[1])) != null) {
                                addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(i), semester.getCourses().get(j)));
                                if (canBeInOneDay(splitedSemesters, semester.getCourses().get(arr[0]), semester.getCourses().get(arr[1])) != null) {
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(arr[0]), semester.getCourses().get(arr[1])));
                                    return;
                                } else {
                                    removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            semester.getCourses().get(i));
                                    removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            semester.getCourses().get(j));
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(arr[0]), semester.getCourses().get(arr[1])));
                                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i), semester.getCourses().get(j)) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(i), semester.getCourses().get(j)));
                                        return;
                                    } else {
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(arr[0]));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(arr[1]));
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < semester.getCourses().size(); i++) {
                        for (int j = i + 1; j < semester.getCourses().size(); j++) {
                            for (int k = j + 1; k < semester.getCourses().size(); k++) {
                                int x = getTheFourthCourse(k, j, i);
                                if (canBeInOneDay(splitedSemesters,
                                        semester.getCourses().get(i),
                                        semester.getCourses().get(j),
                                        semester.getCourses().get(k))
                                        != null
                                        && canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(getTheOtherTwocourses(i, j)[0]),
                                                semester.getCourses().get(x))
                                        != null) {
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                                    semester.getCourses().get(j),
                                                    semester.getCourses().get(k)));
                                    if (canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(x)) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(x)));
                                        return;
                                    } else {
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(i));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(j));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(k));
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(x)));
                                        if (canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(i),
                                                semester.getCourses().get(j),
                                                semester.getCourses().get(k))
                                                != null) {
                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                                            semester.getCourses().get(j),
                                                            semester.getCourses().get(k)));
                                            return;
                                        } else {
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(x));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Course[] c = semester.getCourses().toArray(new Course[semester.getCourses().size()]);
                    if (canBeInOneDay(splitedSemesters, c) != null) {
                        addCoursesToTheLecuterTimeSplitedSemester(
                                splitedSemesters,
                                canBeInOneDay(splitedSemesters, c)
                        );
                        return;
                    }
                }
                case 5 -> {
                    for (int i = 0; i < semester.getCourses().size(); i++) {
                        for (int j = i + 1; j < semester.getCourses().size(); j++) {
                            for (int k = j + 1; k < semester.getCourses().size(); k++) {
                                if (canBeInOneDay(splitedSemesters,
                                        semester.getCourses().get(i),
                                        semester.getCourses().get(j),
                                        semester.getCourses().get(k)) != null
                                        && canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(getTheOtherTwocourses(i, j, k)[0]),
                                                semester.getCourses().get(getTheOtherTwocourses(i, j, k)[1])) != null) {
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(i),
                                                    semester.getCourses().get(j),
                                                    semester.getCourses().get(k)));
                                    if (canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(getTheOtherTwocourses(i, j, k)[0]),
                                            semester.getCourses().get(getTheOtherTwocourses(i, j, k)[1])) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(getTheOtherTwocourses(i, j, k)[0]),
                                                        semester.getCourses().get(getTheOtherTwocourses(i, j, k)[1])));
                                        return;
                                    } else {
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(i));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(k));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(j));
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < semester.getCourses().size(); i++) {
                        for (int j = i + 1; j < semester.getCourses().size(); j++) {
                            for (int k = j + 1; k < semester.getCourses().size(); k++) {
                                for (int l = k + 1; l < semester.getCourses().size(); l++) {
                                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                            semester.getCourses().get(j),
                                            semester.getCourses().get(k),
                                            semester.getCourses().get(l)) != null
                                            && canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(getTheFifthCourse(i, j, k, l))) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(i),
                                                        semester.getCourses().get(j),
                                                        semester.getCourses().get(k),
                                                        semester.getCourses().get(l)));
                                        if (canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(getTheFifthCourse(i, j, k, l))) != null) {
                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    canBeInOneDay(splitedSemesters,
                                                            semester.getCourses().get(getTheOtherTwocourses(i, j, k)[0]),
                                                            semester.getCourses().get(getTheFifthCourse(i, j, k, l))));
                                            return;
                                        } else {
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(i));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(k));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(j));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(l));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return;
                }
                default -> {
                    for (int i = 0; i < semester.getCourses().size(); i++) {
                        for (int j = i + 1; j < semester.getCourses().size(); j++) {
                            for (int k = j + 1; k < semester.getCourses().size(); k++) {
                                if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                        semester.getCourses().get(j),
                                        semester.getCourses().get(k)) != null
                                        && canBeInOneDay(splitedSemesters, semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[0]),
                                                semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[1]),
                                                semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[2])) != null) {
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                                    semester.getCourses().get(j),
                                                    semester.getCourses().get(k)));
                                    if (canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[0]),
                                            semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[1]),
                                            semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[2])) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[0]),
                                                        semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[1]),
                                                        semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[2])));
                                        return;
                                    } else {
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(i));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(k));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(j));
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[0]),
                                                        semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[1]),
                                                        semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[2])));

                                        if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                                semester.getCourses().get(j),
                                                semester.getCourses().get(k)) != null) {
                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                                            semester.getCourses().get(j),
                                                            semester.getCourses().get(k)));

                                            return;
                                        } else {
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[0]));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[1]));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(getTheOtherThreeCourses(k, j, i)[2]));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < semester.getCourses().size(); i++) {
                        for (int j = i + 1; j < semester.getCourses().size(); j++) {
                            for (int k = j + 1; k < semester.getCourses().size(); k++) {
                                for (int l = k + 1; l < semester.getCourses().size(); l++) {
                                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                            semester.getCourses().get(j),
                                            semester.getCourses().get(k),
                                            semester.getCourses().get(l)) != null
                                            && canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[0]),
                                                    semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[1])) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(i),
                                                        semester.getCourses().get(j),
                                                        semester.getCourses().get(k),
                                                        semester.getCourses().get(l)));
                                        if (canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[0]),
                                                semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[1])) != null) {
                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters, semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[0]), semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[1])));
                                            return;
                                        } else {
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(i));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(k));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(j));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(l));
                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[0]),
                                                    semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[1])));
                                            if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                                    semester.getCourses().get(j),
                                                    semester.getCourses().get(k),
                                                    semester.getCourses().get(l)) != null) {
                                                addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                        canBeInOneDay(splitedSemesters,
                                                                semester.getCourses().get(i),
                                                                semester.getCourses().get(j),
                                                                semester.getCourses().get(k),
                                                                semester.getCourses().get(l)));
                                                return;
                                            } else {
                                                removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                        semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[0]));
                                                removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                        semester.getCourses().get(getTheOtherTwocourses(l, k, j, i)[1]));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else /*more than 6 courses*/ {
            /*4     3   courses*/
            if (semester.getCourses().size() == 7) {
                for (int i = 0; i < semester.getCourses().size(); i++) {
                    for (int j = i + 1; j < semester.getCourses().size(); j++) {
                        for (int k = j + 1; k < semester.getCourses().size(); k++) {
                            int arr[] = getTheOtherFourCourses(i, j, k);
                            if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                    semester.getCourses().get(j),
                                    semester.getCourses().get(k)) != null
                                    && canBeInOneDay(splitedSemesters, semester.getCourses().get(arr[0]),
                                            semester.getCourses().get(arr[1]),
                                            semester.getCourses().get(arr[2]),
                                            semester.getCourses().get(3)) != null) {
                                addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                        semester.getCourses().get(arr[0]),
                                        semester.getCourses().get(arr[1]),
                                        semester.getCourses().get(arr[2]),
                                        semester.getCourses().get(arr[3])));
                                if (canBeInOneDay(splitedSemesters,
                                        semester.getCourses().get(i),
                                        semester.getCourses().get(j),
                                        semester.getCourses().get(k)) != null) {
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(i),
                                            semester.getCourses().get(j),
                                            semester.getCourses().get(k)));
                                    return;
                                } else {
                                    removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            semester.getCourses().get(arr[0]));
                                    removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            semester.getCourses().get(arr[1]));
                                    removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            semester.getCourses().get(arr[2]));
                                    removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                            semester.getCourses().get(arr[3]));
                                    addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(i),
                                            semester.getCourses().get(j),
                                            semester.getCourses().get(k)));
                                    if (canBeInOneDay(splitedSemesters, semester.getCourses().get(arr[0]),
                                            semester.getCourses().get(arr[1]),
                                            semester.getCourses().get(arr[2]),
                                            semester.getCourses().get(3)) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(arr[0]),
                                                semester.getCourses().get(arr[1]),
                                                semester.getCourses().get(arr[2]),
                                                semester.getCourses().get(arr[3])));
                                        return;
                                    } else {
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(i));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(j));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(k));
                                    }

                                }

                            }
                        }
                    }
                }
                /*                                      3  2  2                                   */
                for (int i = 0; i < semester.getCourses().size(); i++) {
                    for (int j = i + 1; j < semester.getCourses().size(); j++) {
                        for (int k = j + 1; k < semester.getCourses().size(); k++) {
                            if (canBeInOneDay(splitedSemesters, semester.getCourses().get(i),
                                    semester.getCourses().get(j),
                                    semester.getCourses().get(k)) != null) {
                                addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                        semester.getCourses().get(i),
                                        semester.getCourses().get(j),
                                        semester.getCourses().get(k)));
                                int arr[][] = getTheOtherFourCoursesByTwo(i, j, k);
                                for (int l = 0; l <= arr.length; l += 2) {

                                    if (l >= arr.length) {
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(i));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(j));
                                        removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                semester.getCourses().get(k));
                                    } else if (canBeInOneDay(splitedSemesters,
                                            semester.getCourses().get(arr[l][0]),
                                            semester.getCourses().get(arr[l][1])) != null
                                            && canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(arr[l + 1][0]),
                                                    semester.getCourses().get(arr[l + 1][1])) != null) {
                                        addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(arr[l + 1][0]),
                                                semester.getCourses().get(arr[l + 1][1])));
                                        if (canBeInOneDay(splitedSemesters,
                                                semester.getCourses().get(arr[l][0]),
                                                semester.getCourses().get(arr[l][1])) != null) {

                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(arr[l][0]),
                                                    semester.getCourses().get(arr[l][1])));
                                            return;
                                        } else {
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(arr[l + 1][0]));
                                            removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                    semester.getCourses().get(arr[l + 1][1]));
                                            addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(arr[l][0]),
                                                    semester.getCourses().get(arr[l][1])));
                                            if (canBeInOneDay(splitedSemesters,
                                                    semester.getCourses().get(arr[l + 1][0]),
                                                    semester.getCourses().get(arr[l + 1][1])) != null) {
                                                addCoursesToTheLecuterTimeSplitedSemester(splitedSemesters, canBeInOneDay(splitedSemesters,
                                                        semester.getCourses().get(arr[l + 1][0]),
                                                        semester.getCourses().get(arr[l + 1][1])));
                                                return;
                                            } else {
                                                removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                        semester.getCourses().get(arr[l][0]));
                                                removeCoursesToTheLecuterTimeSplitedSemester(splitedSemesters,
                                                        semester.getCourses().get(arr[l][1]));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * get the other two numbers as the input is two numbers
     *
     * @param num1
     * @param num2
     * @return
     */
    int[] getTheOtherTwocourses(int num1, int num2
    ) {
        ArrayList<Integer> arr = new ArrayList<>();

        if (num1 != 0 && num2 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num2 != 3) {
            arr.add(3);
        }
        return new int[]{arr.get(0), arr.get(1)};
    }

    /**
     * get the other two numbers for the input the 3 numbers
     *
     * @param num1
     * @param num2
     * @param num3
     * @return
     */
    int[] getTheOtherTwocourses(int num1, int num2, int num3
    ) {
        ArrayList<Integer> arr = new ArrayList<>();

        if (num1 != 0 && num2 != 0 && num3 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1 && num3 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2 && num3 != 2) {
            arr.add(2);
        }
        if (num2 != 3 && num3 != 3 && num1 != 3) {
            arr.add(3);
        }
        if (num3 != 4 && num2 != 4 && num1 != 4) {
            arr.add(4);
        }

        return new int[]{arr.get(0), arr.get(1)};
//        if (num1 == 0 && num2 == 1 && num3 == 2) {
//            int nums[] = {3, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 3) {
//            int nums[] = {2, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 4) {
//            int nums[] = {2, 3};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 3) {
//            int nums[] = {1, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 4) {
//            int nums[] = {1, 3};
//            return nums;
//        } else if (num1 == 0 && num2 == 3 && num3 == 4) {
//            int nums[] = {1, 2};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 3) {
//            int nums[] = {0, 4};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 4) {
//            int nums[] = {0, 3};
//            return nums;
//        } else {
//            int nums[] = {0, 1};
//            return nums;
//        }
    }

    /**
     * get other two numbers and the input is four numbres
     *
     * @param num1
     * @param num2
     * @param num3
     * @param num4
     * @return
     */
    int[] getTheOtherTwocourses(int num1, int num2, int num3, int num4
    ) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (num1 != 0 && num4 != 0 && num2 != 0 && num3 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num4 != 1 && num2 != 1 && num3 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num4 != 2 && num2 != 2 && num3 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num4 != 3 && num2 != 3 && num3 != 3) {
            arr.add(3);
        }
        if (num1 != 4 && num4 != 4 && num2 != 4 && num3 != 4) {
            arr.add(4);
        }
        if (num1 != 5 && num4 != 5 && num2 != 5 && num3 != 5) {
            arr.add(5);
        }
        return new int[]{arr.get(0), arr.get(1)};
//        if (num1 == 0 && num2 == 1 && num3 == 2 && num4 == 3) {
//            int nums[] = {4, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 2 && num4 == 4) {
//            int nums[] = {3, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 2 && num4 == 5) {
//            int nums[] = {3, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 3 && num4 == 4) {
//            int nums[] = {2, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 3 && num4 == 5) {
//            int nums[] = {2, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 4 && num4 == 5) {
//            int nums[] = {2, 3};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 3 && num4 == 4) {
//            int nums[] = {1, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 4 && num4 == 5) {
//            int nums[] = {1, 3};
//            return nums;
//        } else if (num1 == 0 && num2 == 3 && num3 == 4 && num4 == 5) {
//            int nums[] = {1, 2};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 3 && num4 == 4) {
//            int nums[] = {0, 5};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 3 && num4 == 5) {
//            int nums[] = {0, 4};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 4 && num4 == 5) {
//            int nums[] = {0, 3};
//            return nums;
//        } else if (num1 == 1 && num2 == 3 && num3 == 4 && num4 == 5) {
//            int nums[] = {0, 2};
//            return nums;
//        } else {
//            int nums[] = {0, 1};
//            return nums;
//        }
    }

    /**
     * get the fourth number with input the other three numbers
     *
     * @param num1
     * @param num2
     * @param num3
     * @return
     */
    int getTheFourthCourse(int num1, int num2, int num3
    ) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (num1 != 0 && num2 != 0 && num3 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1 && num3 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2 && num3 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num2 != 3 && num3 != 3) {
            arr.add(3);
        }
        return arr.get(0);
//        if (num1 == 0) {
//            if (num2 == 1) {
//                if (num3 == 2) {
//                    return 3;
//                } else {
//                    return 2;
//                }
//            } else {
//                return 1;
//            }
//        } else {
//            return 0;
//        }
    }

    /**
     * get the fifth number by input 4 courses
     *
     * @param num1
     * @param num2
     * @param num3
     * @param num4
     * @return
     */
    int getTheFifthCourse(int num1, int num2, int num3, int num4
    ) {

        ArrayList<Integer> arr = new ArrayList<>();
        if (num1 != 0 && num2 != 0 && num3 != 0 && num4 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1 && num3 != 1 && num4 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2 && num3 != 2 && num4 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num2 != 3 && num3 != 3 && num4 != 3) {
            arr.add(3);
        }
        if (num1 != 4 && num2 != 4 && num3 != 4 && num4 != 4) {
            arr.add(4);
        }
        return arr.get(0);

//        for (int i = 0; i < 5; i++) {
//            if (i != num1 && i != num2 && i != num3 && i != num4) {
//                return i;
//            }
//        }
//        return -1;
    }

    /**
     * get the other there courses with input three numbers
     *
     * @param num1
     * @param num2
     * @param num3
     * @return
     */
    int[] getTheOtherThreeCourses(int num1, int num2, int num3
    ) {

        ArrayList<Integer> arr = new ArrayList<>();
        if (num1 != 0 && num2 != 0 && num3 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1 && num3 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2 && num3 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num2 != 3 && num3 != 3) {
            arr.add(3);
        }
        if (num1 != 4 && num2 != 4 && num3 != 4) {
            arr.add(4);
        }
        if (num1 != 5 && num2 != 5 && num3 != 5) {
            arr.add(5);
        }
        return new int[]{arr.get(0), arr.get(1), arr.get(2)};

//        if (num1 == 0 && num2 == 1 && num3 == 2) {
//            int[] nums = {3, 4, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 3) {
//            int[] nums = {2, 4, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 4) {
//            int[] nums = {2, 3, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 1 && num3 == 5) {
//            int[] nums = {2, 3, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 3) {
//            int[] nums = {1, 4, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 4) {
//            int[] nums = {1, 3, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 2 && num3 == 5) {
//            int[] nums = {1, 3, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 3 && num3 == 4) {
//            int[] nums = {1, 2, 5};
//            return nums;
//        } else if (num1 == 0 && num2 == 3 && num3 == 5) {
//            int[] nums = {1, 2, 4};
//            return nums;
//        } else if (num1 == 0 && num2 == 4 && num3 == 5) {
//            int[] nums = {1, 2, 3};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 3) {
//            int[] nums = {0, 4, 5};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 4) {
//            int[] nums = {0, 3, 5};
//            return nums;
//        } else if (num1 == 1 && num2 == 2 && num3 == 5) {
//            int[] nums = {0, 3, 4};
//            return nums;
//        } else if (num1 == 1 && num2 == 3 && num3 == 4) {
//            int[] nums = {0, 2, 5};
//            return nums;
//        } else if (num1 == 1 && num2 == 3 && num3 == 5) {
//            int[] nums = {0, 2, 4};
//            return nums;
//        } else if (num1 == 1 && num2 == 4 && num3 == 5) {
//            int[] nums = {0, 2, 3};
//            return nums;
//        } else if (num1 == 2 && num2 == 3 && num3 == 4) {
//            int[] nums = {0, 1, 5};
//            return nums;
//        } else if (num1 == 2 && num2 == 3 && num3 == 5) {
//            int[] nums = {0, 1, 4};
//            return nums;
//        } else if (num1 == 2 && num2 == 4 && num3 == 5) {
//            int[] nums = {0, 1, 2};
//            return nums;
//        } else {
//            int[] nums = {0, 1, 2};
//            return nums;
//        }
    }

    int[] getTheOtherFourCourses(int num1, int num2, int num3
    ) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (num1 != 0 && num2 != 0 && num3 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1 && num3 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2 && num3 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num2 != 3 && num3 != 3) {
            arr.add(3);
        }
        if (num1 != 4 && num2 != 4 && num3 != 4) {
            arr.add(4);
        }
        if (num1 != 5 && num2 != 5 && num3 != 5) {
            arr.add(5);
        }
        if (num1 != 6 && num2 != 6 && num3 != 6) {
            arr.add(6);
        }
        return new int[]{arr.get(0), arr.get(1), arr.get(2), arr.get(3)};
    }

    int[][] getTheOtherFourCoursesByTwo(int num1, int num2, int num3
    ) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (num1 != 0 && num2 != 0 && num3 != 0) {
            arr.add(0);
        }
        if (num1 != 1 && num2 != 1 && num3 != 1) {
            arr.add(1);
        }
        if (num1 != 2 && num2 != 2 && num3 != 2) {
            arr.add(2);
        }
        if (num1 != 3 && num2 != 3 && num3 != 3) {
            arr.add(3);
        }
        if (num1 != 4 && num2 != 4 && num3 != 4) {
            arr.add(4);
        }
        if (num1 != 5 && num2 != 5 && num3 != 5) {
            arr.add(5);
        }
        if (num1 != 6 && num2 != 6 && num3 != 6) {
            arr.add(6);
        }
        return new int[][]{{arr.get(0), arr.get(1)},
        {arr.get(2), arr.get(3)},
        {arr.get(0), arr.get(2)},
        {arr.get(1), arr.get(3)},
        {arr.get(0), arr.get(3)},
        {arr.get(1), arr.get(2)}
        };
    }

    /**
     * to set the number of hosting rooms for that can be in a day
     *
     * @param staff
     */
    void setHostingRoomsInDaysForStaff(ArrayList<Staff> staff
    ) {
        for (Staff staff1 : staff) {
            staff1.getBranch().setTheNumberOfHostingRooms();
        }
    }

    /**
     * cheek is the staff can be in two days in the week or not and the number
     * of free hours in each day
     *
     * @param staff
     */
    void assineValuesToStaffIsSymmetricAndFreeTimeInDays(ArrayList<Staff> staff
    ) {
        for (int i = 0; i < staff.size(); i++) {
            staff.get(i).cheackIsSymmetric();
            staff.get(i).computeNumberOfFreeHoursInDay();
        }
    }

    /**
     * cheek if a course or more can be added in one day
     *
     * @param splitedSemesters
     * @param courses
     * @return
     */
    coursesWithDay canBeInOneDay(splitedSemestersWithDays splitedSemesters, Course... courses) {
        coursesWithDay c = null;
        FreeTime f = new FreeTime(1);
        for (Course course : courses) {
            f = FreeTime.AnddingTwoFreeTimes(f, course.getStaff().getFreeTime());
        }
        if (f.getNumberOfDays() >= 1) {
            boolean daysOfFreeTime[] = f.getDaysOfFreeTime(), fetted = true;
            for (int i = 0; i < 6; i++) {
                fetted = true;
                for (int j = 0; j < splitedSemesters.getCourses().get(i).size(); j++) {
                    if (splitedSemesters.getCourses().get(i).get(j).getSemester().getId() == courses[0].getSemester().getId()) {
                        fetted = false;
                        break;
                    }

                }
                if (daysOfFreeTime[i] && fetted) {
                    int subtracted[] = new int[courses.length];
                    for (int j = 0; j < courses.length; j++) {
                        boolean a = (courses[j].getStaff().getIssymmetric()[i % 3]
                                && courses[j].getStaff().getNumberOfFreeHoursInDays()[i] >= courses[j].getLecGroups().size()
                                && courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] >= courses[j].getLecGroups().size()
                                && courses[j].getSemester().getNumberOfRoomsInDay()[i] >= courses[j].getLecGroups().size()
                                && courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] >= courses[j].getLecGroups().size()
                                && courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] >= courses[j].getLecGroups().size()
                                && courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] >= courses[j].getLecGroups().size());

                        fetted = fetted && (a);
                        if (fetted) {
                            if (courses[j].getStaff().getIssymmetric()[i % 3]) {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[i] -= courses[j].getLecGroups().size();
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] = (courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] - courses[j].getLecGroups().size());
                                courses[j].getSemester().getNumberOfRoomsInDay()[i] = (courses[j].getSemester().getNumberOfRoomsInDay()[i] - courses[j].getLecGroups().size());
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] -= courses[j].getLecGroups().size();
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] = (courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] - courses[j].getLecGroups().size());
                                courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] = (courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] - courses[j].getLecGroups().size());
                                subtracted[j] += courses[j].getLecGroups().size();
                            } else {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[i] -= courses[j].getLecGroups().size() * courses[j].getLabHours();
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] -= (courses[j].getLecGroups().size() * courses[j].getLabHours());
                                courses[j].getSemester().getNumberOfRoomsInDay()[i] -= (courses[j].getLecGroups().size() * courses[j].getLabHours());
                                subtracted[j] += courses[j].getLecGroups().size() * courses[j].getLabHours();
                            }
                        }
                    }
                    if (fetted) {
//                            semester.setSplitted(true);
                        c = new coursesWithDay();
                        c.setCourses(courses);
                        c.setDay(i);
                        for (int j = 0; j < courses.length; j++) {
                            courses[j].getStaff().getNumberOfFreeHoursInDays()[i] += subtracted[j];
                            courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] += subtracted[j];
                            courses[j].getSemester().getNumberOfRoomsInDay()[i] += subtracted[j];
                            if (courses[j].getStaff().getIssymmetric()[i % 3]) {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] += subtracted[j];
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] += subtracted[j];
                                courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] += subtracted[j];
                            }
                        }
                        break;
                    } else {
                        for (int j = 0; j < subtracted.length; j++) {
                            courses[j].getStaff().getNumberOfFreeHoursInDays()[i] += subtracted[j];
                            courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] += subtracted[j];
                            courses[j].getSemester().getNumberOfRoomsInDay()[i] += subtracted[j];
                            if (courses[j].getStaff().getIssymmetric()[i % 3]) {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] += subtracted[j];
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] += subtracted[j];
                                courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] += subtracted[j];
                            }
                        }
                    }
                }
            }
            if (fetted) {
                return c;
            }
            for (int i = 0; i < 6; i++) {
                fetted = true;
                for (int j = 0; j < splitedSemesters.getCourses().get(i).size(); j++) {
                    if (splitedSemesters.getCourses().get(i).get(j).getSemester().getId() == courses[0].getSemester().getId()) {
                        fetted = false;
                        break;
                    }

                }
                if (daysOfFreeTime[i] && fetted) {
                    int subtracted[] = new int[courses.length];
                    for (int j = 0; j < courses.length; j++) {
                        boolean a = (courses[j].getStaff().getIssymmetric()[i % 3]
                                && courses[j].getStaff().getNumberOfFreeHoursInDays()[i] >= courses[j].getLecGroups().size()
                                && courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] >= courses[j].getLecGroups().size()
                                && courses[j].getSemester().getNumberOfRoomsInDay()[i] >= courses[j].getLecGroups().size()
                                && courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] >= courses[j].getLecGroups().size()
                                && courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] >= courses[j].getLecGroups().size()
                                && courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] >= courses[j].getLecGroups().size()),
                                b = ((!courses[j].getStaff().getIssymmetric()[i % 3]
                                && courses[j].getStaff().getFreeTime().dayStartEnd[i].startSession != null
                                && courses[j].getStaff().getNumberOfFreeHoursInDays()[i] >= courses[j].getLecGroups().size() * courses[j].getLabHours()
                                && courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] >= courses[j].getLecGroups().size() * courses[j].getLabHours()
                                && courses[j].getSemester().getNumberOfRoomsInDay()[i] >= courses[j].getLecGroups().size() * courses[j].getLabHours()));

                        fetted = fetted && (a || b);
                        if (fetted) {
                            if (courses[j].getStaff().getIssymmetric()[i % 3]) {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[i] -= courses[j].getLecGroups().size();
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] = (courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] - courses[j].getLecGroups().size());
                                courses[j].getSemester().getNumberOfRoomsInDay()[i] = (courses[j].getSemester().getNumberOfRoomsInDay()[i] - courses[j].getLecGroups().size());
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] -= courses[j].getLecGroups().size();
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] = (courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] - courses[j].getLecGroups().size());
                                courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] = (courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] - courses[j].getLecGroups().size());
                                subtracted[j] += courses[j].getLecGroups().size();
                            } else {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[i] -= courses[j].getLecGroups().size() * courses[j].getLabHours();
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] -= (courses[j].getLecGroups().size() * courses[j].getLabHours());
                                courses[j].getSemester().getNumberOfRoomsInDay()[i] -= (courses[j].getLecGroups().size() * courses[j].getLabHours());
                                subtracted[j] += courses[j].getLecGroups().size() * courses[j].getLabHours();
                            }
                        }
                    }
                    if (fetted) {
//                            semester.setSplitted(true);
                        c = new coursesWithDay();
                        c.setCourses(courses);
                        c.setDay(i);
                        for (int j = 0; j < courses.length; j++) {
                            courses[j].getStaff().getNumberOfFreeHoursInDays()[i] += subtracted[j];
                            courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] += subtracted[j];
                            courses[j].getSemester().getNumberOfRoomsInDay()[i] += subtracted[j];
                            if (courses[j].getStaff().getIssymmetric()[i % 3]) {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] += subtracted[j];
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] += subtracted[j];
                                courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] += subtracted[j];
                            }
                        }
                        break;
                    } else {
                        for (int j = 0; j < subtracted.length; j++) {
                            courses[j].getStaff().getNumberOfFreeHoursInDays()[i] += subtracted[j];
                            courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] += subtracted[j];
                            courses[j].getSemester().getNumberOfRoomsInDay()[i] += subtracted[j];
                            if (courses[j].getStaff().getIssymmetric()[i % 3]) {
                                courses[j].getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] += subtracted[j];
                                courses[j].getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] += subtracted[j];
                                courses[j].getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] += subtracted[j];
                            }
                        }
                    }
                }
            }
        }
        return c;
    }

    void removeCoursesToTheLecuterTimeSplitedSemester(splitedSemestersWithDays splitedSemesters, Course course
    ) {
        for (int i = 0; i < splitedSemesters.getCourses().size(); i++) {
            if (splitedSemesters.getCourses().get(i).contains(course)) {
                splitedSemesters.getCourses().get(i).remove(course);
                course.getStaff().getNumberOfFreeHoursInDays()[i] += course.getLecGroups().size();
                course.getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] = (course.getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] - course.getLecGroups().size());
                course.getSemester().getNumberOfRoomsInDay()[i] = (course.getSemester().getNumberOfRoomsInDay()[i] + course.getLecGroups().size());
                if (splitedSemesters.getCourses().get((i + 3) % 6).contains(course)) {
                    splitedSemesters.getCourses().get((i + 3) % 6).remove(course);
                    course.getStaff().getNumberOfFreeHoursInDays()[(i + 3) % 6] += course.getLecGroups().size();
                    course.getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] = (course.getStaff().getBranch().getNumberOfHostingRoomsInDay()[(i + 3) % 6] - course.getLecGroups().size());
                    course.getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] = (course.getSemester().getNumberOfRoomsInDay()[(i + 3) % 6] + course.getLecGroups().size());
                } else {
                    course.getStaff().getNumberOfFreeHoursInDays()[i] += course.getLecGroups().size();
                    course.getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] = (course.getStaff().getBranch().getNumberOfHostingRoomsInDay()[i] - course.getLecGroups().size());
                    course.getSemester().getNumberOfRoomsInDay()[i] = (course.getSemester().getNumberOfRoomsInDay()[i] + course.getLecGroups().size());
                }
                break;
            }
        }
    }

    void addCoursesToTheLecuterTimeSplitedSemester(splitedSemestersWithDays splitedSemesters, coursesWithDay courses
    ) {
        //System.out.println("");
        splitedSemesters.add(
                courses.getCourses(),
                courses.getDay()
        );
        for (int j = 0; j < courses.getCourses().size(); j++) {
            if (courses.getCourses().get(j).getStaff().getIssymmetric()[courses.getDay() % 3]) {
                courses.getCourses().get(j).getStaff().getNumberOfFreeHoursInDays()[courses.getDay()] -= courses.getCourses().get(j).getLecGroups().size();
                courses.getCourses().get(j).getStaff().getBranch().getNumberOfHostingRoomsInDay()[courses.getDay()] = (courses.getCourses().get(j).getStaff().getBranch().getNumberOfHostingRoomsInDay()[courses.getDay()] - courses.getCourses().get(j).getLecGroups().size());
                courses.getCourses().get(j).getSemester().getNumberOfRoomsInDay()[courses.getDay()] = (courses.getCourses().get(j).getSemester().getNumberOfRoomsInDay()[courses.getDay()] - courses.getCourses().get(j).getLecGroups().size());
                courses.getCourses().get(j).getStaff().getNumberOfFreeHoursInDays()[(courses.getDay() + 3) % 6] -= courses.getCourses().get(j).getLecGroups().size();
                courses.getCourses().get(j).getStaff().getBranch().getNumberOfHostingRoomsInDay()[(courses.getDay() + 3) % 6] = (courses.getCourses().get(j).getStaff().getBranch().getNumberOfHostingRoomsInDay()[(courses.getDay() + 3) % 6] - courses.getCourses().get(j).getLecGroups().size());
                courses.getCourses().get(j).getSemester().getNumberOfRoomsInDay()[(courses.getDay() + 3) % 6] = (courses.getCourses().get(j).getSemester().getNumberOfRoomsInDay()[(courses.getDay() + 3) % 6] - courses.getCourses().get(j).getLecGroups().size());
            } else if (courses.getCourses().get(j).getStaff().getFreeTime().dayStartEnd[courses.getDay()].startSession != null) {

                courses.getCourses().get(j).getStaff().getNumberOfFreeHoursInDays()[courses.getDay()] -= courses.getCourses().get(j).getLecGroups().size() * courses.getCourses().get(j).getLabHours();
                courses.getCourses().get(j).getStaff().getBranch().getNumberOfHostingRoomsInDay()[courses.getDay()] -= (courses.getCourses().get(j).getLecGroups().size() * courses.getCourses().get(j).getLabHours());
                courses.getCourses().get(j).getSemester().getNumberOfRoomsInDay()[courses.getDay()] -= (courses.getCourses().get(j).getLecGroups().size() * courses.getCourses().get(j).getLabHours());
            }
        }
    }

    Course[] getAllCoursesDataForLectuer(List<Integer> coursesIds, Course[] courses,
            ArrayList<Branch> allBranchesWithHostingRooms
    ) {
        connection conn = new connection();
        ArrayList<Course> cours = new ArrayList<>();
        try {
            ResultSet rs = conn.select("select courses.id, courses.lecHours, courses.SemesterId,  courses.lectureGroupId, coursesstaff.staffId, coursesstaff.BranchId, semester.number"
                    + " from courses "
                    + "inner join coursesstaff on coursesstaff.courseId=courses.id "
                    + "inner join semester on SemesterId=semester.id "
                    + "where  courses.id in "
                    + "(" + coursesIds.toString().substring(1, coursesIds.toString().length() - 1) + ")"
                    + " and coursesstaff.staffId in "
                    + "(select id from staff where JobTypeId=1)");
            int i = 0;
            while (rs.next()) {
                boolean x = false;
                for (int j = 0; j < i; j++) {
                    if (rs.getInt(1) == cours.get(j).getId()) {
                        x = true;
                    }
                }
                if (!x) {
                    courses[0] = new Course(rs.getInt(1));
                    cours.add(courses[0]);
                    courses[0].setLectureHours(rs.getInt(2));
                    courses[0].setSemester(new Semester(rs.getInt(3), rs.getInt(7)));
                    courses[0].setLectuerGroup(new LectureGroup(rs.getInt(4)));
                    courses[0].getLectuerGroup().getTheLecGroups();
                    courses[0].setLecGroups(courses[0].getLectuerGroup().getLecGroups());
                    for (int j = 0; j < courses[0].getLecGroups().size(); j++) {
                        courses[0].getLecGroups().get(j).remaining = courses[0].getLectureHours();
                    }
                    courses[0].setStaff(new Staff(rs.getInt(5), new Branch(rs.getInt(6))));
                    for (Branch allBranchesWithHostingRoom : allBranchesWithHostingRooms) {
                        if (allBranchesWithHostingRoom.getId() == rs.getInt(6)) {
                            courses[0].getStaff().setBranch(allBranchesWithHostingRoom);
                            break;
                        }
                    }
                    i++;
                }
            }

            courses = cours.toArray(Course[]::new);
            getOtherSemesters(coursesIds, courses);
            return courses;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    void getOtherSemesters(List<Integer> coursesIds, Course[] courses
    ) {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select "
                    + "courseId, semesterId, semester.id "
                    + "from coursesWithMoreThanSemester "
                    + "inner join semester on semesterId=semester.id "
                    + "where courseId in "
                    + "(" + coursesIds.toString().substring(1, coursesIds.toString().length() - 1) + ")");
            while (rs.next()) {
                for (int i = 0; i < courses.length; i++) {
                    if (rs.getInt(1) == courses[i].getId()) {
                        courses[i].getOtherSemster().add(new Semester(rs.getInt(2)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }

    Course[] getAllCoursesDataForLectuer(Course[] courses, ArrayList<Branch> allBranchesWithHostingRooms
    ) {
        ArrayList<Integer> coursesIds = new ArrayList<>();
        ArrayList<Course> cours = new ArrayList<>();
        for (int i = 0; i < courses.length; i++) {
            coursesIds.add(courses[i].getId());
        }
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select courses.id,"
                    + "courses.lecHours,"
                    + "courses.SemesterId,"
                    + "courses.lectureGroupId,"
                    + "coursesstaff.staffId,"
                    + "coursesstaff.BranchId "
                    + "from courses"
                    + " inner join coursesstaff on coursesstaff.cou1rseId=courses.id"
                    + " where  courses.id in (" + coursesIds.toString().substring(1, coursesIds.toString().length() - 1) + ") "
                    + "and coursesstaff.staffId in (select id from staff where JobTypeId = 1)");
            int i = 0;
            while (rs.next()) {
                boolean x = false;
                for (int j = 0; j < i; j++) {
                    if (rs.getInt(1) == cours.get(j).getId()) {
                        x = true;
                    }
                }
                if (!x) {
                    courses[0] = new Course(rs.getInt(1));
                    cours.add(courses[0]);
                    courses[0].setLectureHours(rs.getInt(2));
                    courses[0].setSemester(new Semester(rs.getInt(3)));
                    courses[0].setLectuerGroup(new LectureGroup(rs.getInt(4)));
                    courses[0].getLectuerGroup().getTheLecGroups();
                    courses[0].setLecGroups(courses[i].getLectuerGroup().getLecGroups());
                    for (int j = 0; j < courses[i].getLecGroups().size(); j++) {
                        courses[i].getLecGroups().get(j).remaining = courses[i].getLectureHours();
                    }
                    courses[i].setStaff(new Staff(rs.getInt(5), new Branch(rs.getInt(6))));
                    for (Branch allBranchesWithHostingRoom : allBranchesWithHostingRooms) {
                        if (allBranchesWithHostingRoom.getId() == rs.getInt(6)) {
                            courses[i].getStaff().setBranch(allBranchesWithHostingRoom);
                            break;
                        }
                    }
                    i++;
                }
            }
            courses = cours.toArray(Course[]::new);
            getOtherSemesters(coursesIds, courses);
            return courses;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    Course[] getAllCoursesDataForSections(List<Integer> coursesIds, Course[] courses,
            int branchId
    ) {
        connection conn = new connection();
        ArrayList<Course> cours = new ArrayStack<>();
        try {
            ResultSet rs = conn.select("select courses.id,"
                    + "courses.labHours,"
                    + "courses.SemesterId,"
                    + "courses.SectionsNumberOfGroupsID,"
                    + "coursesstaff.staffId "
                    + "from courses "
                    + "inner join coursesstaff "
                    + "on coursesstaff.courseId=courses.id "
                    + "where  courses.id in "
                    + "(" + coursesIds.toString().substring(1, coursesIds.toString().length() - 1) + ")"
                    + " and coursesstaff.staffId in "
                    + "(select id from staff where JobTypeId=2 and branchId = " + branchId + ")");
            int i = 0;
            while (rs.next()) {
                boolean x = false;
                for (int j = 0; j < i; j++) {
                    if (rs.getInt(1) == cours.get(j).getId()) {
                        x = true;
                    }
                }
                if (!x) {

                    courses[0] = new Course(rs.getInt(1));
                    cours.add(courses[0]);
                    courses[0].setLabHours(rs.getInt(2));
                    courses[0].setSemester(new Semester(rs.getInt(3)));
                    courses[0].setSectionGroup(new SectionGroup(rs.getInt(4)));
                    courses[0].setStaff(new Staff(rs.getInt(5)));
                    i++;
                }

            }
            courses = (Course[]) cours.toArray(Course[]::new);
            rs = conn.select("select sectionsfgroups.sectionsnumberofgroupsId,sectionsfgroups.numberOfGroups "
                    + "from sectionsfgroups"
                    + " where sectionsfgroups.sectionsnumberofgroupsId in "
                    + "(" + getTheSectionGroups(courses).toString().substring(1, getTheSectionGroups(courses).toString().length() - 1)
                    + ") and sectionsfgroups.branchId=" + branchId);
            while (rs.next()) {
                for (Course course : courses) {
                    if (course.getSectionGroup().getId() == rs.getInt(1)) {
                        course.getSectionGroup().setNumberOfGroups(rs.getInt(2), course.getLabHours());
                    }
                }
            }
            return courses;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    Course[] getTheSectionsWithMoreThanOneStaff(List<Integer> coursesIds, Course[] courses,
            int branchId
    ) {
        connection conn = new connection();
        try {
            ArrayList<Course> courses1 = new ArrayList<>();
            ResultSet rs = conn.select("SELECT * "
                    + "FROM timetablemanagementsystem.sectiongroupsstaffs"
                    + " where courseId in "
                    + "(" + coursesIds.toString().substring(1, coursesIds.toString().length() - 1) + ")"
                    + "and branchId = " + branchId);
            while (rs.next()) {

                for (int i = 0; i <= courses1.size(); i++) {
                    if (i == courses1.size()) {
                        Course c = new Course(rs.getInt(1), new Staff(rs.getInt(2)));
                        courses1.add(c);
                        c.setGroup(new SectionGroup());
                        c.getGroup().getGroupsNames().add(rs.getInt(3));
                        break;
                    } else if (courses1.get(i).getId() == rs.getInt(1)
                            && courses1.get(i).getStaff().getId() == rs.getInt(2)) {
                        courses1.get(i).getGroup().getGroupsNames().add(rs.getInt(3));
                        break;
                    }
                }
            }

            for (int i = 0; i < courses1.size(); i++) {
                for (Course course : courses) {
                    if (course.getId() == courses1.get(i).getId()) {
                        ArrayList<Integer> arr = courses1.get(i).getGroup().getGroupsNames();
                        int value = courses1.get(i).getSectionGroup().getGroupsNames().size();
                        courses1.get(i).setSemester(course.getSemester());
                        courses1.get(i).setLabHours(course.getLabHours());
                        courses1.get(i).setRoomsTypesForSection(course.getRoomsTypesForSection());
                        courses1.get(i).setGroup(new SectionGroup(course.getSectionGroup().getId()));
                        courses1.get(i).getGroup().setNumberOfGroups(arr, course.getLabHours());
                        course.getSectionGroup().setNumberOfGroups(course.getSectionGroup().getNumberOfGroups() - value, course.getLabHours());
                    }
                }
            }

            Course[] course = new Course[courses.length + courses1.size()];
            System.arraycopy(courses, 0, course, 0, courses.length);

            for (int i = courses.length; i < courses1.size() + courses.length; i++) {
                course[i] = courses1.get(i - courses.length);
            }
            return course;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return null;
    }

    void makeSectionGroupsFreeTimeRefranceInASemster(ArrayList<Semester> semesters
    ) {
        for (Semester semester : semesters) {
            for (int i = 0; i < semester.getCourses().size(); i++) {
                for (int j = i + 1; j < semester.getCourses().size(); j++) {
                    if (semester.getCourses().get(i).getSectionGroup().getId() == semester.getCourses().get(j).getSectionGroup().getId()) {
                        for (sectionGroups sectionGroupBranche : semester.getCourses().get(i).getSectionGroup().getGroups()) {
                            for (sectionGroups sectionGroupBranche1 : semester.getCourses().get(j).getSectionGroup().getGroups()) {
                                if (sectionGroupBranche1.getNumber() == sectionGroupBranche.getNumber()) {
                                    sectionGroupBranche1.setFreeTime(sectionGroupBranche.getFreeTime());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void makeTheLecGroupFreeTimeRefrence(Semester semester
    ) {
        Course[] courses = (Course[]) semester.getCourses().toArray(new Course[semester.getCourses().size()]);
        for (int i = 0; i < courses.length; i++) {
            for (int j = 0; j < courses[i].getLecGroups().size(); j++) {
                for (int k = i + 1; k < courses.length; k++) {
                    for (int l = 0; l < courses[k].getLecGroups().size(); l++) {
                        if (courses[k].getLecGroups().get(l).getId() == courses[i].getLecGroups().get(j).getId()) {
                            courses[k].getLecGroups().get(l).setFreeTime(courses[i].getLecGroups().get(j).getFreeTime());
                        }
                    }
                }
            }
        }
    }

    ArrayList<Integer> getTheSectionGroups(Course[] courses
    ) {
        ArrayList<Integer> sectionGroups = new ArrayList<>();
        for (Course course : courses) {
            for (int j = 0; j <= sectionGroups.size(); j++) {
                if (j <= sectionGroups.size()) {
                    sectionGroups.add(course.getSectionGroup().getId());
                    break;
                } else if (sectionGroups.get(j) == course.getSectionGroup().getId()) {
                    break;
                }
            }
        }
        return sectionGroups;
    }

    /**
     * add the branches with all rooms to each semester
     *
     * @param semesters
     * @param branchs
     */
    void makeBranchesForEachSemester(ArrayList<Semester> semesters, ArrayList<Branch> branchs
    ) {
        for (Semester semester : semesters) {
            semester.setBranchs(branchs);
        }
    }

    ArrayList<Branch> makeNewObjectOfBranchesWithoutRooms(ArrayList<Branch> branchs
    ) {
        ArrayList<Branch> b = new ArrayList<>();
        for (Branch branch : branchs) {
            b.add(new Branch(branch.getId()));
        }
        return b;
    }

    /**
     * @param courses
     * @return array list of the semesters in the courses and make the reference
     */
    ArrayList<Semester> getSemesters(Course[] courses
    ) {
        ArrayList<Semester> s = new ArrayList<>();
        for (Course course : courses) {
            for (int j = 0; j <= s.size(); j++) {
                if (j == s.size()) {
                    Semester sem = new Semester(course.getSemester().getId());
                    s.add(sem);
                    sem.getCourses().add(course);
                    course.setSemester(sem);
                    break;
                } else if (course.getSemester().getId() == s.get(j).getId()) {
                    s.get(j).getCourses().add(course);
                    course.setSemester(s.get(j));
                    break;
                }
            }
        }

        for (Course course : courses) {
            for (int i = 0; i < course.getOtherSemster().size(); i++) {
                for (int j = 0; j < s.size(); j++) {
                    if (course.getOtherSemster().get(i).getId() == s.get(j).getId()) {
                        course.getOtherSemster().set(i, s.get(j));
                    }
                }
            }
        }
        return s;
    }

    ArrayList<Semester> getSemesters(ArrayList<Course> courses
    ) {
        ArrayList<Semester> s = new ArrayList<>();
        for (Course course : courses) {
            for (int j = 0; j <= s.size(); j++) {
                if (j == s.size()) {
                    Semester sem = new Semester(course.getSemester().getId());
                    s.add(sem);
                    sem.getCourses().add(course);
                    course.setSemester(sem);
                    break;
                } else if (course.getSemester().getId() == s.get(j).getId()) {
                    s.get(j).getCourses().add(course);
                    course.setSemester(s.get(j));
                    break;
                }
            }
        }
        return s;
    }

    /**
     * make the staff reference in all the courses
     *
     * @param courses
     */
    void makeTheStaffRefrance(Course[] courses
    ) {
        for (int i = 0; i < courses.length; i++) {
            for (int j = i + 1; j < courses.length; j++) {
                if (courses[i].getStaff().getId() == courses[j].getStaff().getId()) {
                    courses[j].setStaff(courses[i].getStaff());
                    break;
                }
            }
        }
    }

    /**
     * get the staff freeTime from the database
     *
     * @param courses
     * @return array list of the staff
     */
    ArrayList<Staff> getStaffFreeTime(Course[] courses
    ) {
        ArrayList<Staff> staff = new ArrayList<>();
        for (Course course : courses) {
            if (!staff.contains(course.getStaff())) {
                Staff s = course.getStaff();
                staff.add(s);
            }
        }
        staff.sort((t, t1) -> {
            return t.getId() - t1.getId();
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < staff.size(); i++) {
            sb.append(staff.get(i).getId()).append(", ");
        }
        connection conn = new connection();
        try {
            int j = 0;
            ResultSet rs = conn.select("select * from freetimeforstaff where staffid in (" + sb.substring(0, sb.length() - 2) + ")  order by staffid ");
            while (rs.next()) {
                for (int i = j; i < staff.size(); i++) {
                    if (staff.get(i).getId() == rs.getInt(1)) {
                        staff.get(i).getFreeTime().addFreeTime(rs.getInt(2), rs.getInt(3), rs.getInt(4));
                        j = i;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            conn.close();
        }
        return staff;
    }

    /**
     * make the branches in the lecGroups reference to make the rooms and the
     * freetTime reference same as the branches with all rooms
     *
     * @param courses
     * @param branchs
     */
    void makeTheLecGroupBranchesRefrence(Course[] courses, ArrayList<Branch> branchs) {
        for (int i = 0; i < branchs.size(); i++) {
            for (Course course : courses) {
                for (int k = 0; k < course.getLecGroups().size(); k++) {
                    for (int l = 0; l < course.getLecGroups().get(k).getBranchs().size(); l++) {
                        if (course.getLecGroups().get(k).getBranchs().get(l).getId() == branchs.get(i).getId()) {
                            course.getLecGroups().get(k).getBranchs().set(l, branchs.get(i));
                        }
                    }
                }
            }
        }
    }

    /**
     * make the hosting rooms reference in rooms in the branches with
     * hostingRooms and branches with all rooms
     *
     * @param courses
     */
    void makeHostingRoomsReference(Course[] courses
    ) {
        for (Course course : courses) {
            for (int j = 0; j < course.getStaff().getBranch().getRooms().size(); j++) {
                for (int k = 0; k < course.getLecGroups().size(); k++) {
                    for (int l = 0; l < course.getLecGroups().get(k).getBranchs().size(); l++) {
                        if (course.getLecGroups().get(k).getBranchs().get(l).getId() == course.getStaff().getBranch().getId()) {
                            for (int m = 0; m < course.getLecGroups().get(k).getBranchs().get(l).getRooms().size(); m++) {
                                if (course.getLecGroups().get(k).getBranchs().get(l).getRooms().get(m).getId() == course.getStaff().getBranch().getRooms().get(j).getId()) {
                                    course.getStaff().getBranch().getRooms().set(j, course.getLecGroups().get(k).getBranchs().get(l).getRooms().get(m));

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void assineLecGroupsToSemester(Semester semester, ArrayList<Semester> semesters
    ) {
        for (int i = 0; i < semesters.size(); i++) {
            for (int j = 0; j < semesters.get(i).getCourses().size(); j++) {
                for (int k = 0; k < semesters.get(i).getCourses().get(j).getLecGroups().size(); k++) {
                    if (!semesters.get(i).getAllLecGroups().contains(semesters.get(i).getCourses().get(j).getLecGroups().get(k))) {
                        semesters.get(i).getAllLecGroups().add(semesters.get(i).getCourses().get(j).getLecGroups().get(k));
                    }
                }
            }
        }

        for (int j = 0; j < semester.getCourses().size(); j++) {
            for (int k = 0; k < semester.getCourses().get(j).getLecGroups().size(); k++) {
                if (!semester.getAllLecGroups().contains(semester.getCourses().get(j).getLecGroups().get(k))) {
                    semester.getAllLecGroups().add(semester.getCourses().get(j).getLecGroups().get(k));
                }
            }
        }
    }

    void assineLecGroupsToSemester(ArrayList<Semester> semesters
    ) {
        for (int i = 0; i < semesters.size(); i++) {
            for (int j = 0; j < semesters.get(i).getCourses().size(); j++) {
                for (int k = 0; k < semesters.get(i).getCourses().get(j).getLecGroups().size(); k++) {
                    if (!semesters.get(i).getAllLecGroups().contains(semesters.get(i).getCourses().get(j).getLecGroups().get(k))) {
                        semesters.get(i).getAllLecGroups().add(semesters.get(i).getCourses().get(j).getLecGroups().get(k));
                    }
                }
            }
        }
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}

class coursesWithDay {

    private ArrayList<Course> courses;
    private int day;

    public coursesWithDay() {
        courses = new ArrayList<>();
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
    public void setCourses(Course[] courses) {
        this.courses = new ArrayList<>();
        for (int i = 0; i < courses.length; i++) {
            this.courses.add(courses[i]);
        }
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

}
