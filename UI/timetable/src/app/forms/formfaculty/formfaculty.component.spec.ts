import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormfacultyComponent } from './formfaculty.component';

describe('FormfacultyComponent', () => {
  let component: FormfacultyComponent;
  let fixture: ComponentFixture<FormfacultyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormfacultyComponent]
    });
    fixture = TestBed.createComponent(FormfacultyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
