import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormsecComponent } from 'src/app/forms/formsec/formsec.component';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.scss']
})
export class SectionComponent {
  displayedColumns: string[] = [ 'name'
  ,'action'];
  dataSource!: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
constructor(private _dialog:MatDialog, private _secService: FacultyService, private auth:AuthService) {
  auth.loggedIn.next(true);
}
ngOnInit(): void {
    this.getSec();

}
openAddEditsection(){
  const dialogRef= this._dialog.open(FormsecComponent );
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getSec();
      }
    }
  })
}
getSec(){
  this._secService.getseceList().subscribe({
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
deletesec(id:number){
this._secService.deletesec(id).subscribe({
  next: (res) => {
   alert(' Deleted !')
   this.getSec();
  },
  error: console.log
})
}
openEditSec(data: any){
const dialogRef = this._dialog.open(FormsecComponent , {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getSec();
      }
    }
  })
 
}
}
