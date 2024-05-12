import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablelecComponent } from './tablelec.component';

describe('TablelecComponent', () => {
  let component: TablelecComponent;
  let fixture: ComponentFixture<TablelecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TablelecComponent]
    });
    fixture = TestBed.createComponent(TablelecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
