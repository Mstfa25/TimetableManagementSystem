import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreetimestafComponent } from './freetimestaf.component';

describe('FreetimestafComponent', () => {
  let component: FreetimestafComponent;
  let fixture: ComponentFixture<FreetimestafComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FreetimestafComponent]
    });
    fixture = TestBed.createComponent(FreetimestafComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
