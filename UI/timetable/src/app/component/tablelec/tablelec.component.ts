import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Lectime } from 'src/app/core/interfaces/lectime';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';


@Component({
  selector: 'app-tablelec',
  templateUrl: './tablelec.component.html',
  styleUrls: ['./tablelec.component.scss']
})
export class TablelecComponent {
  userFrom: FormGroup;
listdata:any;
course: string[] = [
  'c1',
  'C2',
  'C3',
];
semster:string[]=[
  's1',
  's2'
]
constructor(private fb:FormBuilder ,private auth:AuthService) 
 

{
  auth.loggedIn.next(true);
  this.listdata=[]
  this.userFrom=this.fb.group({
    name:['',Validators.required],
    course:['',Validators.required],
    semster:[''],
  })
}
 public additem():void{
this.listdata.push(this.userFrom.value)
this.userFrom.reset();
 }

  resetitem(){
    this.userFrom.reset();
  }

  removeitem(element:any){
this.listdata.forEach((value:any,index:any)=>{
  if(value== element)
    this.listdata.splice(index,1)

})
  }
}
