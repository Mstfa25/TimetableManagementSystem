import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormcourseComponent } from './formcourse.component';

describe('FormcourseComponent', () => {
  let component: FormcourseComponent;
  let fixture: ComponentFixture<FormcourseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormcourseComponent]
    });
    fixture = TestBed.createComponent(FormcourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
