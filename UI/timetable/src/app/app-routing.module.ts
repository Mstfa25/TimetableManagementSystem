import { ChangepassComponent } from './component/changepass/changepass.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { BranchComponent } from './component/branch/branch.component';
import { FacultyComponent } from './component/faculty/FacultyComponent';
import { LoginComponent } from './component/login/login.component';
import { RoomComponent } from './component/room/room.component';
import { StaffComponent } from './component/staff/staff.component';
import { SemsteritComponent } from './component/semsterit/semsterit.component';
import { StudyitComponent } from './component/studyit/studyit.component';
import { UserComponent } from './component/user/user.component';
import { LecgroupComponent } from './component/lecgroup/lecgroup.component';
import { CourseComponent } from './component/course/course.component';
import { SectionComponent } from './component/section/section.component';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent},
 { path: 'branch', component: BranchComponent},
 { path: 'room', component: RoomComponent},
 { path: 'staff', component: StaffComponent},
  { path: 'faculty', component: FacultyComponent},
  { path: 'studyit', component: StudyitComponent},
  { path: 'semsterit', component: SemsteritComponent},
  { path: 'section', component: SectionComponent},
  { path: 'course', component: CourseComponent},
  { path: 'lecgroup', component: LecgroupComponent},
  { path: 'user', component: UserComponent},
  { path: 'changepass', component: ChangepassComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
