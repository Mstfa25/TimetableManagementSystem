import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FreetimeService } from 'src/app/core/services/freetime.service';

@Component({
  selector: 'app-formtimeroom',
  templateUrl: './formtimeroom.component.html',
  styleUrls: ['./formtimeroom.component.scss']
})
export class FormtimeroomComponent {
  timeroomform: FormGroup;


  constructor(private _fb:FormBuilder, private _freetimeService: FreetimeService, private _dialogRef: MatDialogRef<FormtimeroomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.timeroomform = this._fb.group({
      name: ['',Validators.required],
      
    dates: ['',Validators.required],
    times: ['',Validators.required],
    timee: ['',Validators.required],
    

    });
  }
  ngOnInit(): void {
    this.timeroomform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.timeroomform.valid){
      if(this.data){
        this._freetimeService.updatetimeroom(this.data.id,this.timeroomform.value).subscribe({
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
        
   this._freetimeService.addtimeroom(this.timeroomform.value).subscribe({
      next: (val: any) =>{
       alert('Added Successfuly')
       this._dialogRef.close(true);
      },
      error: (err:any) =>{
        console.error(err)
      }
     })
    }
  }}
}
