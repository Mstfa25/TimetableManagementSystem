import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit, PLATFORM_ID, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Room } from 'src/app/core/interfaces/branch';
import { AuthService } from 'src/app/core/services/auth.service';
import { BranchService } from 'src/app/core/services/branch.service';
import { FormroomComponent } from 'src/app/forms/formroom/formroom.component';

@Component({
  selector: 'app-branch',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {
  displayedColumns: string[] = [ 'roomname', 'capacity', 'bname', 'troom', 'action'];
  dataSource!: MatTableDataSource<Room>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog: MatDialog,
    private _roomService: BranchService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private http: HttpClient,
    private router: Router,
    auth:AuthService
  ) { 
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    this.cheek();
    this.getRoom();
  }

  cheek(){
    if (isPlatformBrowser(this.platformId)) {
      const apiUrl = 'http://localhost:7081/api/home';

      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      });
      this.http.get<any>(apiUrl, { headers, withCredentials: true }).subscribe(
        (response) => {
          if (response[0] === 'home') {
            return true;
          } else {

            this.router.navigate(['/login']);
          return false;
          }
        },
        () => {
          return false;
        }
      );
    }
  }
  openAddEditRoom() {
    const dialogRef = this._dialog.open(FormroomComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getRoom();
        }
      }
    })
  }
  getRoom() {
      this._roomService.getRoomList().subscribe({
        next: (res) => {
          const displayedData = res.map((rooom: {id:number;
             name: any;
              capacity: any;
               roomtype: { name: any;id:number };
                branch: { name: any; id:number}; }) => ({
            id:rooom.id,
            roomname: rooom.name,
            capacity: rooom.capacity,
            troom: rooom.roomtype.name,
            bname: rooom.branch.name
          }));
          this.dataSource = new MatTableDataSource(displayedData);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        },
        error: console.log
      });
    }
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  deleteRoom(id: number) {
      // Include the ID in the body of the request
      const body = { id };
      // Make the HTTP request with the updated headers
      this._roomService.deleteRoom(body).subscribe({
        next: (res) => {
          // Handle the response as needed
          alert('Room Deleted !')
          this.getRoom();
        },
        error: console.log
      });
    }
  
   
  openEditRoom(data: any) {
    const dialogRef = this._dialog.open(FormroomComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getRoom();
        }
      }
    })

  }
}
