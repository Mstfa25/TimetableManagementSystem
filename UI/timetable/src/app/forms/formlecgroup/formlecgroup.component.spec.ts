import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormlecgroupComponent } from './formlecgroup.component';

describe('FormlecgroupComponent', () => {
  let component: FormlecgroupComponent;
  let fixture: ComponentFixture<FormlecgroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormlecgroupComponent]
    });
    fixture = TestBed.createComponent(FormlecgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
