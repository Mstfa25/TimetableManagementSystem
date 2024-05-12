import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormtablesecComponent } from './formtablesec.component';

describe('FormtablesecComponent', () => {
  let component: FormtablesecComponent;
  let fixture: ComponentFixture<FormtablesecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormtablesecComponent]
    });
    fixture = TestBed.createComponent(FormtablesecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
