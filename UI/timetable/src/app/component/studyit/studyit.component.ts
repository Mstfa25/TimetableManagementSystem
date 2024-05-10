import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Studyit } from 'src/app/core/interfaces/branch';
import { AuthService } from 'src/app/core/services/auth.service';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormstudyitComponent } from 'src/app/forms/formstudyit/formstudyit.component';

@Component({
  selector: 'app-studyit',
  templateUrl: './studyit.component.html',
  styleUrls: ['./studyit.component.scss']
})
export class StudyitComponent {
  displayedColumns: string[] = ['sname', 'fname', 'action'];
  dataSource!: MatTableDataSource<Studyit>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog: MatDialog, private _studyervice: FacultyService , private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    this.getStudy();
  }
  openAddEditStudy() {
    const dialogRef = this._dialog.open(FormstudyitComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getStudy();
        }
      }
    })
  }
  getStudy() {
    this._studyervice.getStudyitList().subscribe({
      next: (res) => {
        console.log(res);
        const displayedData = res.map((data: {
          id: number;
          name: any;
          faculty: { id: number, name: any }
        }) => ({
          sname: data.name,
          fname: data.faculty.name,
          sId:data.id,
          fId:data.faculty.id
          // 'action': This value is not present in the server response
        }));
        this.dataSource = new MatTableDataSource(displayedData);
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
  deleteStudy(id: number) {
    const data = { id: id }
    this._studyervice.deleteStudyit(data).subscribe({
      next: (res) => {
        console.log(res);
        alert('Deleted !')
        this.getStudy();
      },
      error: console.log
    })
  }
  openEditStudy(data: any) {
    const dialogRef = this._dialog.open(FormstudyitComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getStudy();
        }
      }
    })

  }
}
