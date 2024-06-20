import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import { FormsecgroupComponent } from 'src/app/forms/formsecgroup/formsecgroup.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-secgroup',
  templateUrl: './secgroup.component.html',
  styleUrls: ['./secgroup.component.scss']
})
export class SecgroupComponent {
  displayedColumns: string[] = [ 'namesec','lecname','grouplecname','groupbranname','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
constructor(private _dialog:MatDialog, private _lecService: LecgroupService, private auth:AuthService) {
  auth.loggedIn.next(true);
}
ngOnInit(): void {
  
    
    this.getsec();
}
  openAddEditsec(){
    const dialogRef= this._dialog.open(FormsecgroupComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getsec();
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
  getsec(){
    this._lecService.getsecgroupList().subscribe({
      next: (res) =>{
        console.log(res);
     this.dataSource = new MatTableDataSource(res);
     this.dataSource.sort = this.sort;
     this.dataSource.paginator = this.paginator;
      },
      error: console.log
    })
  }
  
  deletesec(id: number) {
    Swal.fire({
      title: 'Are you sure you want to delete this section group record?',
      text: "This action cannot be undone.", // Emphasize irreversible nature
      icon: 'warning', // Warning icon
      showCancelButton: true,
      confirmButtonColor: '#d33', // Red for delete
      cancelButtonColor: '#1475CB', // Blue for cancel
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this._lecService.deletesecgroup(id).subscribe({
          next: (res) => {
            Swal.fire('Deleted!', 'Section group record has been deleted successfully.', 'success');
            this.getsec(); // Assuming this refreshes the section group list
          },
          error: (err: any) => {
            console.error('Error deleting section group record:', err);
            Swal.fire('Error!', 'An error occurred while deleting the section group record.', 'error');
          }
        });
      }
    });
  }
  openEditsec(data: any){
  const dialogRef = this._dialog.open(FormsecgroupComponent, {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getsec();
        }
      }
    })
   
  }
}
