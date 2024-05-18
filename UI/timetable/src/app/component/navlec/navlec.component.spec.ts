import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavlecComponent } from './navlec.component';

describe('NavlecComponent', () => {
  let component: NavlecComponent;
  let fixture: ComponentFixture<NavlecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavlecComponent]
    });
    fixture = TestBed.createComponent(NavlecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
