import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormtimeroomComponent } from './formtimeroom.component';

describe('FormtimeroomComponent', () => {
  let component: FormtimeroomComponent;
  let fixture: ComponentFixture<FormtimeroomComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormtimeroomComponent]
    });
    fixture = TestBed.createComponent(FormtimeroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
