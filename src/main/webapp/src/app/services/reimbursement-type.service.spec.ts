import { TestBed, inject } from '@angular/core/testing';

import { ReimbursementTypeService } from './reimbursement-type.service';

describe('ReimbursementTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReimbursementTypeService]
    });
  });

  it('should be created', inject([ReimbursementTypeService], (service: ReimbursementTypeService) => {
    expect(service).toBeTruthy();
  }));
});
