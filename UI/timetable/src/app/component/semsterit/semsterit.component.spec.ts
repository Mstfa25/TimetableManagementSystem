import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SemsteritComponent } from './semsterit.component';

describe('SemsteritComponent', () => {
  let component: SemsteritComponent;
  let fixture: ComponentFixture<SemsteritComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SemsteritComponent]
    });
    fixture = TestBed.createComponent(SemsteritComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
