
import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { BranchService } from 'src/app/core/services/branch.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formroom',
  templateUrl: './formroom.component.html',
  styleUrls: ['./formroom.component.scss']
})
export class FormroomComponent implements OnInit {
  roomform: FormGroup;
  branchname: any[] = [

  ];
  typeroom: any[] = [

  ];
  constructor(private _fb: FormBuilder, private _roomService: BranchService, private _dialogRef: MatDialogRef<FormroomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient

  ) {
    this.roomform = this._fb.group({
      roomname: ['', Validators.required],
      capacity: ['', Validators.required],
      bname: ['', Validators.required],
      troom: ['', Validators.required]
    });
  }
  ngOnInit(): void {
    this.roomform.patchValue(this.data);

    (this.http.get('http://localhost:7081/api/admin/getAllBranches', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.branchname = data;
      });

    (this.http.get('http://localhost:7081/api/admin/getAllRoomType', { withCredentials: true }) as Observable<any[]>)
      .subscribe((data: any[]) => {
        this.typeroom = data;
      });

  }
  onFormSubmit() {
    if (this.roomform.valid) {
      if (this.data) {
        let optionname = this.roomform.get('roomname');
        let optionBranchId = this.roomform.get('bname');
        let optioncapacity = this.roomform.get('capacity');
        let optionroomType = this.roomform.get('troom');
        var branchid, name, roomtype, capacity;
        if (optionBranchId) { branchid = isNaN(optionBranchId.value) ? this.getbranchDropdownId(optionBranchId.value) : optionBranchId.value; }
        if (optioncapacity) { capacity = optioncapacity.value; }
        if (optionroomType) { roomtype = isNaN(optionroomType.value) ? this.gettypeDropdownId(optionroomType.value) : optionroomType.value; }
        if (optionname) name = optionname.value;
        const room = {
          id: this.data.id,
          name: name,
          branch: { id: branchid },
          capacity: capacity,
          roomtype: { id: roomtype }
        };
        Swal.fire({
          title: 'Are you sure you want to update this room?',
          text: "The updated information will be saved.",
          icon: 'info', // Informative icon
          showCancelButton: true,
          confirmButtonColor: '#1475CB',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, update it!'
        }).then((result) => {
          if (result.isConfirmed) {
            this._roomService.updateRoom(room).subscribe({
              next: (val: any) => {
                Swal.fire('Updated!', 'Room has been updated successfully.', 'success');
                this._dialogRef.close(true); // Assuming this closes the dialog after successful update
              },
              error: (err: any) => {
                console.error('Error updating room:', err);
                Swal.fire('Error!', 'An error occurred while updating the room.', 'error');
              }
            });
          }
        });
      }
      else {
        let optionname = this.roomform.get('roomname');
        let optionBranchId = this.roomform.get('bname');
        let optioncapacity = this.roomform.get('capacity');
        let optionroomType = this.roomform.get('troom');
        var branchid, name, roomtype, capacity;
        if (optionBranchId) { branchid = isNaN(optionBranchId.value) ? this.getbranchDropdownId(optionBranchId.value) : optionBranchId.value; }
        if (optioncapacity) { capacity = optioncapacity.value; }
        if (optionroomType) { roomtype = isNaN(optionroomType.value) ? this.gettypeDropdownId(optionroomType.value) : optionroomType.value; }
        if (optionname) name = optionname.value;
        const room = {
          name: name,
          branch: { id: branchid },
          capacity: capacity,
          roomtype: { id: roomtype }
        };
        this._roomService.addRoom(room).subscribe({
          next: (val: any) => {
            alert('Room Added Successfuly')
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err)
          }
        })
      }
    }
  }

  getbranchDropdownId(name: string) {
    let item = this.branchname.find(item => item.name === name);
    return item ? item.id : null;
  }

  gettypeDropdownId(name: string) {
    let item = this.typeroom.find(item => item.name === name);
    return item ? item.id : null;
  }


}
