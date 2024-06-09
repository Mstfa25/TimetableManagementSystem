import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavsecComponent } from './navsec.component';

describe('NavsecComponent', () => {
  let component: NavsecComponent;
  let fixture: ComponentFixture<NavsecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavsecComponent]
    });
    fixture = TestBed.createComponent(NavsecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
