import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';

import { FormlecgroupComponent } from 'src/app/forms/formlecgroup/formlecgroup.component';
import { FormsecgroupComponent } from 'src/app/forms/formsecgroup/formsecgroup.component';
import Swal from 'sweetalert2';

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
        this._lecService.deletelecgroup(id).subscribe({
          next: (res) => {
            Swal.fire('Deleted!', ' deleted successfully.', 'success');
            this.getlec(); // Assuming this refreshes the lecture list
          },
          error: (err: any) => {
            console.error('Error deleting lecture record:', err);
            Swal.fire('Error!', 'An error occurred while deleting the lecture record.', 'error');
          }
        });
      }
    });
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
