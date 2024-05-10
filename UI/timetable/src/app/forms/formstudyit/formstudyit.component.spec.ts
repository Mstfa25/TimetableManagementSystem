import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormstudyitComponent } from './formstudyit.component';

describe('FormstudyitComponent', () => {
  let component: FormstudyitComponent;
  let fixture: ComponentFixture<FormstudyitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormstudyitComponent]
    });
    fixture = TestBed.createComponent(FormstudyitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
