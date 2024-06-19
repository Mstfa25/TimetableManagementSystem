import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { BranchService } from 'src/app/core/services/branch.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formsecstaf',
  templateUrl: './formsecstaf.component.html',
  styleUrls: ['./formsecstaf.component.scss']
})
export class FormsecstafComponent {
secstafform: FormGroup;
  courses: any[] = [];
  branch: any[]=[];
  staff: any[]=[];


  constructor(private _fb:FormBuilder, private _secstafService: BranchService, private _dialogRef: MatDialogRef<FormsecstafComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private http: HttpClient
  ){
    this.secstafform = this._fb.group({
      course: ['',Validators.required],
      
    branch: ['',Validators.required],
    staff: ['',Validators.required],
    section: ['',Validators.required],

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
    this.secstafform.patchValue(this.data)  

  }
  onFormSubmit(){
    if (this.secstafform.valid) {
      if (this.data) {
        Swal.fire({
          title: 'Are you sure you want to update this staff member\'s section assignment?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._secstafService.updateSecStaff(this.data.id, this.secstafform.value).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Staff section assignment has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating staff section assignment:', err);
                Swal.fire('Error!', 'An error occurred while updating the assignment.', 'error');
              }
            });
          }
        });
      }
      
      else{
        
   this._secstafService.addSecStaff(this.secstafform.value).subscribe({
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
