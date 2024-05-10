import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacultyService } from 'src/app/core/services/faculty.service';

@Component({
  selector: 'app-formcourse',
  templateUrl: './formcourse.component.html',
  styleUrls: ['./formcourse.component.scss']
})
export class FormcourseComponent {
  courseform: FormGroup;
  lecname: string[] = [
     'gg1',
     'l25',
    
   ];
   secgroup: string[] = [
    "S1",
    "S2"
   ];
   faculty: string[] = [
    "It",
    "BA"
   ];
   study: string[]=[
    "5545"
   ];
   semster: string[]=[
    "8"
   ]

  
 
   constructor(private _fb:FormBuilder, private _courseService: FacultyService, private _dialogRef: MatDialogRef<FormcourseComponent>,
     @Inject(MAT_DIALOG_DATA) public data: any
   ){
     this.courseform = this._fb.group({
      namec: ['',Validators.required],
      code: ['',Validators.required],
      lechour: ['',Validators.required],
      sechour: ['',Validators.required],
       lecname: ['',Validators.required],
       secgroup: ['',Validators.required],
       faculty: ['',Validators.required],
      study: ['',Validators.required],
      semster: ['',Validators.required],
 
     });
   }
   ngOnInit(): void {
     this.courseform.patchValue(this.data)  
 
   }
   onFormSubmit(){
     if(this.courseform.valid){
       if(this.data){
         this._courseService.updatecourse(this.data.id,this.courseform.value).subscribe({
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
         
    this._courseService.addcourse(this.courseform.value).subscribe({
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
