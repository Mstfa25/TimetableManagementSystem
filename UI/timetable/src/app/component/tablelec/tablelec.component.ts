import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';
import { FormtablelecComponent } from 'src/app/forms/formtablelec/formtablelec.component';

@Component({
  selector: 'app-tablelec',
  templateUrl: './tablelec.component.html',
  styleUrls: ['./tablelec.component.scss']
})
export class TablelecComponent {
  displayedColumns: string[] = [
  'course','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
constructor(private _dialog:MatDialog, private _tableService: TablesService, private auth:AuthService) {
  auth.loggedIn.next(true);
}
ngOnInit(): void {
    this.getTablelec();

}
openAddEditTablelec(){
  const dialogRef= this._dialog.open(FormtablelecComponent );
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getTablelec();
      }
    }
  })
}
getTablelec(){
  this._tableService.gettablelecList().subscribe({
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
deleteTablelec(id:number){
this._tableService.deletetablelec(id).subscribe({
  next: (res) => {
   alert(' Deleted !')
   this.getTablelec();
  },
  error: console.log
})
}
openEditTablelec(data: any){
const dialogRef = this._dialog.open(FormtablelecComponent , {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getTablelec();
      }
    }
  })
 
}
}
