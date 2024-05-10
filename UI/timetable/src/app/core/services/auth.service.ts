import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn  = new BehaviorSubject(false);
  session: any;
  constructor(
    private _Router: Router
  ) {
    
  }

  async login1(username: string, password: string):Promise<boolean> {

    const url = "http://localhost:7081/api/login"; // Corrected the typo in "localhost"
    const data = { username, password };
    const httpOptions: RequestInit = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'withCredentials': 'true'
      },
      body: JSON.stringify(data),
      credentials: 'include'
    };

    try {
      const response = await fetch(url, httpOptions);
      if (response.ok) {
        this.loggedIn.next(true);
        this._Router.navigateByUrl('/home');
        return true;
      }else{
        alert('invalid Data');
      }
    } catch (error) {

    }
    return false;
  }

}
