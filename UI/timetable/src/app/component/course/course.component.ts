import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ViewChild ,Inject,PLATFORM_ID} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { FormcourseComponent } from 'src/app/forms/formcourse/formcourse.component';
import Swal from 'sweetalert2';

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
constructor(@Inject(PLATFORM_ID) private platformId: Object,
private http: HttpClient,private router: Router,private _dialog:MatDialog, private _coursService: FacultyService, private auth:AuthService) {
  auth.loggedIn.next(true);
}

cheeck(): void {
  if (isPlatformBrowser(this.platformId)) {
    // Make the request to the backend server
    const apiUrl = 'http://localhost:7081/api/home';

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    // Make the HTTP request with the updated headers
    this.http.get<any>(apiUrl, { headers, withCredentials: true }).subscribe(
      (response) => {
        if (response[0] === 'home') {
        } else {
          this.router.navigate(['/login']);
          // You can use Angular Router to navigate to the login page
        }
      },
      (error) => {
      }
    );
  }
}

ngOnInit(): void {
  this.cheeck();
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
deletecourse(id: number) {
  Swal.fire({
    title: 'Are you sure you want to delete this course?',
    text: "This action cannot be undone.",
    icon: 'warning', // Warning icon for emphasis
    showCancelButton: true,
    confirmButtonColor: '#d33', // Red for delete action
    cancelButtonColor: '#1475CB', // Blue for cancel
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      this._coursService.deletecourse(id).subscribe({
        next: (res) => {
          Swal.fire('Deleted!', 'Course has been deleted successfully.', 'success');
          this.getCourse(); // Refresh or update course list (adjust as needed)
        },
        error: (err) => {
          console.error('Error deleting course:', err);
          Swal.fire('Error!', 'An error occurred while deleting the course.', 'error');
        }
      });
    }
  });
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
