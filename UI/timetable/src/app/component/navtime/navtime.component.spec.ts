import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavtimeComponent } from './navtime.component';

describe('NavtimeComponent', () => {
  let component: NavtimeComponent;
  let fixture: ComponentFixture<NavtimeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavtimeComponent]
    });
    fixture = TestBed.createComponent(NavtimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
