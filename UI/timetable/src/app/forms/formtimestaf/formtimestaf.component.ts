import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FreetimeService } from 'src/app/core/services/freetime.service';
import { TablesService } from 'src/app/core/services/tables.service';

@Component({
  selector: 'app-formtimestaf',
  templateUrl: './formtimestaf.component.html',
  styleUrls: ['./formtimestaf.component.scss']
})
export class FormtimestafComponent {
  timestafform: FormGroup;


  constructor(private _fb:FormBuilder, private _freetimeService: FreetimeService, private _dialogRef: MatDialogRef<FormtimestafComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.timestafform = this._fb.group({
      id: ['',Validators.required],
      name: ['',Validators.required],
      
    dates: ['',Validators.required],
    times: ['',Validators.required],
    timee: ['',Validators.required],
    

    });
  }
  ngOnInit(): void {
    this.timestafform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.timestafform.valid){
      if(this.data){
        this._freetimeService.updatetimestaff(this.data.id).subscribe({
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
        
   this._freetimeService.addtimestaff(this.timestafform.value).subscribe({
      next: (val: any) =>{
       alert('Added Successfuly')
       this._dialogRef.close(true);
      },
      error: (err:any) =>{
        console.error(err)
      }
     })
    }
  }}}

