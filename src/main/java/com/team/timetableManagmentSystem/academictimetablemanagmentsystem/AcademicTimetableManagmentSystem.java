package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import java.util.ArrayList;

public class AcademicTimetableManagmentSystem {

    public static void main(String[] args) {
        Timetable t = new Timetable(0);
        /*
        
        25
        2
        27
        -----------------
         */
        t.createLectureTimetable(1, 3, 4, 5, 6, 7,
                8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23,
                24, 26, 28, 29, 30, 31,
                32, 33, 34, 35, 36, 37, 38, 39);
        t.createSectionTimetable(t, 4, 1, 2, 3, 4);

//        t.createSectionTimetable(t, 1, 2001, 2002,
//                2003, 2004, 2005, 2007, 2008,
//                2009, 2010, 2011, 2012, 2013, 2014,
//                2015, 2016, 2017, 2018, 2021,
//                2022, 2023);
        ArrayList<TimeInTimetable> timesInTimetable = t.getTimesInTimetable();
        for (int i = 0; i < timesInTimetable.size(); i++) {
            System.out.println(timesInTimetable.get(i).getDay() + "\t"
                    + timesInTimetable.get(i).getStartingTime() + "\t"
                    + timesInTimetable.get(i).getEndingTime() + "\t"
                    + timesInTimetable.get(i).getStaff().getId() + "\t"
                    + timesInTimetable.get(i).getCourse().getId() + "\t"
                    + timesInTimetable.get(i).getCourse().getSemester().getId() + "\t"
                    + timesInTimetable.get(i).getHostingRoom().getId() + "\t"
                    + timesInTimetable.get(i).getHostingBranch().getId() + "\t"
                    + ((timesInTimetable.get(i).getSectionGroupName() != null) ? timesInTimetable.get(i).getSectionGroupName() : (timesInTimetable.get(i).getLecGroup().getId()) + "\t" + timesInTimetable.get(i).getBranchs()) + "\t" + timesInTimetable.get(i).getRooms());
        }
    }

}
