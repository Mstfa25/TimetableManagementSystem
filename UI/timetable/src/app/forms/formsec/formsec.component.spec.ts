import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsecComponent } from './formsec.component';

describe('FormsecComponent', () => {
  let component: FormsecComponent;
  let fixture: ComponentFixture<FormsecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormsecComponent]
    });
    fixture = TestBed.createComponent(FormsecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
