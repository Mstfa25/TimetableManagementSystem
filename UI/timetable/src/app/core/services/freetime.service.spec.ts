import { TestBed } from '@angular/core/testing';

import { FreetimeService } from './freetime.service';

describe('FreetimeService', () => {
  let service: FreetimeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FreetimeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
