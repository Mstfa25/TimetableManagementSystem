import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import { FormsecgroupComponent } from 'src/app/forms/formsecgroup/formsecgroup.component';

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
     this.dataSource = new MatTableDataSource(res);
     this.dataSource.sort = this.sort;
     this.dataSource.paginator = this.paginator;
      },
      error: console.log
    })
  }
  
  deletesec(id:number){
  this._lecService.deletesecgroup(id).subscribe({
    next: (res) => {
     alert(' Deleted !')
     this.getsec();
    },
    error: console.log
  })
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
