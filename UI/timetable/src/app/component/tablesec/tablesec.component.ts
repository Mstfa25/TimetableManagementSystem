import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';
import { FormtablesecComponent } from 'src/app/forms/formtablesec/formtablesec.component';

@Component({
  selector: 'app-tablesec',
  templateUrl: './tablesec.component.html',
  styleUrls: ['./tablesec.component.scss']
})
export class TablesecComponent {
  displayedColumns: string[] = ['table','branch',
    'course','action'];
    dataSource!: MatTableDataSource<any>;
  
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog:MatDialog, private _tableService: TablesService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
      this.getTablesec();
  
  }
  openAddEditTablesec(){
    const dialogRef= this._dialog.open(FormtablesecComponent );
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getTablesec();
        }
      }
    })
  }
  getTablesec(){
    this._tableService.gettablesecList().subscribe({
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
  deleteTablesec(id:number){
  this._tableService.deletetablesec(id).subscribe({
    next: (res) => {
     alert(' Deleted !')
     this.getTablesec();
    },
    error: console.log
  })
  }
  openEditTablesec(data: any){
  const dialogRef = this._dialog.open(FormtablesecComponent , {
      data,
     });
     dialogRef.afterClosed().subscribe({
      next: (val) => {
        if(val){
          this.getTablesec();
        }
      }
    })
   
  }
}
