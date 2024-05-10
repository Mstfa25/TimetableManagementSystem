import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormcourseComponent } from 'src/app/forms/formcourse/formcourse.component';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent {
  displayedColumns: string[] = [ 'namec','code','lechour','sechour','lecname','secgroup'
  ,'faculty','study','semster','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
constructor(private _dialog:MatDialog, private _coursService: FacultyService, private auth:AuthService) {
  auth.loggedIn.next(true);
}
ngOnInit(): void {
    this.getCourse();

}
openAddEditcourse(){
  const dialogRef= this._dialog.open(FormcourseComponent );
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getCourse();
      }
    }
  })
}
getCourse(){
  this._coursService.getcourseList().subscribe({
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
deletecourse(id:number){
this._coursService.deletecourse(id).subscribe({
  next: (res) => {
   alert(' Deleted !')
   this.getCourse();
  },
  error: console.log
})
}
openEditcourse(data: any){
const dialogRef = this._dialog.open(FormcourseComponent , {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getCourse();
      }
    }
  })
 
}
}
