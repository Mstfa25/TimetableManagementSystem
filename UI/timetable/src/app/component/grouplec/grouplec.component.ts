import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import { FormgrouplecComponent } from 'src/app/forms/formgrouplec/formgrouplec.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-grouplec',
  templateUrl: './grouplec.component.html',
  styleUrls: ['./grouplec.component.scss']
})
export class GrouplecComponent {
  displayedColumns: string[] = [ 'lecname','nameg','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog:MatDialog, private _lecService: LecgroupService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    
    this.getgrlec();
    
  
}
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}
openAddEditgrlec(){
  const dialogRef= this._dialog.open(FormgrouplecComponent);
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getgrlec();
      }
    }
  })
}
getgrlec(){
  this._lecService.getgrouplecList().subscribe({
    next: (res) =>{
   this.dataSource = new MatTableDataSource(res);
   this.dataSource.sort = this.sort;
   this.dataSource.paginator = this.paginator;
    },
    error: console.log
  })
}

deletegrlec(id: number) {
  Swal.fire({
    title: 'Are you sure you want to delete this group lecture record?',
    text: "This action cannot be undone.", // Emphasize irreversible nature
    icon: 'warning', // Warning icon
    showCancelButton: true,
    confirmButtonColor: '#d33', // Red for delete
    cancelButtonColor: '#1475CB', // Blue for cancel
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      this._lecService.deletegrouplec(id).subscribe({
        next: (res) => {
          Swal.fire('Deleted!', 'Group lecture record has been deleted successfully.', 'success');
          this.getgrlec(); // Assuming this refreshes the group lecture list
        },
        error: (err: any) => {
          console.error('Error deleting group lecture record:', err);
          Swal.fire('Error!', 'An error occurred while deleting the group lecture record.', 'error');
        }
      });
    }
  });
}
openEditgrlec(data: any){
const dialogRef = this._dialog.open(FormgrouplecComponent, {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getgrlec();
      }
    }
  })
 
}
}
