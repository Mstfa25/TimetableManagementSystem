import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { FacultyService } from 'src/app/core/services/faculty.service';

@Component({
  selector: 'app-formsec-group',
  templateUrl: './formsec-group.component.html',
  styleUrls: ['./formsec-group.component.scss']
})
export class FormsecGroupComponent {
  secname: any[] = [];
  branches: any[] = [];

  sgForm: FormGroup;
  constructor(private _fb: FormBuilder, private _secgroupService: FacultyService, private _dialogRef: MatDialogRef< FormsecGroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private http: HttpClient

  ) {
    this.sgForm = this._fb.group({
      secname: ['', Validators.required],
      branch: ['', Validators.required],
      capacty:['', Validators.required]
      
    });
  }
  ngOnInit(): void {
    (this.http.get('http://localhost:7081/api/admin/getAllBranches',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.branches = data;
      });
      (this.http.get('http://localhost:7081/api/admin/getSectionGroups',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.secname = data;
      });
    this.sgForm.patchValue(this.data)
  }
  onFormSubmit(){
    if(this.sgForm.valid){
      if(this.data){
        this._secgroupService.updatsecgrop(this.data.id,this.sgForm.value).subscribe({
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
        
   this._secgroupService.addsecgrop(this.sgForm.value).subscribe({
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
  

