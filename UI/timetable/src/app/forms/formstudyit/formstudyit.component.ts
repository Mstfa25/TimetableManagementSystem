import { HttpClient } from '@angular/common/http';
import { faculty } from './../../core/interfaces/branch';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacultyService } from 'src/app/core/services/faculty.service';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formstudyit',
  templateUrl: './formstudyit.component.html',
  styleUrls: ['./formstudyit.component.scss']
})
export class FormstudyitComponent implements OnInit {
  studyform: FormGroup;
  faname: any[] = [
  ];

  constructor(private _fb: FormBuilder, private _studtService: FacultyService, private _dialogRef: MatDialogRef<FormstudyitComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient
  ) {
    this.studyform = this._fb.group({
      sname: ['', Validators.required],
      fname: ['', Validators.required],
      sId: [''],
      fId: ['']
    })}
  ngOnInit(): void {
    (this.http.get('http://localhost:7081/api/admin/getFacultys', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.faname = data;
      });
    this.studyform.patchValue(this.data);}
     onFormSubmit() {
      if (this.studyform.valid) {
        if (this.data) {
          const data = {
            id: this.studyform.get("sId")?.value,
            name: this.studyform.get("sname")?.value,
            faculty: {
              id: this.studyform.get("fname")?.value,
            }
          };
      
          // Informative confirmation message (consider tailoring to your context)
          Swal.fire({
            title: 'Are you sure you want to update this study?',
            text: 'The updated information, including name and faculty, will be saved.',
            icon: 'info', // Informative icon
            showCancelButton: true,
            confirmButtonColor: '#1475CB',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, update it!'
          }).then((result) => {
            if (result.isConfirmed) {
              this._studtService.updateStudyit(data).subscribe({
                next: (val: any) => {
                  Swal.fire('Updated!', 'Study has been updated successfully.', 'success');
                  this._dialogRef.close(true); // Assuming this closes the dialog after successful update
                },
                error: (err: any) => {
                  console.error('Error updating study:', err);
                  Swal.fire('Error!', 'An error occurred while updating the study.', 'error');
                }
              });
            }
          });
        }
      else {
        const data = {
          name: this.studyform.get("sname")?.value,
          faculty: {
            id: this.studyform.get("fname")?.value,
          }}
        console.log(this.studyform.get("fname")?.value);
        console.log(data);
        this._studtService.addStudyit(data).subscribe({
          next: (val: any) => {
            alert(' Added Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)}})}}}}
