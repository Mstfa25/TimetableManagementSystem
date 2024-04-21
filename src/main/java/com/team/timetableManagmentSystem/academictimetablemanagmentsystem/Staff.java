package com.team.timetableManagmentSystem.academictimetablemanagmentsystem;

public class Staff {

    private int id;
    private String name;
    private jobType Type;
    private Branch branch;
    private FreeTime freeTime;
    private boolean[] isSymmetric;
    private int[] numberOfFreeHoursInDays;

    public Staff(int id, Branch branch) {
        this.id = id;
        this.branch = branch;
        freeTime = new FreeTime();
    }
    
    
    public Staff(int id, String name, int typeid, String typeName, int branchId, String branchName) {
        this.id = id;
        this.name = name;
        this.Type = new jobType(typeid, typeName);
        this.branch = new Branch(branchId, branchName);
    }

    public Staff(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Staff(int id) {
        this.id = id;
        freeTime = new FreeTime();
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    void computeNumberOfFreeHoursInDay(){
        numberOfFreeHoursInDays=freeTime.getNumberOfFreeHouresInEachDay();       
    }
    
    void cheackIsSymmetric(){
        boolean [] b=freeTime.getDaysOfFreeTime();
        isSymmetric=new boolean[3];
        isSymmetric[0]=b[0]&&b[3];
        isSymmetric[1]=b[1]&&b[4];
        isSymmetric[2]=b[2]&&b[5];
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

    void getTheFreeTime() {
        freeTime.getDataForStuff(id);
    }

    /**
     * @return the issymmetric
     */
    public boolean[] getIssymmetric() {
        return isSymmetric;
    }

    /**
     * @param issymmetric the issymmetric to set
     */
    public void setIssymmetric(boolean[] issymmetric) {
        this.isSymmetric = issymmetric;
    }

    /**
     * @return the numberOfFreeHoursInDays
     */
    public int[] getNumberOfFreeHoursInDays() {
        return numberOfFreeHoursInDays;
    }

    /**
     * @param numberOfFreeHoursInDays the numberOfFreeHoursInDays to set
     */
    public void setNumberOfFreeHoursInDays(int[] numberOfFreeHoursInDays) {
        this.numberOfFreeHoursInDays = numberOfFreeHoursInDays;
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
     * @return the Type
     */
    public jobType getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(jobType Type) {
        this.Type = Type;
    }

}
