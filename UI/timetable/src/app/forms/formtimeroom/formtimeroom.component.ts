import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FreetimeService } from 'src/app/core/services/freetime.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formtimeroom',
  templateUrl: './formtimeroom.component.html',
  styleUrls: ['./formtimeroom.component.scss']
})
export class FormtimeroomComponent {
  timeroomform: FormGroup;


  constructor(private _fb: FormBuilder, private _freetimeService: FreetimeService, private _dialogRef: MatDialogRef<FormtimeroomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.timeroomform = this._fb.group({

      id: ['',Validators.required],
      name: ['',Validators.required],
      
    dates: ['',Validators.required],
    times: ['',Validators.required],
    timee: ['',Validators.required],
    

    });
  }
  ngOnInit(): void {
    this.timeroomform.patchValue(this.data)

  }
  onFormSubmit() {
    if (this.timeroomform.valid) {
      if (this.data) {
        // Confirmation dialog with informative message
        Swal.fire({
          title: 'Are you sure you want to update this timeroom?',
          text: "The updated information will be saved.", // Consider tailoring the text if specific fields are updated
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._freetimeService.updatetimeroom(this.data.id).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Timeroom has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating timeroom:', err);
                Swal.fire('Error!', 'An error occurred while updating the timeroom.', 'error');
              }
            });
          }
        });
      }
      else {

        this._freetimeService.addtimeroom(this.timeroomform.value).subscribe({
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
}
