import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsecgroupComponent } from './formsecgroup.component';

describe('FormsecgroupComponent', () => {
  let component: FormsecgroupComponent;
  let fixture: ComponentFixture<FormsecgroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormsecgroupComponent]
    });
    fixture = TestBed.createComponent(FormsecgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
