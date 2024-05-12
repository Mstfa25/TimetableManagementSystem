import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablesecComponent } from './tablesec.component';

describe('TablesecComponent', () => {
  let component: TablesecComponent;
  let fixture: ComponentFixture<TablesecComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TablesecComponent]
    });
    fixture = TestBed.createComponent(TablesecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
