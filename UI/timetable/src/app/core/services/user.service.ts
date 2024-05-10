import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  addUser(data: any): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/addUser';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }
  
  updateUser(data: any): Observable<any> {
    const apiUrl = `http://localhost:7081/api/admin/editUser`;
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }
  
  getUserList(): Observable<any> {
    const apiUrl = 'http://localhost:7081/api/admin/getAllUsers';
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.get(apiUrl, httpOptions);
  }
  
  deleteUser(data:any): Observable<any> {
    const apiUrl = `http://localhost:7081/api/admin/deleteUser`;
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    };
    return this.http.post(apiUrl, data, httpOptions);
  }
  

}
