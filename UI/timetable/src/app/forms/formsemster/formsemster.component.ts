import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { FacultyService } from 'src/app/core/services/faculty.service';

@Component({
  selector: 'app-formsemster',
  templateUrl: './formsemster.component.html',
  styleUrls: ['./formsemster.component.scss']
})
export class FormsemsterComponent {
  semform: FormGroup;
  stname: any[] = [];

  constructor(private _fb: FormBuilder, private _semsService: FacultyService, private _dialogRef: MatDialogRef<FormsemsterComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private http: HttpClient

  ) {
    this.semform = this._fb.group({
      snumber: ['', Validators.required],
      sId: [''],
      ssId: [''],
      studyname: ['', Validators.required]
    });
  }
  ngOnInit(): void {
    this.semform.patchValue(this.data);
    (this.http.get('http://localhost:7081/api/admin/getAllStudyPlans', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.stname = data;
      });
  }
  onFormSubmit() {
    if (this.semform.valid) {
      
      if (this.data) {
        const data={
          id:this.semform.get('sId')?.value,
          number:this.semform.get('snumber')?.value,
          studyPlan:{id:this.semform.get('studyname')?.value}
      }
      console.log(data);
        this._semsService.updateSemsterit(data).subscribe({
          next: (val: any) => {
            alert('Update Successfuly')
            this._dialogRef.close(true);
          }
        })
      }
      else {
        const data={
          number:this.semform.get('snumber')?.value,
          studyPlan:{id:this.semform.get('studyname')?.value}
        }
        this._semsService.addSemsterit(data).subscribe({
          next: (val: any) => {
            alert(' Added Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)
          }
        })
      }
    }
  }
}
