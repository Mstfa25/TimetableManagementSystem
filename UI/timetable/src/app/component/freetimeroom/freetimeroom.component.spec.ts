import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreetimeroomComponent } from './freetimeroom.component';

describe('FreetimeroomComponent', () => {
  let component: FreetimeroomComponent;
  let fixture: ComponentFixture<FreetimeroomComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FreetimeroomComponent]
    });
    fixture = TestBed.createComponent(FreetimeroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
