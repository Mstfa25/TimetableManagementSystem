import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSelectChange } from '@angular/material/select';
import { Observable } from 'rxjs';
import { Branch } from 'src/app/core/interfaces/branch';
import { LecgroupService } from 'src/app/core/services/lecgroup.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formsecgroup',
  templateUrl: './formsecgroup.component.html',
  styleUrls: ['./formsecgroup.component.scss']
})
export class FormsecgroupComponent {
 
  secform: FormGroup;
  lecname: any[] = [];
  grouplecname: any[] = [];
  groupbranname: any[] = [];

 
  constructor(private _fb:FormBuilder, private _secService: LecgroupService, private _dialogRef: MatDialogRef<FormsecgroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private http: HttpClient
  ){
    this.secform = this._fb.group({
      namesec: ['',Validators.required],
      lecname: ['',Validators.required],
      grouplecname: ['',Validators.required],
      groupbranname: ['',Validators.required]

    });
  }
  ngOnInit(): void {
    (this.http.get('http://localhost:7081/api/admin/getAllBranches',{withCredentials:true}) as Observable<any[]>)
    .subscribe((data: any[]) => {
      this.groupbranname = data;
    });
    (this.http.get('http://localhost:7081/api/admin/getAllLectuerGroupsWithLecgroups',{withCredentials:true}) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.lecname = data;
      });
    this.secform.patchValue(this.data)  

  }

  setLecGroups(event: MatSelectChange) {
    const selectedValue = event.value;
    console.log(this.lecname)
    console.log(selectedValue.lecgroups)
    const selectedObject = this.lecname.find((item: any) => item.name === selectedValue.name);
    if (selectedObject) {
        this.grouplecname=selectedValue.lecgroups;
    }
}

  onFormSubmit(){
    if (this.secform.valid) {
      if (this.data) {
        Swal.fire({
          title: 'Are you sure you want to update this section group?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._secService.updatesecgroup(this.data.id, this.secform.value).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Section group has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating section group:', err);
                Swal.fire('Error!', 'An error occurred while updating the group.', 'error');
              }
            });
          }
        });
      }    
        else{
        
   this._secService.addsecgroup(this.secform.value).subscribe({
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
