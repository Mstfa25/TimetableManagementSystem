package com.team.timetableManagmentSystem.DTOs;

public class Staff {

    private int id;
    private String name;
    private jobType type;
    private Branch branch;

    public Staff(int id) {
        this.id = id;
        type=null;
        branch=null;
    }

    public Staff(int id, String name, int typeid, String typeName, int branchId, String branchName) {
        this.id = id;
        this.name = name;
        this.type = new jobType(typeid, typeName);
        this.branch = new Branch(branchId, branchName);
    }

    public Staff(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Staff() {
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

    /**
     * @return the type
     */
    public jobType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(jobType type) {
        this.type = type;
    }

    /**
     * @return the branch
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
