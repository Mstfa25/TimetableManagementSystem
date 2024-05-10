import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormroomComponent } from './formroom.component';

describe('FormroomComponent', () => {
  let component: FormroomComponent;
  let fixture: ComponentFixture<FormroomComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormroomComponent]
    });
    fixture = TestBed.createComponent(FormroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
