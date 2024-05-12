import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsecstafComponent } from './formsecstaf.component';

describe('FormsecstafComponent', () => {
  let component: FormsecstafComponent;
  let fixture: ComponentFixture<FormsecstafComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormsecstafComponent]
    });
    fixture = TestBed.createComponent(FormsecstafComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
