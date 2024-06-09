import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BranchService } from 'src/app/core/services/branch.service';

@Component({
  selector: 'app-formsecstaf',
  templateUrl: './formsecstaf.component.html',
  styleUrls: ['./formsecstaf.component.scss']
})
export class FormsecstafComponent {
secstafform: FormGroup;
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
  ];


  constructor(private _fb:FormBuilder, private _secstafService: BranchService, private _dialogRef: MatDialogRef<FormsecstafComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.secstafform = this._fb.group({
      course: ['',Validators.required],
      
    branch: ['',Validators.required],
    staff: ['',Validators.required],
    section: ['',Validators.required],

    });
  }
  ngOnInit(): void {
    this.secstafform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.secstafform.valid){
      if(this.data){
        this._secstafService.updateSecStaff(this.data.id,this.secstafform.value).subscribe({
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
        
   this._secstafService.addSecStaff(this.secstafform.value).subscribe({
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
