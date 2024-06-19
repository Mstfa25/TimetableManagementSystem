import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { isNumber } from '@ng-bootstrap/ng-bootstrap/util/util';
import { Observable } from 'rxjs';
import { BranchService } from 'src/app/core/services/branch.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formstaff',
  templateUrl: './formstaff.component.html',
  styleUrls: ['./formstaff.component.scss']
})
export class FormstaffComponent {
  staffform: FormGroup;
  branchname: any[] = [
    ''
  ];
  typejop: any[] = [
    ''
 ];
  constructor(private _fb: FormBuilder,
    private _staffService: BranchService,
    private _dialogRef: MatDialogRef<FormstaffComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient
  ) {
    this.staffform = this._fb.group({
      jopname: ['', Validators.required],
      bname: ['', Validators.required],
      tjop: ['', Validators.required]
    });
  }
  ngOnInit(): void {
    this.staffform.patchValue(this.data);

    (this.http.get('http://localhost:7081/api/admin/getAllBranches',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.branchname = data;
      });

      (this.http.get('http://localhost:7081/api/admin/getJobTypes',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.typejop = data;
      });

  }

  onFormSubmit() {
    if (this.staffform.valid) {
      if (this.data) {
        // Extract staff information (maintain your existing logic)
        let optionname = this.staffform.get('jopname');
        let optionbranchid = this.staffform.get('bname');
        let optiontypeid = this.staffform.get('tjop');
        var name = '', branchid = 0, jopid = 0;
        if (optionname) name = optionname.value;
        if (optionbranchid) {
          branchid = isNaN(optionbranchid.value) ? this.getbranchDropdownId(optionbranchid.value) : optionbranchid.value;
        }
        if (optiontypeid) {
          jopid = isNaN(optiontypeid.value) ? this.gettypeDropdownId(optiontypeid.value) : optiontypeid.value;
        }
    
        const staff = {
          id: this.data.id,
          name: name,
          branch: { id: branchid },
          type: { id: jopid }
        };
    
        // Confirmation dialog with informative message
        Swal.fire({
          title: 'Are you sure you want to update this staff member?',
          text: "The updated information, including name, branch, and job type, will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._staffService.updateStaff(staff).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Staff member has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating staff member:', err);
                Swal.fire('Error!', 'An error occurred while updating the staff member.', 'error');
              }
            });
          }
        });
      }
      else {
        let optionname = this.staffform.get('jopname');
        let optionbranchid = this.staffform.get('bname');
        let optiontypeid = this.staffform.get('tjop');
        var name = '', branchid = 0, jopid = 0;
        if (optionname) name = optionname.value;
        if (optionbranchid) branchid = optionbranchid.value;
        if (optiontypeid) jopid = optiontypeid.value;
        const staff = { name: name,
           branch: { id: branchid },
           type: { id: jopid } }
        this._staffService.addStaff(staff).subscribe({
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

   getbranchDropdownId(name:string) {
    let item = this.branchname.find(item => item.name === name);
    return item ? item.id : null;
  }
  
  gettypeDropdownId(name:string) {
    let item = this.typejop.find(item => item.name === name);
    return item ? item.id : null;
  }
}
