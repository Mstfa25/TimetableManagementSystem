import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';


@Component({
  selector: 'app-tablesec',
  templateUrl: './tablesec.component.html',
  styleUrls: ['./tablesec.component.scss']
})
export class TablesecComponent {
  useFrom: FormGroup;
  listdata: any;
  course: any[] = [];
  branch: any[] = []
  table: any[] = []
  constructor(private fb: FormBuilder, private auth: AuthService, @Inject(PLATFORM_ID) private platformId: Object,
  private http: HttpClient,
  private router: Router) {
    auth.loggedIn.next(true);
    this.listdata = []
    this.useFrom = this.fb.group({
      branch: ['', Validators.required],
      course: ['', Validators.required],
      table: ['', Validators.required],

    })
  }

  ngOnInit(){
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
      (this.http.get('http://localhost:7081/api/admin/getTimetableNames', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.table = data;
      });
      (this.http.get('http://localhost:7081/api/admin/getAllBranches', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.branch = data;
      });
  }

  public additem(): void {
    this.listdata.push(this.useFrom.value)
    this.useFrom.reset();
  }

  resetitem() {
    this.useFrom.reset();
  }

  removeitem(element: any) {
    this.listdata.forEach((value: any, index: any) => {
      if (value == element)
        this.listdata.splice(index, 1)

    })
  }
}
