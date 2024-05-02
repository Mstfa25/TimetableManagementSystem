
package com.team.timetableManagmentSystem.DTOs;

public class userRole {
    private int id;
    private String RoleName;

    public userRole() {
    }

    public userRole(int id, String RoleName) {
        this.id = id;
        this.RoleName = RoleName;
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the RoleName
     */
    public String getRoleName() {
        return RoleName;
    }

    /**
     * @param RoleName the RoleName to set
     */
    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
}
