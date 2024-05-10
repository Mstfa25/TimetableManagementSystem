import { ThemePalette } from "@angular/material/core";

export interface Branch {
    id: number ;
    name: string;
}
export interface Room {
    id: number ;
    name: string;
    capacity: number;
    bname: string;
    troom: string
}
export interface Staff {
    id: number ;
    name: string;
    bname: string;
    tjop: string
}
export interface faculty{
    id: number ;
    
    name: string;
}
export interface Studyit {
    id: number ;
    sname: string;
    fname: string;
   
}
export interface Semsterit {
    id: number ;
    snumber: number;
    studyname: string;
   
}

