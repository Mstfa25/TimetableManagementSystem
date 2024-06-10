
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lectime } from '../interfaces/lectime';

@Injectable({
  providedIn: 'root'
})
export class TablesService {

  constructor(private _http: HttpClient) { }
  urlApi='http://localhost:3000/tablelec'
  getAll(){
    return this._http.get<Lectime[]>(this.urlApi);
  }

  // DELETE one
  delete(id: number){
    return this._http.delete(`${this.urlApi}/${id}`);
  }

  // CREATE one
  post(br: Lectime){
      return this._http.post<Lectime>(this.urlApi, br);
  }

  // UPDATE one
  updateRecipe(br: Lectime){
    return this._http.put(`${this.urlApi}/${br.id}`, br);
  }

  // search by id
  search(id: number){
      return this._http.get<Lectime>(`${this.urlApi}/${id}`); //${id}
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
