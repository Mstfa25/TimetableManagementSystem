import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TablesService } from 'src/app/core/services/tables.service';

@Component({
  selector: 'app-formtablesec',
  templateUrl: './formtablesec.component.html',
  styleUrls: ['./formtablesec.component.scss']
})
export class FormtablesecComponent {
  tabsecform: FormGroup;
  courses: string[] = [
    'gg1',
    'l25',
   
  ];
  branch: string[]=[
    'fayoum',
    'dokki'
  ];
  table: string[]=[
    't1',
    't2'
  ];
  

  constructor(private _fb:FormBuilder, private _tablefService: TablesService, private _dialogRef: MatDialogRef<FormtablesecComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.tabsecform = this._fb.group({
      course: ['',Validators.required],
      
    branch: ['',Validators.required],
    table: ['',Validators.required],
    

    });
  }
  ngOnInit(): void {
    this.tabsecform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.tabsecform.valid){
      if(this.data){
        this._tablefService.updatetablesec(this.data.id,this.tabsecform.value).subscribe({
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
        
   this._tablefService.addtablesec(this.tabsecform.value).subscribe({
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
