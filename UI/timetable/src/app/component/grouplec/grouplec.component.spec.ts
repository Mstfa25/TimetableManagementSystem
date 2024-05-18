import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrouplecComponent } from './grouplec.component';

describe('GrouplecComponent', () => {
  let component: GrouplecComponent;
  let fixture: ComponentFixture<GrouplecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GrouplecComponent]
    });
    fixture = TestBed.createComponent(GrouplecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
