import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';


@Component({
  selector: 'app-tablesec',
  templateUrl: './tablesec.component.html',
  styleUrls: ['./tablesec.component.scss']
})
export class TablesecComponent {
  useFrom: FormGroup;
listdata:any;
course: string[] = [
  'c1',
  'C2',
  'C3',
];
branch:string[]=[
  'b1',
  'b2'
]
table:string[]=[
  't1',
  't2'
]
constructor(private fb:FormBuilder ,private auth:AuthService) 
 

{
  auth.loggedIn.next(true);
  this.listdata=[]
  this.useFrom=this.fb.group({
    branch:['',Validators.required],
    course:['',Validators.required],
    table:['',Validators.required],

  })
}
 public additem():void{
this.listdata.push(this.useFrom.value)
this.useFrom.reset();
 }

  resetitem(){
    this.useFrom.reset();
  }

  removeitem(element:any){
this.listdata.forEach((value:any,index:any)=>{
  if(value== element)
    this.listdata.splice(index,1)

})
  }
}
