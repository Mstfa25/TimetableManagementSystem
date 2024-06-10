import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Lectime } from 'src/app/core/interfaces/lectime';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';


@Component({
  selector: 'app-tablelec',
  templateUrl: './tablelec.component.html',
  styleUrls: ['./tablelec.component.scss']
})
export class TablelecComponent {
  userFrom: FormGroup;
  listdata: any;
  course: any[] = [];
  semster: any[] = ["شسي"]
  constructor(private fb: FormBuilder, private auth: AuthService, @Inject(PLATFORM_ID) private platformId: Object,
    private http: HttpClient,
    private router: Router) {
    auth.loggedIn.next(true);
    this.listdata = []
    this.userFrom = this.fb.group({
      name: ['', Validators.required],
      course: ['', Validators.required],
      semster: [''],
    })
  }

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      const apiUrl = 'http://localhost:7081/api/home';

      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      });
      this.http.get<any>(apiUrl, { headers, withCredentials: true }).subscribe(
        (response) => {
          if (response[0] === 'home') {
          } else {
            this.router.navigate(['/login']);
          }
        },
        () => {
        }
      );
    }
    (this.http.get('http://localhost:7081/api/admin/getCourseNames', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.course = data;
      });
  }

  public additem(): void {
    this.listdata.push(this.userFrom.value)
    this.userFrom.reset();

  }

  resetitem() {
    this.userFrom.reset();
  }

  removeitem(element: any) {
    this.listdata.forEach((value: any, index: any) => {
      if (value == element)
        this.listdata.splice(index, 1)

    })
  }
}
