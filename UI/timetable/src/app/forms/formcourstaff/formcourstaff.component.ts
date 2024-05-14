import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BranchService } from 'src/app/core/services/branch.service';

@Component({
  selector: 'app-formcourstaff',
  templateUrl: './formcourstaff.component.html',
  styleUrls: ['./formcourstaff.component.scss']
})
export class FormcourstaffComponent {
  coursestform: FormGroup;
  courses: string[] = [
    'gg1',
    'l25',
   
  ];
  branch: string[]=[
    'fayoum',
    'dokki'
  ];
  staff: string[]=[
    'TA',
    'docter'
  ]

  constructor(private _fb:FormBuilder, private _couStaffService: BranchService, private _dialogRef: MatDialogRef<FormcourstaffComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.coursestform = this._fb.group({
      course: ['',Validators.required],
      
    branch: ['',Validators.required],
    staff: ['',Validators.required],
   

    });
  }
  ngOnInit(): void {
    this.coursestform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.coursestform.valid){
      if(this.data){
        this._couStaffService.updatecourStaff(this.data.id,this.coursestform.value).subscribe({
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
        
   this._couStaffService.addcourStaff(this.coursestform.value).subscribe({
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
