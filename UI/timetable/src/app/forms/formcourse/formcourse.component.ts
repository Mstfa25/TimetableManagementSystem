import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formcourse',
  templateUrl: './formcourse.component.html',
  styleUrls: ['./formcourse.component.scss']
})
export class FormcourseComponent {
  courseform: FormGroup;
  lecname: any[] = [];
  secgroup: any[] = [];
  faculty: any[] = [];
  study: any[] = [];
  semster: any[] = []



  constructor(private _fb: FormBuilder, private _courseService: FacultyService, private _dialogRef: MatDialogRef<FormcourseComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private http: HttpClient
  ) {
    this.courseform = this._fb.group({
      namec: ['', Validators.required],
      code: ['', Validators.required],
      lechour: ['', Validators.required],
      sechour: ['', Validators.required],
      lecname: ['', Validators.required],
      secgroup: ['', Validators.required],
      faculty: ['', Validators.required],
      study: ['', Validators.required],
      semster: ['', Validators.required],

    });
  }
  ngOnInit(): void {
    (this.http.get('http://localhost:7081/api/admin/getAllLectuerGroups', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.lecname = data;
      });
    (this.http.get('http://localhost:7081/api/admin/getSectionGroups', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.secgroup = data;
      });
    (this.http.get('http://localhost:7081/api/admin/getAllFacultysWithThereStudyPlansAndStemesters', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.faculty = data;
      });
    this.courseform.patchValue(this.data)

  }
  onFormSubmit() {
    if (this.courseform.valid) {
      if (this.data) {
        this._courseService.updatecourse(this.data.id, this.courseform.value).subscribe({
          next: (val: any) => {
            alert('Update Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)
          }
        })
      }
      else {

        this._courseService.addcourse(this.courseform.value).subscribe({
          next: (val: any) => {
            alert('Added Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)
          }
        })
      }
    }
  }

  onFacultySelect(event: any) {
    const selectedFaculty = event.value;
    this.study=event.value.studyPlans;
    console.log('Selected Faculty:', event.value.studyPlans);
  }

  onStudyPlanSelect(event: any) {
    this.semster=event.value.semesters;
    console.log('Selected Faculty:', event.value.semesters);
  }

}
