/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.timetableManagmentSystem.DTOs;

/**
 *
 * @author Mostafa
 */
public class node {

    public node next;
    public node privous;
    //stratSession is always less than the endSession 
    //  as it is the start time of free time
    public Integer startSession, endSession;

    //value return the string value for this free time 
    String value() {
//        if (startSession <= 12 && endSession <= 12) {
//            return startSession + " pm to " + endSession + " pm.";
//        } else if (startSession <= 12 && endSession > 12) {
//            return startSession + " pm to " + (endSession % 12) + " am.";
//        } else {
//            return startSession + " am to " + endSession + " am.";
//        }

        return (startSession % 12) + ((startSession / 12 == 0) ? " am to " : " pm to ") + (endSession % 12) + ((endSession / 12 == 0) ? " am." : " pm.");
    }
}
