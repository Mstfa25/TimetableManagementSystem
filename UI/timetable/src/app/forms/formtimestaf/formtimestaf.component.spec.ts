import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormtimestafComponent } from './formtimestaf.component';

describe('FormtimestafComponent', () => {
  let component: FormtimestafComponent;
  let fixture: ComponentFixture<FormtimestafComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormtimestafComponent]
    });
    fixture = TestBed.createComponent(FormtimestafComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
