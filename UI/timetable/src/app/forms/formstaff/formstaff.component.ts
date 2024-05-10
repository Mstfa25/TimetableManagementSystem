import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { isNumber } from '@ng-bootstrap/ng-bootstrap/util/util';
import { Observable } from 'rxjs';
import { BranchService } from 'src/app/core/services/branch.service';

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
        this._staffService.updateStaff(staff).subscribe({
          next: (val: any) => {

            alert('Staff Update Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)
          }
        })
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
