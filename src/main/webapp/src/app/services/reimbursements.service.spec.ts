import { TestBed, inject } from '@angular/core/testing';

import { ReimbursementsService } from './reimbursements.service';

describe('ReimbursementsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReimbursementsService]
    });
  });

  it('should be created', inject([ReimbursementsService], (service: ReimbursementsService) => {
    expect(service).toBeTruthy();
  }));
});
