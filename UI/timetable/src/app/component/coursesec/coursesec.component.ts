import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { BranchService } from 'src/app/core/services/branch.service';
import { FormsecstafComponent } from 'src/app/forms/formsecstaf/formsecstaf.component';

@Component({
  selector: 'app-coursesec',
  templateUrl: './coursesec.component.html',
  styleUrls: ['./coursesec.component.scss']
})
export class CoursesecComponent {
  displayedColumns: string[] = ['course','branch','staff','section', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

constructor(private _dialog:MatDialog, private _corStaService: BranchService, private auth:AuthService) {
  auth.loggedIn.next(true);
}
ngOnInit(): void {
    this.getSecStaf();

}
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
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}
getSecStaf(){
  this._corStaService.getSecStaffList().subscribe({
    next: (res) =>{
   this.dataSource = new MatTableDataSource(res);
   this.dataSource.sort = this.sort;
   this.dataSource.paginator = this.paginator;
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
