package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class LecGroup {

    private int id;
    private String name;
    private ArrayList<Branch> branchs;
    private LectureGroup lectuerGoup;

    public LecGroup(int id) {
        this.id = id;
    }

    public LecGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public LecGroup(int id, String name,  int lectuerGoupid,String lectuerGroupName) {
        this.id = id;
        this.name = name;
        this.lectuerGoup=new LectureGroup(lectuerGoupid, lectuerGroupName);
    }
    
    
    
    
    

    public LecGroup(ArrayList<Branch> branchs, LectureGroup lectuerGoup) {
        this.branchs = branchs;
        this.lectuerGoup = lectuerGoup;
    }

    public LecGroup() {
    }
    

    /**
     * @return the branchs
     */
    public ArrayList<Branch> getBranchs() {
        return branchs;
    }

    /**
     * @param branchs the branchs to set
     */
    public void setBranchs(ArrayList<Branch> branchs) {
        this.branchs = branchs;
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
}
