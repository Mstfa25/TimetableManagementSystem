import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsecGroupComponent } from './formsec-group.component';

describe('FormsecGroupComponent', () => {
  let component: FormsecGroupComponent;
  let fixture: ComponentFixture<FormsecGroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormsecGroupComponent]
    });
    fixture = TestBed.createComponent(FormsecGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
