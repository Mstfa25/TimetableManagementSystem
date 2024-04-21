package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import com.team.timetableManagmentSystem.database.connection;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    void setTheNumberOfHostingRooms() {
        setNumberOfHostingRoomsInDay(new int[6]);
        for (int i = 0; i < 6; i++) {
            getNumberOfHostingRoomsInDay()[i] = rooms.size() * 9;
        }

    }

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

    Room getARoomWithATypeFreeAt(int day, int hour, int... types) {
        for (Room room : rooms) {
            for (int i = 0;
                    i < types.length;
                    i++) {

                if (room
                        .getRoomtype()
                        .getId() == types[i]
                        && room
                                .getFreeTime()
                                .isFreeAt(
                                        day,
                                        hour)) {

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
