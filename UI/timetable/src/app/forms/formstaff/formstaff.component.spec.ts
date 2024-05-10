import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormstaffComponent } from './formstaff.component';

describe('FormstaffComponent', () => {
  let component: FormstaffComponent;
  let fixture: ComponentFixture<FormstaffComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormstaffComponent]
    });
    fixture = TestBed.createComponent(FormstaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
