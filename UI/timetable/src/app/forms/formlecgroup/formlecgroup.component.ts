import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formlecgroup',
  templateUrl: './formlecgroup.component.html',
  styleUrls: ['./formlecgroup.component.scss']
})
export class FormlecgroupComponent {
  lecform: FormGroup;

  constructor(private _fb:FormBuilder, private _lecService: LecgroupService, private _dialogRef: MatDialogRef<FormlecgroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.lecform = this._fb.group({
      name: ['',Validators.required],
   
     

    });
  }
  ngOnInit(): void {
    this.lecform.patchValue(this.data)  

  }
  onFormSubmit(){
    if (this.lecform.valid) {
      if (this.data) {
        Swal.fire({
          title: 'Are you sure you want to update this lecture group?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._lecService.updatelecgroup(this.data.id, this.lecform.value).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Lecture group has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating lecture group:', err);
                Swal.fire('Error!', 'An error occurred while updating the group.', 'error');
              }
            });
          }
        });
      }
      else{
        
   this._lecService.addlecgroup(this.lecform.value).subscribe({
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

