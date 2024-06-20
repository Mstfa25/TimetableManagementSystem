import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { faculty } from 'src/app/core/interfaces/branch';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormfacultyComponent } from 'src/app/forms/formfaculty/formfaculty.component';
import { AuthService } from 'src/app/core/services/auth.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.scss']
})
export class FacultyComponent {
  displayedColumns: string[] = ['id', 'name', 'action'];
  dataSource!: MatTableDataSource<faculty>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog: MatDialog, private _facultyService: FacultyService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  openAddEditFaculty() {
    const dialogRef = this._dialog.open(FormfacultyComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getFaculty();
        }
      }
    });
  }
  ngOnInit(): void {
    this.getFaculty();

  }
  getFaculty() {
    this._facultyService.getFacultyList().subscribe({
      next: (res) => {
        console.log(res);
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
  deleteFaculty(id: number) {
    Swal.fire({
      title: 'Are you sure you want to delete this faculty?',
      text: "This action cannot be undone.", // Emphasize irreversible nature
      icon: 'warning', // Warning icon
      showCancelButton: true,
      confirmButtonColor: '#d33', // Red for delete
      cancelButtonColor: '#1475CB', // Blue for cancel
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        const data = { id }; // Destructuring assignment for brevity
        this._facultyService.deleteFaculty(data).subscribe({
          next: (res) => {
            Swal.fire('Deleted!', 'Faculty has been deleted successfully.', 'success');
            this.getFaculty(); // Assuming this refreshes the faculty list
          },
          error: (err: any) => {
            console.error('Error deleting faculty:', err);
            Swal.fire('Error!', 'An error occurred while deleting the faculty.', 'error');
          }
        });
      }
    });
  }
  openEditFaculty(data: any) {
    const dialogRef = this._dialog.open(FormfacultyComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getFaculty();
        }
      }
    });
  }
}
