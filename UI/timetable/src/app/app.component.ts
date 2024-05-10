import { Component } from '@angular/core';
import { AuthService } from './core/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'timetable';
  sideBarOpen = true;
  loggedIn =false
  constructor(_AuthService: AuthService) {
    _AuthService.loggedIn.subscribe(() => {
      this.loggedIn = _AuthService.loggedIn.value
    });
  }
}
