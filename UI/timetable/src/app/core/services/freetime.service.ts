import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FreetimeService {


  constructor(private _http: HttpClient) { }
  addtimestaff(data: any): Observable<any> {
    return this._http.post('http://localhost:3000/timestaf', data);
  }

  updatetimestaff(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:3000/timestaf/${id}`, data);
  }

  gettimestaffList(): Observable<any> {
    return this._http.get('http://localhost:3000/timestaf');
  }

  deletetimestaff(id: number): Observable<any> {
    return this._http.delete(`http://localhost:3000/timestaf/${id}`);
  }
  //---------------------------------------------freetimeroom
  addtimeroom(data: any): Observable<any> {
    return this._http.post('http://localhost:3000/timeroom', data);
  }

  updatetimeroom(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:3000/timeroom/${id}`, data);
  }

  gettimeroomList(): Observable<any> {
    return this._http.get('http://localhost:3000/timeroom');
  }

  deletetimeroom(id: number): Observable<any> {
    return this._http.delete(`http://localhost:3000/timeroom/${id}`);
  }
}
