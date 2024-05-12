import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TablesService {

  constructor(private _http: HttpClient) { }
  addtablelec(data: any): Observable<any> {
    return this._http.post('http://localhost:3000/tablelec', data);
  }

  updatetablelec(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:3000/tablelec/${id}`, data);
  }

  gettablelecList(): Observable<any> {
    return this._http.get('http://localhost:3000/tablelec');
  }

  deletetablelec(id: number): Observable<any> {
    return this._http.delete(`http://localhost:3000/tablelec/${id}`);
  }
  //         table section
  addtablesec(data: any): Observable<any> {
    return this._http.post('http://localhost:3000/tablesec', data);
  }

  updatetablesec(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:3000/tablesec/${id}`, data);
  }

  gettablesecList(): Observable<any> {
    return this._http.get('http://localhost:3000/tablesec');
  }
  
  deletetablesec(id: number): Observable<any> {
    return this._http.delete(`http://localhost:3000/tablesec/${id}`);
  }
}
