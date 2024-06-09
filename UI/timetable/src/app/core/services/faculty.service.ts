import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  constructor(@Inject(PLATFORM_ID) private platformId: Object,
    private http: HttpClient,
  ) {

  }

  addFaculty(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addFaculty';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  updateFaculty(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/editFacultyName';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  getFacultyList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getFacultys';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.get(apiUrl, httpOptions);
  }

  deleteFaculty(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/deleteFaculty';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  addStudyit(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addStudyPlan';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  updateStudyit(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/editStudyPlan';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  getStudyitList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getAllStudyPlans';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.get(apiUrl, httpOptions);
  }

  deleteStudyit(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/removeStudyPlan';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  addSemsterit(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addSemester';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  updateSemsterit(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/editSemester';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }

  getSemsteritList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getAllSemesters';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.get(apiUrl, httpOptions);
  }

  deleteSemsterit(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/removeSemester';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }
  //sections-------------------------------------------------------------
  addsec(data: any): Observable<any> {
    return this.http.post('http://localhost:3000/section', data);
  }

  updatesec(id: number, data: any): Observable<any> {
    return this.http.put(`http://localhost:3000/section/${id}`, data);
  }

  getseceList(): Observable<any> {
    return this.http.get('http://localhost:3000/section');
  }

  deletesec(id: number): Observable<any> {
    return this.http.delete(`http://localhost:3000/section/${id}`);
  }
  ////secgrop
  addsecgrop(data: any): Observable<any> {
    return this.http.post('http://localhost:3000/sec_group', data);
  }

  updatsecgrop(id: number, data: any): Observable<any> {
    return this.http.put(`http://localhost:3000/sec_group/${id}`, data);
  }

  getsecgropList(): Observable<any> {
    return this.http.get('http://localhost:3000/sec_group');
  }

  deletesecgrop(id: number): Observable<any> {
    return this.http.delete(`http://localhost:3000/sec_group/${id}`);
  }
  //courses-------------------------------------------------------------------
  addcourse(data: any): Observable<any> {
    return this.http.post('http://localhost:3000/course', data);
  }

  updatecourse(id: number, data: any): Observable<any> {
    return this.http.put(`http://localhost:3000/course/${id}`, data);
  }

  getcourseList(): Observable<any> {
    return this.http.get('http://localhost:3000/course');
  }

  deletecourse(id: number): Observable<any> {
    return this.http.delete(`http://localhost:3000/course/${id}`);
  }
}
