import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Branch } from 'src/app/core/interfaces/branch';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';

@Component({
  selector: 'app-formsecgroup',
  templateUrl: './formsecgroup.component.html',
  styleUrls: ['./formsecgroup.component.scss']
})
export class FormsecgroupComponent {
 
  secform: FormGroup;
  lecname: string[] = [
    'gg1',
    'l25',
   
  ];
  grouplecname: string[] = [
    '156',
    '454'
  ];
  groupbranname: string[] = [
    'dokki',
    'fayoum',
    'tanta'
  ];

 
  constructor(private _fb:FormBuilder, private _secService: LecgroupService, private _dialogRef: MatDialogRef<FormsecgroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.secform = this._fb.group({
      namesec: ['',Validators.required],
      lecname: ['',Validators.required],
      grouplecname: ['',Validators.required],
      groupbranname: ['',Validators.required]

    });
  }
  ngOnInit(): void {
    this.secform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.secform.valid){
      if(this.data){
        this._secService.updatesecgroup(this.data.id,this.secform.value).subscribe({
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
        
   this._secService.addsecgroup(this.secform.value).subscribe({
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
