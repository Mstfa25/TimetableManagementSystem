import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { DayPilot, DayPilotCalendarComponent } from '@daypilot/daypilot-lite-angular';
import { TableService } from 'src/app/core/services/table.service';
import { HistoryRecord, UndoService } from 'src/app/core/services/undo.service';
import EventData = DayPilot.EventData;
import { AuthService } from 'src/app/core/services/auth.service';
@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.scss']
})
export class TimetableComponent implements AfterViewInit {

  @ViewChild("calendar")
  calendar!: DayPilotCalendarComponent;

  config: DayPilot.CalendarConfig = {
    viewType: "Week",
    headerDateFormat:"dddd",
    timeRangeSelectedHandling: "Enabled",
    businessBeginsHour: 8, 
    businessEndsHour: 18,
   
    durationBarVisible: false,

    /*onTimeRangeSelected: async args => {
      const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
      const calendar = args.control;
      calendar.clearSelection();
      if (modal.canceled) {
        return;
      }
      const data = {
        start: args.start,
        end: args.end,
        id: DayPilot.guid(),
        resource: args.resource,
        text: modal.result
      };
      calendar.events.add(data);
      this.undoService.add(data, "Event created.");
    },

    onEventMoved: args => {
      this.undoService.update(args.e.data, "Event moved.");
    },
    onEventResized: args => {
      this.undoService.update(args.e.data, "Event resized.");
    },

    eventDeleteHandling: "Update",
    onEventDeleted: args => {
      this.undoService.remove(args.e.data, "Event deleted.");
    }*/
  };



durationFromMinutes(minutes: number): DayPilot.Duration {
  return DayPilot.Duration.ofMinutes(minutes);
}

  constructor(private ds: TableService, public undoService: UndoService, private auth:AuthService) {
    auth.loggedIn.next(true);
  }

  ngAfterViewInit(): void {
    const from = this.calendar.control.visibleStart();
    const to = this.calendar.control.visibleEnd();
    this.ds.getEvents(from, to).subscribe(events => {
      this.calendar.control.update({events});
      this.undoService.initialize(events);
    });
  }

  undoButtonClick(): void {
    let record: HistoryRecord = this.undoService.undo();

    switch (record.type) {
      case "add":
        // added, need to delete now
        this.calendar.control.events.remove(record.id);
        break;
      case "remove":
        // removed, need to add now
        this.calendar.control.events.add(<EventData>record.previous);
        break;
      case "update":
        // updated
        this.calendar.control.events.update(<EventData>record.previous);
        break;
    }
  }

  redoButtonClick(): void {
    const record: HistoryRecord = this.undoService.redo();

    switch (record.type) {
      case "add":
        // added, need to re-add
        this.calendar.control.events.add(<EventData>record.current);
        break;
      case "remove":
        // removed, need to remove again
        this.calendar.control.events.remove(record.id);
        break;
      case "update":
        // updated, use the new version
        this.calendar.control.events.update(<EventData>record.current);
        break;
    }
  }

  }
  
