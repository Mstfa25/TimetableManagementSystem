/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.timetableManagmentSystem.DTOs;

/**
 *
 * @author Mostafa
 */
public class freeTime {

    public node dayStartEnd[];

    public freeTime() {
        dayStartEnd = new node[6];
        for (int i = 0; i < 6; i++) {
            dayStartEnd[i] = new node();
        }
    }

    public void addFreeTime(int day, int start, int end) {
        //cheak if the no free time in the day was enterd it will
        //  set the free time in this day the free time was passed 

        node n = dayStartEnd[day];
        if (n.startSession == null) {
            n.startSession = start;
            n.endSession = (end);
            return;
            //if there is a free time and the last start is greater than of equal 
            //  the start time was enterd and the new start time is greater than or equal
            //  the last end time 
        } else if (start >= n.startSession && start <= n.endSession) {
            // if the last condition was true and the new end is lessthan
            //  or equal the new end it will do nothing as the new time is free before 
            if (end <= n.endSession) {
                return;
            } else {
                //else the last end time is greater then the new time it will update the new end time
                n.endSession = end;
                return;
            }

        } else if (start <= n.startSession && end >= n.startSession) {
            if (end > n.endSession) {
                n.startSession = start;
                n.endSession = end;
                return;
            } else {
                n.startSession = start;
                return;
            }
        }
        while (n.next != null) {
            if (start > n.startSession && start <= n.endSession) {
                if (end < n.endSession) {
                    return;
                } else {
                    n.endSession = end;
                    return;
                }
            } else if (start < n.startSession && end >= n.startSession) {
                if (end > n.endSession) {
                    n.startSession = start;
                    n.endSession = end;
                    return;
                } else {
                    n.startSession = start;
                    return;
                }
            }
            n = n.next;
        }
        n.next = new node();
        n.next.startSession = (start);
        n.next.endSession = (end);
        n.next.privous = n;
    }

    public void removeAnHour(int day, int x) {
        node n = dayStartEnd[day];
        if (n.startSession == x) {
            n.startSession++;
        } else if (n.endSession - 1 == x) {
            n.endSession--;
        } else if (n.startSession < x && n.endSession > x) {
            int value = n.endSession;
            n.endSession = x;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new node();
            n.next.startSession = x + 1;
            n.next.endSession = value;
        } else {
            while (n.next != null) {
                n = n.next;
                if (n.startSession == x) {
                    n.startSession++;
                    break;
                } else if (n.endSession - 1 == x) {
                    n.endSession--;
                    break;
                } else if (n.startSession < x && n.endSession > x) {
                    int value = n.endSession;
                    n.endSession = x;
                    while (n.next != null) {
                        n = n.next;
                    }
                    n.next = new node();
                    n.next.startSession = x + 1;
                    n.next.endSession = value;
                    break;
                }
            }
        }

    }

}
