import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectiongroupComponent } from './sectiongroup.component';

describe('SectiongroupComponent', () => {
  let component: SectiongroupComponent;
  let fixture: ComponentFixture<SectiongroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectiongroupComponent]
    });
    fixture = TestBed.createComponent(SectiongroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
