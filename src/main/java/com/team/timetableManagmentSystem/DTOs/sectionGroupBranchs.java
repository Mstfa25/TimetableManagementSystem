
package com.team.timetableManagmentSystem.DTOs;

import java.util.ArrayList;

public class sectionGroupBranchs {
    private SectionGroup sectionGroup;
    private ArrayList<BranchWithNumberOfSectionGroups> branchWithNumberOfSectionGroupses;

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

    /**
     * @return the branchWithNumberOfSectionGroupses
     */
    public ArrayList<BranchWithNumberOfSectionGroups> getBranchWithNumberOfSectionGroupses() {
        return branchWithNumberOfSectionGroupses;
    }

    /**
     * @param branchWithNumberOfSectionGroupses the branchWithNumberOfSectionGroupses to set
     */
    public void setBranchWithNumberOfSectionGroupses(ArrayList<BranchWithNumberOfSectionGroups> branchWithNumberOfSectionGroupses) {
        this.branchWithNumberOfSectionGroupses = branchWithNumberOfSectionGroupses;
    }
}

