import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacultyService } from 'src/app/core/services/faculty.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formfaculty',
  templateUrl: './formfaculty.component.html',
  styleUrls: ['./formfaculty.component.scss']
})
export class FormfacultyComponent {
  fForm: FormGroup;
  constructor(private _fb: FormBuilder, private _facultyService: FacultyService, private _dialogRef: MatDialogRef<FormfacultyComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.fForm = this._fb.group({
      name: ['', Validators.required]
    });
  }
  ngOnInit(): void {
    this.fForm.patchValue(this.data)
  }
  onFormSubmit() {
    if (this.fForm.valid) {
      if (this.data) {
        const data = {
          id: this.data.id,
          name: this.fForm.get("name")?.value
        };
    
        Swal.fire({
          title: 'Are you sure you want to update this faculty?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._facultyService.updateFaculty(data).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Faculty has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating faculty:', err);
                Swal.fire('Error!', 'An error occurred while updating the faculty.', 'error');
              }
            });
          }
        });
      }
      else {
        const data = {
          Faculty: { id: this.fForm.get("name")?.value }
        }
        this._facultyService.addFaculty(this.fForm.value).subscribe({
          next: (val: any) => {
            alert('Faculty Added Successful');
            this._dialogRef.close(true)
          },
          error: (err: any) => {
            console.error(err)
          }
        })
      }
    }
  }
}

