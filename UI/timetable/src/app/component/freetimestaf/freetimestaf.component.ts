import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { FreetimeService } from 'src/app/core/services/freetime.service';
import { FormtimestafComponent } from 'src/app/forms/formtimestaf/formtimestaf.component';

@Component({
  selector: 'app-freetimestaf',
  templateUrl: './freetimestaf.component.html',
  styleUrls: ['./freetimestaf.component.scss']
})
export class FreetimestafComponent {
  displayedColumns: string[] = [
    'name','dates','times','timee','action'];
    dataSource!: MatTableDataSource<any>;
  
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog:MatDialog, private _freetimeService: FreetimeService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
      this.getfretimes();
  
  }
  openAddEditfretimes(){
    const dialogRef= this._dialog.open(FormtimestafComponent );
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getfretimes();
        }
      }
    })
  }
  getfretimes(){
    this._freetimeService.gettimestaffList().subscribe({
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
  deletefretimes(id:number){
  this._freetimeService.deletetimestaff(id).subscribe({
    next: (res) => {
     alert(' Deleted !')
     this.getfretimes();
    },
    error: console.log
  })
  }
  openEditfretimes(data: any){
  const dialogRef = this._dialog.open(FormtimestafComponent , {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getfretimes();
        }
      }
    })
   
  }
}
