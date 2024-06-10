import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ViewChild,Inject,PLATFORM_ID } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormsecGroupComponent } from 'src/app/forms/formsecstaf/formsec-group/formsec-group.component';

@Component({
  selector: 'app-sectiongroup',
  templateUrl: './sectiongroup.component.html',
  styleUrls: ['./sectiongroup.component.scss']
})
export class SectiongroupComponent {
  displayedColumns: string[] = [
    'secname','branch','capacty','action'];
    dataSource!: MatTableDataSource<any>;
  
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;
  constructor(@Inject(PLATFORM_ID) private platformId: Object,
  private http: HttpClient,private router: Router,private _dialog:MatDialog, private _secgrop: FacultyService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }


  cheeck(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Make the request to the backend server
      const apiUrl = 'http://localhost:7081/api/home';

      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      });

      // Make the HTTP request with the updated headers
      this.http.get<any>(apiUrl, { headers, withCredentials: true }).subscribe(
        (response) => {
          if (response[0] === 'home') {
          } else {
            this.router.navigate(['/login']);
            // You can use Angular Router to navigate to the login page
          }
        },
        (error) => {
        }
      );
    }
  }
  ngOnInit(): void {
      this.getsecgrop();
  
  }
  openAddEditsecgroup(){
    const dialogRef= this._dialog.open(FormsecGroupComponent );
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getsecgrop();
        }
      }
    })
  }
  getsecgrop(){
    this._secgrop.getsecgropList().subscribe({
      next: (res) =>{
     this.dataSource = new MatTableDataSource(res);
     this.dataSource.sort = this.sort;
     this.dataSource.paginator = this.paginator;
      },
      error: console.log
    })
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  deletesecgrp(id:number){
  this._secgrop.deletesecgrop(id).subscribe({
    next: (res) => {
     alert(' Deleted !')
     this.getsecgrop();
    },
    error: console.log
  })
  }
  openEditsecgrop(data: any){
  const dialogRef = this._dialog.open(FormsecGroupComponent  , {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getsecgrop();
        }
      }
    })
   
  }
}
