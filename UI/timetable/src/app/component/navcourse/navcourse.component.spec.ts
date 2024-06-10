import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavcourseComponent } from './navcourse.component';

describe('NavcourseComponent', () => {
  let component: NavcourseComponent;
  let fixture: ComponentFixture<NavcourseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavcourseComponent]
    });
    fixture = TestBed.createComponent(NavcourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
