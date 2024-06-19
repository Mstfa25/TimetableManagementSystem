
import { Component, Inject, OnInit, PLATFORM_ID, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { BranchModel } from './branch.model';
import { BranchService } from 'src/app/core/services/branch.service';
import { Branch } from 'src/app/core/interfaces/branch';
import { MatDialog } from '@angular/material/dialog';
import { FormbranchComponent } from 'src/app/forms/formbranch/formbranch.component';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.scss']
})
export class BranchComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'action'];
  dataSource!: MatTableDataSource<Branch>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
constructor(
  @Inject(PLATFORM_ID) private platformId: Object,
  private http: HttpClient,
  private router: Router,
  private _dialog: MatDialog,
   private _brnService:BranchService,
   private auth:AuthService)
{
  auth.loggedIn.next(true);
}
openAddEditBranch(){
 const dialogRef = this._dialog.open(FormbranchComponent);
 dialogRef.afterClosed().subscribe({
  next: (val ) =>{
    if(val){
      this.getBranch();
    }
  }
 })
}

cheeck():void{
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
   this.getBranch();
   
}
getBranch(){
  this._brnService.getBranchList().subscribe({
      next: (res) => {
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log
    });
  }
  

applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}

deleteBranch(id: number) {
  Swal.fire({
    title: 'Are you sure you want to delete this branch?',
    text: "This action cannot be undone!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      const body = { id }; // Prepare data for deletion

      // Handle deletion with proper error handling
      this._brnService.removeBranch(body).subscribe({
        next: (res) => {
          Swal.fire('Deleted!', 'Branch has been deleted successfully.', 'success');
          this.getBranch(); // Refresh branch data (assuming this refreshes data)
        },
        error: (error) => {
          console.error('Error deleting branch:', error);
          Swal.fire('Error!', 'An error occurred while deleting the branch.', 'error');
        }
      });
    }
  });
}
  
openEditBranch(data: any){
 const dialogRef = this._dialog.open(FormbranchComponent,{
    data,
  });
  dialogRef.afterClosed().subscribe({
    next: (val ) =>{
      if(val){
        this.getBranch();
      }
    }
   })
 }
}
