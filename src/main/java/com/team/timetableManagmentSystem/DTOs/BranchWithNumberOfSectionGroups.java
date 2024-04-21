/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.timetableManagmentSystem.DTOs;

/**
 *
 * @author Mostafa
 */
public class BranchWithNumberOfSectionGroups{
    private Branch branch;
    private int numberOfSectionGroups;

    public BranchWithNumberOfSectionGroups() {
    }

    public BranchWithNumberOfSectionGroups(int branchId,String branchName, int numberOfSectionGroups) {
        this.branch = new Branch(branchId,branchName);
        this.numberOfSectionGroups = numberOfSectionGroups;
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
     * @return the numberOfSectionGroups
     */
    public int getNumberOfSectionGroups() {
        return numberOfSectionGroups;
    }

    /**
     * @param numberOfSectionGroups the numberOfSectionGroups to set
     */
    public void setNumberOfSectionGroups(int numberOfSectionGroups) {
        this.numberOfSectionGroups = numberOfSectionGroups;
    }
}