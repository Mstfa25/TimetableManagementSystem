import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursestaffComponent } from './coursestaff.component';

describe('CoursestaffComponent', () => {
  let component: CoursestaffComponent;
  let fixture: ComponentFixture<CoursestaffComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoursestaffComponent]
    });
    fixture = TestBed.createComponent(CoursestaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
