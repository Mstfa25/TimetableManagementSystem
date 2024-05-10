import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';

@Component({
  selector: 'app-formlecgroup',
  templateUrl: './formlecgroup.component.html',
  styleUrls: ['./formlecgroup.component.scss']
})
export class FormlecgroupComponent {
  lecform: FormGroup;

  constructor(private _fb:FormBuilder, private _lecService: LecgroupService, private _dialogRef: MatDialogRef<FormlecgroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.lecform = this._fb.group({
      name: ['',Validators.required],
   
     

    });
  }
  ngOnInit(): void {
    this.lecform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.lecform.valid){
      if(this.data){
        this._lecService.updatelecgroup(this.data.id,this.lecform.value).subscribe({
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
        
   this._lecService.addlecgroup(this.lecform.value).subscribe({
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

