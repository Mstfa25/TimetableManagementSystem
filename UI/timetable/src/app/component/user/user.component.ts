import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserService } from 'src/app/core/services/user.service';
import { FormuserComponent } from 'src/app/forms/formuser/formuser.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
  displayedColumns: string[] = ['sname', 'branch', 'role', 'username', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private _dialog: MatDialog, private _userservice: UserService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }
  ngOnInit(): void {
    this.getUser();
  }
  openAddEditUser() {
    const dialogRef = this._dialog.open(FormuserComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getUser();
        }
      }
    })
  }
  getUser() {
    this._userservice.getUserList().subscribe({
      next: (res) => {
        console.log(res);
        const displayedData = res.map((data: {
          id: number;
          username: any;
          password: any;
          role: number;
          roleName: any;
          staff: {
            name: any; id: number;
            branch: { name: any; id: number; },
            type: { name: any; id: number; }
          };
        }) => ({
          sname: data.staff.name,
          branch: data.staff.branch.name,
          role: data.roleName,
          username: data.username,
          sId: data.id,
          rId: data.role,
          bId: data.staff.branch.id
          // 'action': This value is not present in the server response
        }));
        this.dataSource = new MatTableDataSource(displayedData);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log
    })
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  deleteUser(id: number) {
    const data = { id: id }
    this._userservice.deleteUser(data).subscribe({
      next: (res) => {
        alert('Deleted !')
        this.getUser();
      },
      error: console.log
    })
  }
  openEditUser(data: any) {
    const dialogRef = this._dialog.open(FormuserComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getUser();
        }
      }
    })

  }
}
