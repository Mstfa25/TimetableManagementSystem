import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsemsterComponent } from './formsemster.component';

describe('FormsemsterComponent', () => {
  let component: FormsemsterComponent;
  let fixture: ComponentFixture<FormsemsterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormsemsterComponent]
    });
    fixture = TestBed.createComponent(FormsemsterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
