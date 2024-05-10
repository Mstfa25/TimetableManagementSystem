import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import { FormgrouplecComponent } from 'src/app/forms/formgrouplec/formgrouplec.component';
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
  //------------------groplec
  displayedColumns1: string[] = ['lecname', 'nameg', 'action'];
  dataSource1!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator1!: MatPaginator;
  @ViewChild(MatSort) sort1!: MatSort;
  ///secgroup
  displayedColumns2: string[] = ['namesec', 'lecname', 'grouplecname', 'groupbranname', 'action'];
  dataSource2!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator2!: MatPaginator;
  @ViewChild(MatSort) sort2!: MatSort;
  constructor(private _dialog: MatDialog, private _lecService: LecgroupService, private auth: AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    this.getlec();
    this.getgrlec();

    this.getsec();
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
  //---------------------------------------grouplec-------------------

  openAddEditgrlec() {
    const dialogRef = this._dialog.open(FormgrouplecComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getgrlec();
        }
      }
    })
  }
  getgrlec() {
    this._lecService.getgrouplecList().subscribe({
      next: (res) => {
        console.log(res);
        const displayedData = res.map((data: {
          id: number;
          name: any;
          lectuerGoup: { name: any; id: number };
        }) => ({
          id: data.id,
          lecname: data.name,
          nameg: data.lectuerGoup.name,
          lecid: data.lectuerGoup.id,
          // 'action': This value is not present in the server response
        }));
        this.dataSource1 = new MatTableDataSource(displayedData);
        this.dataSource1.sort = this.sort;
        this.dataSource1.paginator = this.paginator;
      },
      error: console.log
    })
  }

  deletegrlec(id: number) {
    this._lecService.deletegrouplec(id).subscribe({
      next: (res) => {
        alert(' Deleted !')
        this.getgrlec();
      },
      error: console.log
    })
  }
  openEditgrlec(data: any) {
    const dialogRef = this._dialog.open(FormlecgroupComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getgrlec();
        }
      }
    })

  }
  //---------------------secgroup//////////////
  openAddEditsec() {
    const dialogRef = this._dialog.open(FormsecgroupComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getsec();
        }
      }
    })
  }

  //[ 'namesec','lecname','grouplecname','groupbranname','action'];
  getsec() {
    this._lecService.getsecgroupList().subscribe({
      next: (res) => {
        const displayedData = res.map((data: {
          branch: { name: any; id: number };
          lecGroup: {
            id: number;
            name: any;
            lectuerGoup: { name: any; id: number };
          }
        }) => ({
          namesec: data.lecGroup.lectuerGoup.id,
          lecname: data.lecGroup.lectuerGoup.name,
          grouplecname: data.lecGroup.name,
          groupbranname: data.branch.name,
          // 'action': This value is not present in the server response
        }));
        this.dataSource2 = new MatTableDataSource(displayedData);
        this.dataSource2.sort = this.sort;
        this.dataSource2.paginator = this.paginator;
      },
      error: console.log
    })
  }

  deletesec(id: number) {
    this._lecService.deletesecgroup(id).subscribe({
      next: (res) => {
        alert(' Deleted !')
        this.getsec();
      },
      error: console.log
    })
  }
  openEditsec(data: any) {
    const dialogRef = this._dialog.open(FormsecgroupComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getsec();
        }
      }
    })

  }
}
