import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { BranchService } from 'src/app/core/services/branch.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formcourstaff',
  templateUrl: './formcourstaff.component.html',
  styleUrls: ['./formcourstaff.component.scss']
})
export class FormcourstaffComponent {
  coursestform: FormGroup;
  courses: any[] = [];
  branch: any[]=[];
  staff: any[]=[]

  constructor(private _fb:FormBuilder, private _couStaffService: BranchService, private _dialogRef: MatDialogRef<FormcourstaffComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private http: HttpClient
  ){
    this.coursestform = this._fb.group({
      course: ['',Validators.required],
      
    branch: ['',Validators.required],
    staff: ['',Validators.required],
   

    });
  }
  ngOnInit(): void {
    (this.http.get('http://localhost:7081/api/admin/getAllBranches',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.branch = data;
      });
      (this.http.get('http://localhost:7081/api/admin/getCourseNames',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.courses = data;
      });
      (this.http.get('http://localhost:7081/api/admin/getStaffNames',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.staff = data;
      });
    this.coursestform.patchValue(this.data)  

  }
  onFormSubmit(){
    if (this.coursestform.valid) {
      if (this.data) {
        Swal.fire({
          title: 'Are you sure you want to update course staff information?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._couStaffService.updatecourStaff(this.data.id, this.coursestform.value).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Course staff information has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating course staff information:', err);
                Swal.fire('Error!', 'An error occurred while updating the information.', 'error');
              }
            });
          }
        });
      }
      else{
        
   this._couStaffService.addcourStaff(this.coursestform.value).subscribe({
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
