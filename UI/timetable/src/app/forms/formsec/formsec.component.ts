import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacultyService } from 'src/app/core/services/faculty.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formsec',
  templateUrl: './formsec.component.html',
  styleUrls: ['./formsec.component.scss']
})
export class FormsecComponent {
  secionform: FormGroup;
 

  constructor(private _fb:FormBuilder, private _secService: FacultyService, private _dialogRef: MatDialogRef<FormsecComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.secionform = this._fb.group({
  
    name: ['',Validators.required],

    });
  }
  ngOnInit(): void {
    this.secionform.patchValue(this.data)  

  }
  onFormSubmit(){
    if (this.secionform.valid) {
      if (this.data) {
        Swal.fire({
          title: 'Are you sure you want to update this section?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._secService.updatesec(this.data.id, this.secionform.value).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Section has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating section:', err);
                Swal.fire('Error!', 'An error occurred while updating the section.', 'error');
              }
            });
          }
        });
      }
      else{
        
   this._secService.addsec(this.secionform.value).subscribe({
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
