package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

import java.util.ArrayList;

public class SectionGroup {

    private int id;
    private String name;
    private sectionGroups[] Groups;
    private int numberOfGroups;
    private ArrayList<Integer> groupsNames;
    

    public SectionGroup(int id) {
        this.id = id;
    }

    public SectionGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SectionGroup() {
        groupsNames=new ArrayList<>();
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
     * @return the numberOfGroups
     */
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * @param numberOfGroups the numberOfGroups to set
     * @param remaining
     */
    public void setNumberOfGroups(int numberOfGroups, int remaining) {
        this.numberOfGroups = numberOfGroups;
        Groups = new sectionGroups[numberOfGroups];
        for (int i = 0; i < numberOfGroups; i++) {
            Groups[i] = new sectionGroups(i + 1);
            Groups[i].remaining = remaining;
        }
    }

    public void setNumberOfGroups(ArrayList<Integer> Groups, int remaining) {
        this.numberOfGroups = Groups.size();
        this.Groups = new sectionGroups[Groups.size()];
        for (int i = 0; i < Groups.size(); i++) {
            this.Groups[i] = new sectionGroups(Groups.get(i));
            this.Groups[i].remaining = remaining;
        }
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    /**
     * @return the sectionGroupBranches
     */
    public sectionGroups[] getGroups() {
        return Groups;
    }

    /**
     * @param sectionGroupBranches the sectionGroupBranches to set
     */
    public void setGroups(sectionGroups[] sectionGroupBranches) {
        this.Groups = sectionGroupBranches;
    }

    /**
     * @return the groupsNames
     */
    public ArrayList<Integer> getGroupsNames() {
        return groupsNames;
    }

    /**
     * @param groupsNames the groupsNames to set
     */
    public void setGroupsNames(ArrayList<Integer> groupsNames) {
        this.groupsNames = groupsNames;
    }

}

class sectionGroups {

    private int number;
    private FreeTime freeTime;
    private String name;
    int remaining;

    public sectionGroups(int number) {
        name = "s" + number;
        freeTime = new FreeTime(1);
        this.number = number;
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
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

}
