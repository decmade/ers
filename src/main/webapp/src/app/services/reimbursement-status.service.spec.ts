import { TestBed, inject } from '@angular/core/testing';

import { ReimbursementStatusService } from './reimbursement-status.service';

describe('ReimbursementStatusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReimbursementStatusService]
    });
  });

  it('should be created', inject([ReimbursementStatusService], (service: ReimbursementStatusService) => {
    expect(service).toBeTruthy();
  }));
});
