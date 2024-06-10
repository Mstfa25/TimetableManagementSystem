import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TimetableComponent } from './timetable/timetable.component';
import { HttpClientModule } from '@angular/common/http';
import { DayPilotModule } from '@daypilot/daypilot-lite-angular';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { TableService } from 'src/app/core/services/table.service';
import { UndoService } from 'src/app/core/services/undo.service';



@NgModule({
  declarations: [
    TimetableComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    DayPilotModule,
    BrowserModule,
    FormsModule
  ],
  exports:[TimetableComponent],
  providers:[
    TableService,
    UndoService
  ]
})
export class TimetableModule { }
