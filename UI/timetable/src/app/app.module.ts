import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatSelectModule} from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatNativeDateModule} from '@angular/material/core';
import { AppComponent } from './app.component';
import { NavComponent } from './component/nav/nav.component';
import { HomeComponent } from './component/home/home.component';
import { BranchComponent } from './component/branch/branch.component';
import { FacultyComponent } from './component/faculty/FacultyComponent';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import { FormbranchComponent } from './forms/formbranch/formbranch.component';
import { LoginComponent } from './component/login/login.component';
import { RoomComponent } from './component/room/room.component';
import { FormroomComponent } from './forms/formroom/formroom.component';
import { StaffComponent } from './component/staff/staff.component';
import { FormstaffComponent } from './forms/formstaff/formstaff.component';
import { StudyitComponent } from './component/studyit/studyit.component';
import { SemsteritComponent } from './component/semsterit/semsterit.component';
import { FormfacultyComponent } from './forms/formfaculty/formfaculty.component';
import { FormstudyitComponent } from './forms/formstudyit/formstudyit.component';
import { FormsemsterComponent } from './forms/formsemster/formsemster.component';
import { UserComponent } from './component/user/user.component';
import { ChangepassComponent } from './component/changepass/changepass.component';
import { FormuserComponent } from './forms/formuser/formuser.component';
import { LecgroupComponent } from './component/lecgroup/lecgroup.component';
import { FormlecgroupComponent } from './forms/formlecgroup/formlecgroup.component';
import { FormgrouplecComponent } from './forms/formgrouplec/formgrouplec.component';
import { FormsecgroupComponent } from './forms/formsecgroup/formsecgroup.component';
import { CourseComponent } from './component/course/course.component';
import { FormcourseComponent } from './forms/formcourse/formcourse.component';
import { FormtimestafComponent } from './forms/formtimestaf/formtimestaf.component';
import { SectionComponent } from './component/section/section.component';
import { FreetimestafComponent } from './component/freetimestaf/freetimestaf.component';
import { FormsecComponent } from './forms/formsec/formsec.component';
import { CoursestaffComponent } from './component/coursestaff/coursestaff.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { FormcourstaffComponent } from './forms/formcourstaff/formcourstaff.component';
import { FormsecstafComponent } from './forms/formsecstaf/formsecstaf.component';
import { TablelecComponent } from './component/tablelec/tablelec.component';

import { FormtablesecComponent } from './forms/formtablesec/formtablesec.component';
import { TablesecComponent } from './component/tablesec/tablesec.component';
import { NavlecComponent } from './component/navlec/navlec.component';
import { NavtimeComponent } from './component/navtime/navtime.component';
import { GrouplecComponent } from './component/grouplec/grouplec.component';
import { SecgroupComponent } from './component/secgroup/secgroup.component';
import { FormtimeroomComponent } from './forms/formtimeroom/formtimeroom.component';
import { FreetimeroomComponent } from './component/freetimeroom/freetimeroom.component';
import { NavcourseComponent } from './component/navcourse/navcourse.component';
import { CoursesecComponent } from './component/coursesec/coursesec.component';
import { NavsecComponent } from './component/navsec/navsec.component';
import { SectiongroupComponent } from './component/sectiongroup/sectiongroup.component';
import { FormsecGroupComponent } from './forms/formsecstaf/formsec-group/formsec-group.component';


@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    BranchComponent,
    FacultyComponent,
    FormbranchComponent,
    LoginComponent,
    RoomComponent,
    FormroomComponent,
    StaffComponent,
    FormstaffComponent,
    StudyitComponent,
    SemsteritComponent,
    FormfacultyComponent,
    FormstudyitComponent,
    FormsemsterComponent,
    UserComponent,
    ChangepassComponent,
    FormuserComponent,
    LecgroupComponent,
    FormlecgroupComponent,
    FormgrouplecComponent,
    FormsecgroupComponent,
    CourseComponent,
    FormcourseComponent,
    SectionComponent,
    FormsecComponent,
    CoursestaffComponent,
    FormcourstaffComponent,
    FormsecstafComponent,
    TablelecComponent,

    FormtablesecComponent,
    TablesecComponent,
    NavlecComponent,
    GrouplecComponent,
    SecgroupComponent,
    FormtimestafComponent ,
    FreetimestafComponent,
    NavtimeComponent,
    FormtimeroomComponent,
    FreetimeroomComponent,
    NavcourseComponent,
    CoursesecComponent,
    NavsecComponent,
    SectiongroupComponent,
    FormsecGroupComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    MatCheckboxModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
