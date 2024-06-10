import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesecComponent } from './coursesec.component';

describe('CoursesecComponent', () => {
  let component: CoursesecComponent;
  let fixture: ComponentFixture<CoursesecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoursesecComponent]
    });
    fixture = TestBed.createComponent(CoursesecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
