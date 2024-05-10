import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LecgroupComponent } from './lecgroup.component';

describe('LecgroupComponent', () => {
  let component: LecgroupComponent;
  let fixture: ComponentFixture<LecgroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LecgroupComponent]
    });
    fixture = TestBed.createComponent(LecgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
