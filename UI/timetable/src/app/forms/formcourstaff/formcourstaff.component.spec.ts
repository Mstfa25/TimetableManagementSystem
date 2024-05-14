import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormcourstaffComponent } from './formcourstaff.component';

describe('FormcourstaffComponent', () => {
  let component: FormcourstaffComponent;
  let fixture: ComponentFixture<FormcourstaffComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormcourstaffComponent]
    });
    fixture = TestBed.createComponent(FormcourstaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
