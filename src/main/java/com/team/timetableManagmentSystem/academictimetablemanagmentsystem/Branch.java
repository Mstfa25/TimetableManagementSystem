package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * used in the operation of creating timeTable
 * its data are from the database
 * @data_field  id to specify the branch
 * @data_field name to specify the branch name
 * @data_field to specify the rooms in this branch
 * @data_field numberOfRoomsInDay is an integer array to have the number of rooms free in each time slot
 * @data_field numberOfHostingRoomsInDay is an integer array to have the number of Hosting rooms free in each time slot
 * @author Mostafa
 */
public class Branch {

    private int id;
    private String name;
    private ArrayList<Room> rooms;
    private FreeTime freeTime;
    private int[] numberOfRoomsInDay;
    private int[] numberOfHostingRoomsInDay;

    public Branch(int id) {
        this.id = id;
        rooms = new ArrayList<>();
        numberOfRoomsInDay = new int[6];
        numberOfHostingRoomsInDay = new int[6];
        freeTime = new FreeTime(1);
    }

    public Branch(int id, String name) {
        this.id = id;
        this.name = name;
        rooms = new ArrayList<>();
        numberOfRoomsInDay = new int[6];
        numberOfHostingRoomsInDay = new int[6];
        freeTime = new FreeTime(1);
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
     * @return the rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
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
     * get all the hosting rooms from the database
     * as hosting room type id is 3
     */
    void getHostingRooms() {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select id "
                    + "from rooms"
                    + " where branchId = " + id
                    + " and TypeId = " + 3);
            while (rs.next()) {
                getRooms()
                        .add(
                                new Room(
                                        rs.getInt(1),
                                        3)
                        );
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }

    /**
     * get all rooms in the branch from the database
     */

    void getAllRooms() {
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select "
                    + "id,"
                    + "TypeId"
                    + " from rooms "
                    + "where branchId = " + id);
            while (rs.next()) {
                getRooms()
                        .add(
                                new Room(
                                        rs.getInt(1),
                                        rs.getInt(2)
                                )
                        );
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }

    /**
     * get all branches from the database with all the rooms in them
     * @return array list of branches each of them has arraylist of rooms
     */
    static ArrayList<Branch> getAllBranchesWithAllRooms() {
        ArrayList<Branch> branchs = new ArrayList<>();
        connection conn = new connection();
        try {
            StringBuilder sb = new StringBuilder();
            ResultSet rs = conn.select("select "
                    + "branch.id,"
                    + "rooms.id,"
                    + "rooms.TypeId"
                    + " from branch "
                    + "inner join rooms "
                    + "     on branch.id = rooms.branchId");
            Branch b1 = null;
            while (rs.next()) {
                if (sb
                        .indexOf(
                                "--" + rs.getInt(1) + "--"
                        ) == -1) {
                    sb.append(
                            "--" + rs.getInt(1) + "--"
                    );
                    b1 = new Branch(
                            rs.getInt(1)
                    );
                    branchs.add(b1);
                    b1
                            .getRooms()
                            .add(
                                    new Room(
                                            rs.getInt(2),
                                            rs.getInt(3)
                                    )
                            );
                } else {
                    for (int i = 0;
                            i < branchs.size();
                            i++) {
                        if (branchs
                                .get(i)
                                .getId() == rs.getInt(1)) {
                            branchs
                                    .get(i)
                                    .getRooms()
                                    .add(new Room(
                                            rs.getInt(2),
                                            rs.getInt(3)
                                    )
                                    );
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        for (Branch branch : branchs) {
            branch.setTheNumberOfRooms();
        }
        return branchs;
    }

    /**
     * get all the branches with the hosting rooms in them
     * as hosting rooms type id is 3
     * @return array list of branches with the hosting rooms in each branch
     */
    static ArrayList<Branch> getAllBranchesWithHostingRooms() {
        ArrayList<Branch> branchs = new ArrayList<>();
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select branch.id,"
                    + "rooms.id,"
                    + "rooms.TypeId"
                    + " from branch"
                    + " inner join rooms "
                    + "     on branch.id = rooms.branchId"
                    + " where rooms.TypeId = 3");
            StringBuilder sb = new StringBuilder();
            Branch b1 = null;
            while (rs.next()) {
                if (sb.indexOf(
                        "--" + rs.getInt(1) + "--"
                ) == -1) {
                    sb.append(
                            "--" + rs.getInt(1) + "--"
                    );
                    b1 = new Branch(
                            rs.getInt(1)
                    );
                    branchs.add(b1);
                    b1
                            .getRooms()
                            .add(
                                    new Room(
                                            rs.getInt(2),
                                            rs.getInt(3)
                                    )
                            );
                } else {
                    for (int i = 0;
                            i < branchs.size();
                            i++) {
                        if (branchs
                                .get(i)
                                .getId() == rs.getInt(1)) {

                            branchs
                                    .get(i)
                                    .getRooms()
                                    .add(
                                            new Room(
                                                    rs.getInt(2),
                                                    rs.getInt(3)
                                            )
                                    );
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        for (Branch branch : branchs) {
            branch.setTheNumberOfHostingRooms();
        }
        return branchs;
    }

    /**
     * get all branches from the database
     * @return array list of branches
     */

    static ArrayList<Branch> getAllBranches() {
        ArrayList<Branch> branchs = new ArrayList<>();
        connection conn = new connection();
        try {
            ResultSet rs = conn.select("select "
                    + "id "
                    + "from branch");
            while (rs.next()) {
                Branch b = new Branch(
                        rs.getInt(1)
                );
                branchs.add(b);
                b.getAllRooms();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
        return branchs;
    }

    /**
     * set the number of hosting rooms in day as it is by default from 8 to 15 so it make it number of rooms by(*) 9
     */
    void setTheNumberOfHostingRooms() {
        setNumberOfHostingRoomsInDay(new int[6]);
        for (int i = 0; i < 6; i++) {
            getNumberOfHostingRoomsInDay()[i] = rooms.size() * 9;
        }

    }

    /**
     * set the number of rooms in day as it is by default from 8 to 15 so it make it number of rooms by(*) 9
     */
    void setTheNumberOfRooms() {
        for (int i = 0; i < 6; i++) {
            getNumberOfRoomsInDay()[i] = rooms.size() * 9;
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

    /**
     * @return the numberOfHostingRoomsInDay
     */
    public int[] getNumberOfHostingRoomsInDay() {
        return numberOfHostingRoomsInDay;
    }

    /**
     * @param numberOfHostingRoomsInDay the numberOfHostingRoomsInDay to set
     */
    public void setNumberOfHostingRoomsInDay(int[] numberOfHostingRoomsInDay) {
        this.numberOfHostingRoomsInDay = numberOfHostingRoomsInDay;
    }

    public Room getAHostingRoomFreeAt(int day, int hour) {
        for (Room room : rooms) {
            if (room.getFreeTime().isFreeAt(day, hour)) {
                return room;
            }
        }
        return null;
    }

    /**
     *
     * @param day int for the day the needed room free in
     * @param hour int for the hour the needed room free in
     * @return if there is a room free at the time given it returns the room
     * it sorts the rooms on there type as if the rooms is not hosting room it will be with high priority
     */
    public Room getARoomFreeAt(int day, int hour) {
        rooms.sort((o1, o2) -> {
            return o1
                    .getRoomtype()
                    .getId() - o2
                            .getRoomtype()
                            .getId();
        });
        for (Room room : rooms) {
            if (room
                    .getFreeTime()
                    .isFreeAt(
                            day,
                            hour)) {
                return room;
            }
        }
        return null;
    }

    /**
     *
     * @param day int for the day the room free in
     * @param hour int for the hour the room free in
     * @param types int is the type of room needed
     * @return the room if exist
     */
    Room getARoomWithATypeFreeAt(int day, int hour, int... types) {
        for (Room room : rooms) {
            for (int i = 0;
                    i < types.length;
                    i++) {

                if (room.getRoomtype()
                        .getId() == types[i]
                        && room.getFreeTime()
                                .isFreeAt(day, hour)) {
                    return room;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + " ";
    }

}
