import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecgroupComponent } from './secgroup.component';

describe('SecgroupComponent', () => {
  let component: SecgroupComponent;
  let fixture: ComponentFixture<SecgroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SecgroupComponent]
    });
    fixture = TestBed.createComponent(SecgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
