import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { BranchService } from 'src/app/core/services/branch.service';
import { FormcourstaffComponent } from 'src/app/forms/formcourstaff/formcourstaff.component';
import { FormsecstafComponent } from 'src/app/forms/formsecstaf/formsecstaf.component';

@Component({
  selector: 'app-coursestaff',
  templateUrl: './coursestaff.component.html',
  styleUrls: ['./coursestaff.component.scss']
})
export class CoursestaffComponent {
  displayedColumns: string[] = [ 'course','branch','staff'
  ,'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  //SectionStaff-------------------------------------------------------------------------
  displayedColumns1: string[] = ['course','branch','staff','section', 'action'];
  dataSource1!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator1!: MatPaginator;
  @ViewChild(MatSort) sort1!: MatSort;

constructor(private _dialog:MatDialog, private _corStaService: BranchService, private auth:AuthService) {
  auth.loggedIn.next(true);
}
ngOnInit(): void {
    this.getCostaf();
    this.getSecStaf();

}
openAddEditCorstaf(){
  const dialogRef= this._dialog.open(FormcourstaffComponent );
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getCostaf();
      }
    }
  })
}
getCostaf(){
  this._corStaService.getcourStaffList().subscribe({
    next: (res) =>{
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
deleteCostaf(id:number){
this._corStaService.deletecourStaff(id).subscribe({
  next: (res) => {
   alert(' Deleted !')
   this.getCostaf();
  },
  error: console.log
})
}
openEditcostaf(data: any){
const dialogRef = this._dialog.open(FormcourstaffComponent  , {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getCostaf();
      }
    }
  })
 
}
//SctionStaff-------------------------------------------------------------------------
openAddEditSecStaf() {
  const dialogRef = this._dialog.open(FormsecstafComponent);
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if (val) {
        this.getSecStaf();
      }
    }
  })
}
getSecStaf(){
  this._corStaService.getSecStaffList().subscribe({
    next: (res) =>{
   this.dataSource1 = new MatTableDataSource(res);
   this.dataSource1.sort = this.sort;
   this.dataSource1.paginator = this.paginator;
    },
    error: console.log
  })
}
deleteSecstaf(id:number){
  this._corStaService.deletecourStaff(id).subscribe({
    next: (res) => {
     alert(' Deleted !')
     this.getSecStaf();
    },
    error: console.log
  })
  }
  openEditSecStaf(data: any){
  const dialogRef = this._dialog.open(FormsecstafComponent  , {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getSecStaf();
        }
      }
    })
   
  }

}
