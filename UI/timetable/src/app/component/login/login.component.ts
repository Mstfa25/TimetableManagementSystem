import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss','../nav/style.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });
  constructor(
    private _AuthService: AuthService,
    private fb: FormBuilder,
    private _Router: Router,
    @Inject(PLATFORM_ID) private platformId: Object,
     private http: HttpClient
  ) {
    _AuthService.loggedIn.next(false);
  }
  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      const apiUrl = 'http://localhost:7081/api/home';
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      });
      this.http.get<any>(apiUrl, { headers, withCredentials: true }).subscribe(
        (response) => {
          if (response[0] === 'home') {
            this._Router.navigate(['/home']);
          } else if (response[0] === 'SubAdminHome') {
            this._Router.navigate(['/home']);
          } else {
          }
        },
        (error) => {
        }
      );
    }
  }

  login() {
    this._AuthService.login1(
      this.form.value.username,
      this.form.value.password
    );
    
  }
}
