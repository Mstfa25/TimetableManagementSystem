import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { FreetimeService } from 'src/app/core/services/freetime.service';
import { FormtimeroomComponent } from 'src/app/forms/formtimeroom/formtimeroom.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-freetimeroom',
  templateUrl: './freetimeroom.component.html',
  styleUrls: ['./freetimeroom.component.scss']
})
export class FreetimeroomComponent {
  displayedColumns: string[] = ['id','name', 'dates', 'times', 'timee', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(@Inject(PLATFORM_ID) private platformId: Object,
    private http: HttpClient,private router: Router, private _dialog: MatDialog, private _freetimeService: FreetimeService, private auth: AuthService) {
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
    this.getfretimer();

  }
  openAddEditfretimer() {
    const dialogRef = this._dialog.open(FormtimeroomComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getfretimer();
        }
      }
    })
  }
  getfretimer() {
    this._freetimeService.gettimeroomList().subscribe({
      next: (res) => {
        const displayedData = res.map((
          rooom: {
           dayStartEnd: { startSession: number;endSession:number;dayId:number; };
           roomName: any; 
           roomId: any;
           id:number;  
          }) => ({
              //['name', 'dates', 'times', 'timee', 'action'];

         id:rooom.id,
         name: rooom.roomName,
         dates: this.getLocaleDayNames(rooom.dayStartEnd.dayId),
         times: rooom.dayStartEnd.startSession,
         timee:rooom.dayStartEnd.endSession,
       }));
        this.dataSource = new MatTableDataSource(displayedData);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log
    })
  }

  getLocaleDayNames(number:number):any{
    switch(number){
      case 0:return "saturday";break;
      case 1:return "sunday";break;
      case 2:return "moneday";break;
      case 3:return "tuesday";break;
      case 4:return "wednesday";break;
      case 5:return "thursday";break;
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  deletefretimer(id: number) {
    Swal.fire({
      title: 'Are you sure you want to delete this free time room?',
      text: "This action cannot be undone.", // Emphasize irreversible nature
      icon: 'warning', // Warning icon
      showCancelButton: true,
      confirmButtonColor: '#d33', // Red for delete
      cancelButtonColor: '#1475CB', // Blue for cancel
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this._freetimeService.deletetimeroom(id).subscribe({
          next: (res) => {
            Swal.fire('Deleted!', 'Free time room has been deleted successfully.', 'success');
            this.getfretimer(); // Assuming this refreshes the free time room list
          },
          error: (err: any) => {
            console.error('Error deleting free time room:', err);
            Swal.fire('Error!', 'An error occurred while deleting the free time room.', 'error');
          }
        });
      }
    });
  }
  openEditfretimer(data: any) {
    const dialogRef = this._dialog.open(FormtimeroomComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getfretimer();
        }
      }
    })

  }
}
