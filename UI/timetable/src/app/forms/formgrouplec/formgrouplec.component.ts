import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';

@Component({
  selector: 'app-formgrouplec',
  templateUrl: './formgrouplec.component.html',
  styleUrls: ['./formgrouplec.component.scss']
})
export class FormgrouplecComponent {
  groupform: FormGroup;
 lecname: any[] = [];
 

  constructor(private _fb:FormBuilder, private _grouplecService: LecgroupService, private _dialogRef: MatDialogRef<FormgrouplecComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private http: HttpClient
  ){
    this.groupform = this._fb.group({
      lecname: ['',Validators.required],
      
    nameg: ['',Validators.required],

    });
  }
  ngOnInit(): void {

    (this.http.get('http://localhost:7081/api/admin/getAllLectuerGroups',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.lecname = data;
      });
    this.groupform.patchValue(this.data)  

  }
  onFormSubmit(){
    if(this.groupform.valid){
      if(this.data){
        this._grouplecService.updategrouplec(this.data.id,this.groupform.value).subscribe({
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
        
   this._grouplecService.addgrouplec(this.groupform.value).subscribe({
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
