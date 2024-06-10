import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DayPilot } from '@daypilot/daypilot-lite-angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TableService {

  events: any[] = [
    {
      id: "1",
      start: DayPilot.Date.today().addHours(8),
      end: DayPilot.Date.today().addHours(17),
      text: "Event "
    }
  ];

  constructor(private http : HttpClient){
  }

  getEvents(from: DayPilot.Date, to: DayPilot.Date): Observable<any[]> {
var evts = [{
    "id": 1,
    "staff": {
        "id": 11,
        "name": "mohamed Basiouny",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 2,
        "name": "AI Systems Design and Implementation",
        "code": "AI343",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 11,
        "name": "Asmalia"
    }, {
        "id": 6,
        "name": "Menoufia"
    }, {
        "id": 2,
        "name": "Fayoum"
    }, {
        "id": 3,
        "name": "Assuit"
    }, {
        "id": 13,
        "name": "Aswan"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 61,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 11,
            "name": null
        }
    }, {
        "id": 34,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 6,
            "name": null
        }
    }, {
        "id": 7,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 2,
            "name": null
        }
    }, {
        "id": 12,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 3,
            "name": null
        }
    }, {
        "id": 69,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 13,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 3,
        "name": "AI",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 0,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 2,
    "staff": {
        "id": 11,
        "name": "mohamed Basiouny",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 2,
        "name": "AI Systems Design and Implementation",
        "code": "AI343",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 11,
        "name": "Asmalia"
    }, {
        "id": 6,
        "name": "Menoufia"
    }, {
        "id": 2,
        "name": "Fayoum"
    }, {
        "id": 3,
        "name": "Assuit"
    }, {
        "id": 13,
        "name": "Aswan"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 61,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 11,
            "name": null
        }
    }, {
        "id": 34,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 6,
            "name": null
        }
    }, {
        "id": 7,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 2,
            "name": null
        }
    }, {
        "id": 12,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 3,
            "name": null
        }
    }, {
        "id": 69,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 13,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 3,
        "name": "AI",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 3,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 3,
    "staff": {
        "id": 2,
        "name": "Walaa ElHady",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 5,
        "name": "Computer Graphics",
        "code": "IT221",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 13,
        "name": "Aswan"
    }, {
        "id": 7,
        "name": "Qena"
    }, {
        "id": 3,
        "name": "Assuit"
    }, {
        "id": 10,
        "name": "Hurgada"
    }, {
        "id": 8,
        "name": "suhag"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 70,
        "name": "Lab 2",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 13,
            "name": null
        }
    }, {
        "id": 40,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 7,
            "name": null
        }
    }, {
        "id": 13,
        "name": "Lab 2",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 3,
            "name": null
        }
    }, {
        "id": 56,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 10,
            "name": null
        }
    }, {
        "id": 46,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 8,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 1,
        "name": "A&B",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 24,
        "name": "Room 2",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 0,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 4,
    "staff": {
        "id": 2,
        "name": "Walaa ElHady",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 5,
        "name": "Computer Graphics",
        "code": "IT221",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 13,
        "name": "Aswan"
    }, {
        "id": 7,
        "name": "Qena"
    }, {
        "id": 3,
        "name": "Assuit"
    }, {
        "id": 10,
        "name": "Hurgada"
    }, {
        "id": 8,
        "name": "suhag"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 70,
        "name": "Lab 2",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 13,
            "name": null
        }
    }, {
        "id": 40,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 7,
            "name": null
        }
    }, {
        "id": 13,
        "name": "Lab 2",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 3,
            "name": null
        }
    }, {
        "id": 56,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 10,
            "name": null
        }
    }, {
        "id": 46,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 8,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 1,
        "name": "A&B",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 24,
        "name": "Room 2",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 3,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 5,
    "staff": {
        "id": 2,
        "name": "Walaa ElHady",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 5,
        "name": "Computer Graphics",
        "code": "IT221",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 4,
        "name": "Alex"
    }, {
        "id": 9,
        "name": "Beni Suef"
    }, {
        "id": 2,
        "name": "Fayoum"
    }, {
        "id": 6,
        "name": "Menoufia"
    }, {
        "id": 11,
        "name": "Asmalia"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 21,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 4,
            "name": null
        }
    }, {
        "id": 50,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 9,
            "name": null
        }
    }, {
        "id": 7,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 2,
            "name": null
        }
    }, {
        "id": 34,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 6,
            "name": null
        }
    }, {
        "id": 61,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 11,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 2,
        "name": "C&D",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 0,
    "startingTime": 9,
    "endingTime": 10,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 6,
    "staff": {
        "id": 2,
        "name": "Walaa ElHady",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 5,
        "name": "Computer Graphics",
        "code": "IT221",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 4,
        "name": "Alex"
    }, {
        "id": 9,
        "name": "Beni Suef"
    }, {
        "id": 2,
        "name": "Fayoum"
    }, {
        "id": 6,
        "name": "Menoufia"
    }, {
        "id": 11,
        "name": "Asmalia"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 21,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 4,
            "name": null
        }
    }, {
        "id": 50,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 9,
            "name": null
        }
    }, {
        "id": 7,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 2,
            "name": null
        }
    }, {
        "id": 34,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 6,
            "name": null
        }
    }, {
        "id": 61,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 11,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 2,
        "name": "C&D",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 3,
    "startingTime": 9,
    "endingTime": 10,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 7,
    "staff": {
        "id": 7,
        "name": "Mayar Ali",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 1,
        "name": "Advanced Software Engineering",
        "code": "CS344",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 13,
        "name": "Aswan"
    }, {
        "id": 7,
        "name": "Qena"
    }, {
        "id": 3,
        "name": "Assuit"
    }, {
        "id": 10,
        "name": "Hurgada"
    }, {
        "id": 8,
        "name": "suhag"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 69,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 13,
            "name": null
        }
    }, {
        "id": 40,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 7,
            "name": null
        }
    }, {
        "id": 12,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 3,
            "name": null
        }
    }, {
        "id": 56,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 10,
            "name": null
        }
    }, {
        "id": 46,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 8,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 1,
        "name": "A&B",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 1,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 8,
    "staff": {
        "id": 7,
        "name": "Mayar Ali",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 1,
        "name": "Advanced Software Engineering",
        "code": "CS344",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 13,
        "name": "Aswan"
    }, {
        "id": 7,
        "name": "Qena"
    }, {
        "id": 3,
        "name": "Assuit"
    }, {
        "id": 10,
        "name": "Hurgada"
    }, {
        "id": 8,
        "name": "suhag"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 69,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 13,
            "name": null
        }
    }, {
        "id": 40,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 7,
            "name": null
        }
    }, {
        "id": 12,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 3,
            "name": null
        }
    }, {
        "id": 56,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 10,
            "name": null
        }
    }, {
        "id": 46,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 8,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 1,
        "name": "A&B",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 4,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 9,
    "staff": {
        "id": 7,
        "name": "Mayar Ali",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 1,
        "name": "Advanced Software Engineering",
        "code": "CS344",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 4,
        "name": "Alex"
    }, {
        "id": 9,
        "name": "Beni Suef"
    }, {
        "id": 2,
        "name": "Fayoum"
    }, {
        "id": 6,
        "name": "Menoufia"
    }, {
        "id": 11,
        "name": "Asmalia"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 21,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 4,
            "name": null
        }
    }, {
        "id": 50,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 9,
            "name": null
        }
    }, {
        "id": 7,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 2,
            "name": null
        }
    }, {
        "id": 34,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 6,
            "name": null
        }
    }, {
        "id": 61,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 11,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 2,
        "name": "C&D",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 1,
    "startingTime": 9,
    "endingTime": 10,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 10,
    "staff": {
        "id": 7,
        "name": "Mayar Ali",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 1,
        "name": "Advanced Software Engineering",
        "code": "CS344",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 4,
        "name": "Alex"
    }, {
        "id": 9,
        "name": "Beni Suef"
    }, {
        "id": 2,
        "name": "Fayoum"
    }, {
        "id": 6,
        "name": "Menoufia"
    }, {
        "id": 11,
        "name": "Asmalia"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 21,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 4,
            "name": null
        }
    }, {
        "id": 50,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 9,
            "name": null
        }
    }, {
        "id": 7,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 2,
            "name": null
        }
    }, {
        "id": 34,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 6,
            "name": null
        }
    }, {
        "id": 61,
        "name": "Lab 1",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 11,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 2,
        "name": "C&D",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 23,
        "name": "Room 1",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 4,
    "startingTime": 9,
    "endingTime": 10,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 11,
    "staff": {
        "id": 5,
        "name": "Yasser Abd Elhameed",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 3,
        "name": "Algorithms and Data Structures",
        "code": "SWE206",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 8,
        "name": "suhag"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 47,
        "name": "Lab 2",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 8,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 7,
        "name": "VCR4",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 24,
        "name": "Room 2",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 1,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}, {
    "id": 12,
    "staff": {
        "id": 5,
        "name": "Yasser Abd Elhameed",
        "type": null,
        "branch": null
    },
    "course": {
        "id": 3,
        "name": "Algorithms and Data Structures",
        "code": "SWE206",
        "labHours": 0,
        "lectureHours": 0,
        "lectuerGoup": {
            "id": 0,
            "name": null
        },
        "group": {
            "id": 0,
            "name": null
        },
        "semester": {
            "id": 0,
            "number": 0,
            "studyPlan": {
                "id": 0,
                "name": null,
                "faculty": {
                    "id": 0,
                    "name": null
                }
            }
        },
        "studyPlan": {
            "id": 0,
            "name": null,
            "faculty": {
                "id": 0,
                "name": null
            }
        },
        "faculty": {
            "id": 0,
            "name": null
        }
    },
    "branchs": [{
        "id": 8,
        "name": "suhag"
    }],
    "hostingBranch": {
        "id": 5,
        "name": "Ain Shams"
    },
    "rooms": [{
        "id": 47,
        "name": "Lab 2",
        "capacity": 0,
        "roomtype": {
            "id": 0,
            "name": null
        },
        "branch": {
            "id": 8,
            "name": null
        }
    }],
    "lecGroup": {
        "id": 7,
        "name": "VCR4",
        "branchs": null,
        "lectuerGoup": null
    },
    "hostingRoom": {
        "id": 24,
        "name": "Room 2",
        "capacity": 0,
        "roomtype": null,
        "branch": null
    },
    "day": 4,
    "startingTime": 8,
    "endingTime": 9,
    "sectionGroupName": null,
    "timetable": null
}];
this.events = evts.map(function(item){return  {id:item.id,text:item.course.name + " - "+item.staff.name,day:DayPilot.Date.today().addDays(item.day),start:DayPilot.Date.today().addDays(item.day).addHours(item.startingTime),end:DayPilot.Date.today().addDays(item.day).addHours(item.endingTime)}});
    // simulating an HTTP request
    return new Observable(observer => {
      setTimeout(() => {
        observer.next(this.events);
      }, 200);
    });

    // return this.http.get("/api/events?from=" + from.toString() + "&to=" + to.toString());
  }

}


