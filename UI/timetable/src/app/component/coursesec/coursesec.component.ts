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
import { FormsecstafComponent } from 'src/app/forms/formsecstaf/formsecstaf.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-coursesec',
  templateUrl: './coursesec.component.html',
  styleUrls: ['./coursesec.component.scss']
})
export class CoursesecComponent {
  displayedColumns: string[] = ['course','branch','staff','section', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

constructor(@Inject(PLATFORM_ID) private platformId: Object,
private http: HttpClient,private router: Router, private _dialog:MatDialog, private _corStaService: BranchService, private auth:AuthService) {
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
    this.getSecStaf();

}
openAddEditSecStaf() {
  const dialogRef = this._dialog.open(FormsecstafComponent);
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if (val) {
        this.getSecStaf();
      }
    }
  })
}
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}
getSecStaf(){
  this._corStaService.getSecStaffList().subscribe({
    next: (res) =>{
      console.log(res);
   this.dataSource = new MatTableDataSource(res);
   this.dataSource.sort = this.sort;
   this.dataSource.paginator = this.paginator;
    },
    error: console.log
  })
}
deleteSecstaf(id: number) {
  Swal.fire({
    title: 'Are you sure you want to delete ?',
    text: "This action cannot be undone.", // Emphasize irreversible nature
    icon: 'warning', // Warning icon
    showCancelButton: true,
    confirmButtonColor: '#d33', // Red for delete
    cancelButtonColor: '#1475CB', // Blue for cancel
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      this._corStaService.deleteSecStaff(id).subscribe({
        next: (res) => {
          Swal.fire('Deleted!', ' has been deleted successfully.', 'success');
          this.getSecStaf(); // Assuming this refreshes the staff list
        },
        error: (err: any) => {
          console.error('Error deleting staff member:', err);
          Swal.fire('Error!', 'An error occurred while deleting the staff member.', 'error');
        }
      });
    }
  });
  }
  openEditSecStaf(data: any){
  const dialogRef = this._dialog.open(FormsecstafComponent  , {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getSecStaf();
        }
      }
    })
   
  }
}
