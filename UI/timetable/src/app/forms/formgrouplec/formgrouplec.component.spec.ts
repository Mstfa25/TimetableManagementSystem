import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormgrouplecComponent } from './formgrouplec.component';

describe('FormgrouplecComponent', () => {
  let component: FormgrouplecComponent;
  let fixture: ComponentFixture<FormgrouplecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormgrouplecComponent]
    });
    fixture = TestBed.createComponent(FormgrouplecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
