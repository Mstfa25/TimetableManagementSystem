import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Semsterit } from 'src/app/core/interfaces/branch';
import { AuthService } from 'src/app/core/services/auth.service';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormsemsterComponent } from 'src/app/forms/formsemster/formsemster.component';

@Component({
  selector: 'app-semsterit',
  templateUrl: './semsterit.component.html',
  styleUrls: ['./semsterit.component.scss']
})
export class SemsteritComponent {
  displayedColumns: string[] = ['snumber', 'studyname', 'action'];
  dataSource!: MatTableDataSource<Semsterit>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog: MatDialog, private _semservice: FacultyService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    this.getSemster();
  }
  openAddEditSemster() {
    const dialogRef = this._dialog.open(FormsemsterComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getSemster();
        }
      }
    })
  }
  getSemster() {
    this._semservice.getSemsteritList().subscribe({
      next: (res) => {
        console.log(res);
        const displayedData = res.map((data: {
          id: number;
          number: number;
          studyPlan: { name: any; id: number; faculty: { name: any; id: number; } };
        }) => ({
          sId: data.id,
          snumber: data.number,
          studyname: data.studyPlan.name,
          ssId: data.studyPlan.id
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
  deleteSemster(id: number) {
    const data={id:id}
    this._semservice.deleteSemsterit(data).subscribe({
      next: (res) => {
        alert('Deleted !')
        this.getSemster();
      },
      error: console.log
    })
  }
  openEditSemster(data: any) {
    const dialogRef = this._dialog.open(FormsemsterComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getSemster();
        }
      }
    })

  }
}
