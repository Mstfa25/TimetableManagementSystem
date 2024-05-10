import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacultyComponent } from './FacultyComponent';

describe('FacultyComponent', () => {
  let component: FacultyComponent;
  let fixture: ComponentFixture<FacultyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FacultyComponent]
    });
    fixture = TestBed.createComponent(FacultyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
