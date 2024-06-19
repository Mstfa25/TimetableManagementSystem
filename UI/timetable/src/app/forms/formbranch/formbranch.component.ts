
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BranchService } from 'src/app/core/services/branch.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formbranch',
  templateUrl: './formbranch.component.html',
  styleUrls: ['./formbranch.component.scss']
})
export class FormbranchComponent implements OnInit {
  brnForm: FormGroup;
  constructor(private _fb: FormBuilder, private _branchService: BranchService, private _dialogRef: MatDialogRef<FormbranchComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.brnForm = this._fb.group({
      name: ['', Validators.required]
    });
  }
  ngOnInit(): void {
    this.brnForm.patchValue(this.data)
  }
  onFormSubmit() {
    if (this.brnForm.valid) {
      // Create an object with the fields id and name
      const dataToSend = {
        id: this.data ? this.data.id : null,
        name: this.brnForm.value.name
      };
  
      if (this.data) { // Check if data exists before proceeding
        Swal.fire({
          title: 'Are you sure you want to update this branch?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result:any) => {
          if (result.isConfirmed) {
            this._branchService.updateBranch(dataToSend).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Branch has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating branch:', err);
                Swal.fire('Error!', 'An error occurred while updating the branch.', 'error');
              }
            });
          }
        });
      }
      else {
        this._branchService.addBranch(dataToSend).subscribe({
          next: (val: any) => {
            alert('Branch Added Successful');
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
