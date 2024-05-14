import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Branch } from '../interfaces/branch';

@Injectable({
  providedIn: 'root'
})
export class BranchService {
   
  constructor(private _http: HttpClient) {}

  getBranchList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getAllBranches';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.get<any>(apiUrl, httpOptions);
  }
  
  removeBranch(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/removeBranch';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  addBranch(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addBranch';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  updateBranch(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/editBranchName';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  addRoom(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addRoom';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  updateRoom(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/updateRoom';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  getRoomList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getAllRoomsInAllBranches';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.get(apiUrl, httpOptions);
  }
  
  deleteRoom(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/DeleteRoom';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  addStaff(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addStaff';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  updateStaff(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/updateStaff';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  
  getStaffList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getAllStaff';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.get(apiUrl, httpOptions);
  }
  
  deleteStaff(body: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/removeStaff';
    const httpOptions = { 
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
      withCredentials: true 
    };
    return this._http.post(apiUrl, body, httpOptions);
  }
  ///courseSttaff------------------------------------------------------------
  addcourStaff(data: any): Observable<any> {
    return this._http.post('http://localhost:3000/coursestaaf', data);
  }

  updatecourStaff(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:3000/coursestaaf/${id}`, data);
  }

  getcourStaffList(): Observable<any> {
    return this._http.get('http://localhost:3000/coursestaaf');
  }

  deletecourStaff(id: number): Observable<any> {
    return this._http.delete(`http://localhost:3000/coursestaaf/${id}`);
  }
  //SEctionStaff-------------------------------------------------------------------------------------
  addSecStaff(data: any): Observable<any> {
    return this._http.post('http://localhost:3000/secstaaf', data);
  }

  updateSecStaff(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:3000/secstaaf/${id}`, data);
  }

  getSecStaffList(): Observable<any> {
    return this._http.get('http://localhost:3000/secstaaf');
  }

  deleteSecStaff(id: number): Observable<any> {
    return this._http.delete(`http://localhost:3000/secstaaf/${id}`);
  }
  
}
