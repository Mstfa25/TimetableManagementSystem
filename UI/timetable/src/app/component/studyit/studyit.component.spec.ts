import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyitComponent } from './studyit.component';

describe('StudyitComponent', () => {
  let component: StudyitComponent;
  let fixture: ComponentFixture<StudyitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudyitComponent]
    });
    fixture = TestBed.createComponent(StudyitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
