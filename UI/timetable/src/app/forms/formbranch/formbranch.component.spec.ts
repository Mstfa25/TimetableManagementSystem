import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormbranchComponent } from './formbranch.component';

describe('FormbranchComponent', () => {
  let component: FormbranchComponent;
  let fixture: ComponentFixture<FormbranchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormbranchComponent]
    });
    fixture = TestBed.createComponent(FormbranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
