import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LecgroupService {

  constructor(private http: HttpClient) {}

addlecgroup(data: any): Observable<any> {
  const apiUrl = 'http://localhost:7081/api/admin/lecgroup';
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, data, httpOptions);
}

updatelecgroup(id: number, data: any): Observable<any> {
  const apiUrl = `http://localhost:7081/api/admin/lecgroup/${id}`;
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, data, httpOptions);
}

getlecgroupList(): Observable<any> {
  const apiUrl = 'http://localhost:7081/api/admin/getAllLectuerGroups';
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.get(apiUrl, httpOptions);
}

deletelecgroup(id: number): Observable<any> {
  const apiUrl = `http://localhost:7081/api/admin/lecgroup/${id}`;
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, {}, httpOptions);
}

addgrouplec(data: any): Observable<any> {
  const apiUrl = 'http://localhost:7081/api/admin/grouplec';
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, data, httpOptions);
}

updategrouplec(id: number, data: any): Observable<any> {
  const apiUrl = `http://localhost:7081/api/admin/grouplec/${id}`;
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, data, httpOptions);
}

getgrouplecList(): Observable<any> {
  const apiUrl = 'http://localhost:7081/api/admin/getAllLecGroups';
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.get(apiUrl, httpOptions);
}

deletegrouplec(id: number): Observable<any> {
  const apiUrl = `http://localhost:7081/api/admin/grouplec/${id}`;
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, {}, httpOptions);
}

addsecgroup(data: any): Observable<any> {
  const apiUrl = 'http://localhost:7081/api/admin/secgroup';
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, data, httpOptions);
}

updatesecgroup(id: number, data: any): Observable<any> {
  const apiUrl = `http://localhost:7081/api/admin/secgroup/${id}`;
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, data, httpOptions);
}

getsecgroupList(): Observable<any> {
  const apiUrl = 'http://localhost:7081/api/admin/getAllLecGroupBranches';
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.get(apiUrl, httpOptions);
}

deletesecgroup(id: number): Observable<any> {
  const apiUrl = `http://localhost:7081/api/admin/secgroup/${id}`;
  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };
  return this.http.post(apiUrl, {}, httpOptions);
}

}