package com.team.timetableManagmentSystem.DTOs;

public class sectionGroupBranchs {

    private int numberOfGroups;
    private Branch branch;
    private SectionGroup sectionGroup;

    public sectionGroupBranchs() {
    }

    public sectionGroupBranchs(int numberOfGroups, int branchId, String branchName, int sectionGroupId, String sectionGroupName) {
        this.numberOfGroups = numberOfGroups;
        this.branch = new Branch(branchId, branchName);
        this.sectionGroup = new SectionGroup(sectionGroupId, sectionGroupName);
    }

    /**
     * @return the numberOfGroups
     */
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * @param numberOfGroups the numberOfGroups to set
     */
    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
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
     * @return the sectionGroup
     */
    public SectionGroup getSectionGroup() {
        return sectionGroup;
    }

    /**
     * @param sectionGroup the sectionGroup to set
     */
    public void setSectionGroup(SectionGroup sectionGroup) {
        this.sectionGroup = sectionGroup;
    }
}
