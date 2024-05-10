import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacultyService } from 'src/app/core/services/faculty.service';

@Component({
  selector: 'app-formsec',
  templateUrl: './formsec.component.html',
  styleUrls: ['./formsec.component.scss']
})
export class FormsecComponent {
  secionform: FormGroup;
 secname: string[] = [
    'gg1',
    'l25',
   
  ];
  branches: string[] = [
    'fayoum',
   
  ];

  constructor(private _fb:FormBuilder, private _secService: FacultyService, private _dialogRef: MatDialogRef<FormsecComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.secionform = this._fb.group({
      secname: ['',Validators.required],
      
    namese: ['',Validators.required],
    branch: ['',Validators.required],
    capacity: ['',Validators.required],

    });
  }
  ngOnInit(): void {
    this.secionform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.secionform.valid){
      if(this.data){
        this._secService.updatesec(this.data.id,this.secionform.value).subscribe({
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
        
   this._secService.addsec(this.secionform.value).subscribe({
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
