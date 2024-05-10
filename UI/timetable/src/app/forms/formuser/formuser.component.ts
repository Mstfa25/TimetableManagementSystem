import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-formuser',
  templateUrl: './formuser.component.html',
  styleUrls: ['./formuser.component.scss']
})
export class FormuserComponent {
  userform: FormGroup;
  staffname: any[] = [];
  role: any[] = [];
  branch: any[] = [];
  constructor(private _fb: FormBuilder, private _userService: UserService, private _dialogRef: MatDialogRef<FormuserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient
  ) {
    this.userform = this._fb.group({
      sname: ['', Validators.required],
      role: ['', Validators.required],
      branch: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      sId: [''],
      rId: [''],
      bId: ['']
    });
  }
  ngOnInit(): void {
    this.userform.patchValue(this.data);
    (this.http.get('http://localhost:7081/api/admin/getAllStaffWithoutExistingUsers', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.staffname = data;
      });

    (this.http.get('http://localhost:7081/api/admin/getAllUserRoles', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.role = data;
      });

    (this.http.get('http://localhost:7081/api/admin/getAllBranches', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.branch = data;
      });
  }
  onFormSubmit() {
    if (this.userform.valid) {
      if (this.data) {
        const data = {
          id: this.userform.get('sId')?.value,
          username: this.userform.get('username')?.value,
          role: this.userform.get('rId')?.value,
        }
        console.log(data)
        this._userService.updateUser(data).subscribe({
          next: (val: any) => {
            alert('Update Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)
          }
        })
      }
      else {
        const data = {
          id: this.userform.get('sname')?.value,
          username: this.userform.get('username')?.value,
          password: this.userform.get('password')?.value,
          role: this.userform.get('role')?.value,
        }
        console.log(data);
        this._userService.addUser(data).subscribe({
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
}
