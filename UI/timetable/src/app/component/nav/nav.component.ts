import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {

  constructor(
    private _AuthService: AuthService,
    private _Router: Router,
    @Inject(PLATFORM_ID) private platformId: Object,
     private http: HttpClient
  ){

  }

  logout():void{
    this._AuthService.loggedIn.next(false);
    if (isPlatformBrowser(this.platformId)) {
      const apiUrl = 'http://localhost:7081/api/logout';
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      });
      this.http.get<any>(apiUrl, { headers, withCredentials: true }).subscribe(
        (response) => {
            this._Router.navigate(['/login']);
        },
        (error) => {
        }
      );
    }
  }

}
