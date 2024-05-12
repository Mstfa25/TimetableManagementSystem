import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormtablelecComponent } from './formtablelec.component';

describe('FormtablelecComponent', () => {
  let component: FormtablelecComponent;
  let fixture: ComponentFixture<FormtablelecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormtablelecComponent]
    });
    fixture = TestBed.createComponent(FormtablelecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
