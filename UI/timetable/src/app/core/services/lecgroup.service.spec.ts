import { TestBed } from '@angular/core/testing';

import { LecgroupService } from './lecgroup.service';

describe('LecgroupService', () => {
  let service: LecgroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LecgroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
