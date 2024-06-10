import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { BranchService } from 'src/app/core/services/branch.service';
import { FormcourstaffComponent } from 'src/app/forms/formcourstaff/formcourstaff.component';
import { FormsecstafComponent } from 'src/app/forms/formsecstaf/formsecstaf.component';

@Component({
  selector: 'app-coursestaff',
  templateUrl: './coursestaff.component.html',
  styleUrls: ['./coursestaff.component.scss']
})
export class CoursestaffComponent {
  displayedColumns: string[] = [ 'course','branch','staff'
  ,'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  


constructor(@Inject(PLATFORM_ID) private platformId: Object,
private http: HttpClient,private router: Router,private _dialog:MatDialog, private _corStaService: BranchService, private auth:AuthService) {
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
  this.cheeck();
    this.getCostaf();

}
openAddEditCorstaf(){
  const dialogRef= this._dialog.open(FormcourstaffComponent );
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getCostaf();
      }
    }
  })
}
getCostaf(){
  this._corStaService.getcourStaffList().subscribe({
    next: (res) =>{
      console.log(res);
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
deleteCostaf(id:number){
this._corStaService.deletecourStaff(id).subscribe({
  next: (res) => {
   alert(' Deleted !')
   this.getCostaf();
  },
  error: console.log
})
}
openEditcostaf(data: any){
const dialogRef = this._dialog.open(FormcourstaffComponent  , {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getCostaf();
      }
    }
  })
 
}

}
