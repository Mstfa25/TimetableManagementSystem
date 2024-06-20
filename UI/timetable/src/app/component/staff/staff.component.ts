import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Staff } from 'src/app/core/interfaces/branch';
import { AuthService } from 'src/app/core/services/auth.service';
import { BranchService } from 'src/app/core/services/branch.service';
import { FormstaffComponent } from 'src/app/forms/formstaff/formstaff.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss']
})
export class StaffComponent {
  displayedColumns: string[] = ['jopname', 'bname', 'tjop', 'action'];
  dataSource!: MatTableDataSource<Staff>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private http: HttpClient,
    private router: Router,
    private _dialog: MatDialog,
    private _staffService: BranchService,
    auth:AuthService
  ) { 
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
          }
        },
        (error) => {
        }
      );
    }
  }

  ngOnInit(): void {
    this.cheeck();
    this.getStaff();
  }
  openAddEditStaff() {
    const dialogRef = this._dialog.open(FormstaffComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getStaff();
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
  getStaff() {
    this._staffService.getStaffList().subscribe({
      next: (res) => {
        const displayedData = res.map((data: {id:number;
          name: any;
          type: { name: any; id:number };
          branch: { name: any; id:number}; }) => ({
         id: data.id,
         jopname: data.name,
         tjop: data.type.name,
         bname: data.branch.name
         // 'action': This value is not present in the server response
       }));
        this.dataSource = new MatTableDataSource(displayedData);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log
    });
  }

  deleteStaff(id: number) {
    Swal.fire({
      title: 'Are you sure you want to delete this staff member?',
      text: "This action cannot be undone.", // Emphasize irreversible nature
      icon: 'warning', // Warning icon
      showCancelButton: true,
      confirmButtonColor: '#d33', // Red for delete
      cancelButtonColor: '#1475CB', // Blue for cancel
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        const body = { id }; // Destructuring assignment for concise body creation
        this._staffService.deleteStaff(body).subscribe({
          next: (res) => {
            Swal.fire('Deleted!', 'Staff member has been deleted successfully.', 'success');
            this.getStaff(); // Assuming this refreshes the staff list
          },
          error: (err: any) => {
            console.error('Error deleting staff member:', err);
            Swal.fire('Error!', 'An error occurred while deleting the staff member.', 'error');
          }
        });
      }
    });
  }

  openEditStaff(data: any) {
    const dialogRef = this._dialog.open(FormstaffComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getStaff();
        }
      }
    })

  }
}
