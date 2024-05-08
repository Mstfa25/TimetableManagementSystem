package com.team.timetableManagmentSystem.DTOs;

/**
 *
 * @author mosta
 */
public class lectureGroupBranchs {

    private Branch branch;
    private LecGroup lecGroup;

    public lectureGroupBranchs() {
    }

    public lectureGroupBranchs(int branchId, String branchName, int lecGroupId, String lecGroupName, int lectureGroupId, String lectureGroupName) {
        this.branch = new Branch(branchId, branchName);
        this.lecGroup = new LecGroup(lecGroupId, lecGroupName, lectureGroupId, lectureGroupName);
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

    /**
     * @return the lecGroup
     */
    public LecGroup getLecGroup() {
        return lecGroup;
    }

    /**
     * @param lecGroup the lecGroup to set
     */
    public void setLecGroup(LecGroup lecGroup) {
        this.lecGroup = lecGroup;
    }

}
