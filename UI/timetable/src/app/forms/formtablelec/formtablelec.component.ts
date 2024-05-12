import { Component, Inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BranchService } from 'src/app/core/services/branch.service';
import { TablesService } from 'src/app/core/services/tables.service';

@Component({
  selector: 'app-formtablelec',
  templateUrl: './formtablelec.component.html',
  styleUrls: ['./formtablelec.component.scss']
})
export class FormtablelecComponent {
 tableform: FormGroup;
  courses: string[] = [
    'course1',
    'cours2',
   
  ];


  constructor(private _fb:FormBuilder, private _tableService: TablesService, private _dialogRef: MatDialogRef<FormtablelecComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.tableform = this._fb.group({
      course: ['',Validators.required],
      
 

    });
  }
  ngOnInit(): void {
    this.tableform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.tableform.valid){
      if(this.data){
        this._tableService.updatetablelec(this.data.id,this.tableform.value).subscribe({
          next: (val: any) =>{
           alert('Update Successfuly')
           this._dialogRef.close(true);
          },
          error: (err:any) =>{
            console.error(err)
          }
         })
      }
      else{
        
   this._tableService.addtablelec(this.tableform.value).subscribe({
      next: (val: any) =>{
       alert('Added Successfuly')
       this._dialogRef.close(true);
      },
      error: (err:any) =>{
        console.error(err)
      }
     })
    }
  }
}
}
