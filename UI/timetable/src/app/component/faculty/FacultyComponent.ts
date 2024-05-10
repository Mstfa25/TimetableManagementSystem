import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { faculty } from 'src/app/core/interfaces/branch';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormfacultyComponent } from 'src/app/forms/formfaculty/formfaculty.component';
import { AuthService } from 'src/app/core/services/auth.service';


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
    const data = { id: id };
    this._facultyService.deleteFaculty(data).subscribe({
      next: (res) => {
        alert('Faculty deleted !');
        this.getFaculty();
      },
      error: console.log
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
