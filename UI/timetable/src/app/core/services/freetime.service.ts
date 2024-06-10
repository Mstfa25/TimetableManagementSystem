import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FreetimeService {

  constructor(private _http: HttpClient) { }

  private httpOptions = { 
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
    withCredentials: true 
  };

  //---------------------------------------------timestaff
  addtimestaff(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addFreeTimeForStaff';
    return this._http.post(apiUrl, data, this.httpOptions);
  }

  updatetimestaff(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/';
    return this._http.post(apiUrl, data, this.httpOptions);
  }

  gettimestaffList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getFreeTimeForStaff';
    return this._http.post(apiUrl, {}, this.httpOptions);
  }

  deletetimestaff(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/';
    return this._http.post(apiUrl, data, this.httpOptions);
  }

  //---------------------------------------------timeroom
  addtimeroom(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/';
    return this._http.post(apiUrl, data, this.httpOptions);
  }

  updatetimeroom(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/';
    return this._http.post(apiUrl, data, this.httpOptions);
  }

  gettimeroomList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getFreetimeForRooms';
    return this._http.post(apiUrl, {}, this.httpOptions);
  }

  deletetimeroom(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/';
    return this._http.post(apiUrl, data, this.httpOptions);
  }

}
