import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { FacultyService } from 'src/app/core/services/faculty.service';
import Swal from 'sweetalert2';

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
        const data = {
          id: this.semform.get('sId')?.value,
          number: this.semform.get('snumber')?.value,
          studyPlan: { id: this.semform.get('studyname')?.value }
        };
    
        // Informative confirmation message (consider tailoring to your context):
        Swal.fire({
          title: 'Are you sure you want to update this semester?',
          text: "The updated information, including semester number and study plan, will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._semsService.updateSemsterit(data).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Semester has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating semester:', err);
                Swal.fire('Error!', 'An error occurred while updating the semester.', 'error');
              }
            });
          }
        });
    
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
