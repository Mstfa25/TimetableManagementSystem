import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';

import { FormlecgroupComponent } from 'src/app/forms/formlecgroup/formlecgroup.component';
import { FormsecgroupComponent } from 'src/app/forms/formsecgroup/formsecgroup.component';

@Component({
  selector: 'app-lecgroup',
  templateUrl: './lecgroup.component.html',
  styleUrls: ['./lecgroup.component.scss']
})
export class LecgroupComponent {
  displayedColumns: string[] = ['name', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private _dialog: MatDialog, private _lecService: LecgroupService, private auth: AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    this.getlec();
 

 
  }
  openAddEditlec() {
    const dialogRef = this._dialog.open(FormlecgroupComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getlec();
        }
      }
    })
  }
  getlec() {
    this._lecService.getlecgroupList().subscribe({
      next: (res) => {
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
  deletelec(id: number) {
    this._lecService.deletelecgroup(id).subscribe({
      next: (res) => {
        alert(' Deleted !')
        this.getlec();
      },
      error: console.log
    })
  }
  openEditlec(data: any) {
    const dialogRef = this._dialog.open(FormlecgroupComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getlec();
        }
      }
    })

  }
 
}
