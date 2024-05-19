import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { FreetimeService } from 'src/app/core/services/freetime.service';
import { FormtimeroomComponent } from 'src/app/forms/formtimeroom/formtimeroom.component';

@Component({
  selector: 'app-freetimeroom',
  templateUrl: './freetimeroom.component.html',
  styleUrls: ['./freetimeroom.component.scss']
})
export class FreetimeroomComponent {
  displayedColumns: string[] = [
    'name','dates','times','timee','action'];
    dataSource!: MatTableDataSource<any>;
  
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog:MatDialog, private _freetimeService: FreetimeService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
      this.getfretimer();
  
  }
  openAddEditfretimer(){
    const dialogRef= this._dialog.open(FormtimeroomComponent );
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getfretimer();
        }
      }
    })
  }
  getfretimer(){
    this._freetimeService.gettimeroomList().subscribe({
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
  deletefretimer(id:number){
  this._freetimeService.deletetimeroom(id).subscribe({
    next: (res) => {
     alert(' Deleted !')
     this.getfretimer();
    },
    error: console.log
  })
  }
  openEditfretimer(data: any){
  const dialogRef = this._dialog.open(FormtimeroomComponent , {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getfretimer();
        }
      }
    })
   
  }
}
