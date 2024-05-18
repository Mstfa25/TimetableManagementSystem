import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import { FormgrouplecComponent } from 'src/app/forms/formgrouplec/formgrouplec.component';

@Component({
  selector: 'app-grouplec',
  templateUrl: './grouplec.component.html',
  styleUrls: ['./grouplec.component.scss']
})
export class GrouplecComponent {
  displayedColumns: string[] = [ 'lecname','nameg','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog:MatDialog, private _lecService: LecgroupService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    
    this.getgrlec();
    
  
}
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}
openAddEditgrlec(){
  const dialogRef= this._dialog.open(FormgrouplecComponent);
  dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getgrlec();
      }
    }
  })
}
getgrlec(){
  this._lecService.getgrouplecList().subscribe({
    next: (res) =>{
   this.dataSource = new MatTableDataSource(res);
   this.dataSource.sort = this.sort;
   this.dataSource.paginator = this.paginator;
    },
    error: console.log
  })
}

deletegrlec(id:number){
this._lecService.deletegrouplec(id).subscribe({
  next: (res) => {
   alert(' Deleted !')
   this.getgrlec();
  },
  error: console.log
})
}
openEditgrlec(data: any){
const dialogRef = this._dialog.open(FormgrouplecComponent, {
    data,
   });
   dialogRef.afterClosed().subscribe({
    next: (val) => {
      if(val){
        this.getgrlec();
      }
    }
  })
 
}
}
