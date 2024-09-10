import { TestBed } from '@angular/core/testing';

import { TestEvalutionService } from './test-evalution.service';

describe('TestEvalutionService', () => {
  let service: TestEvalutionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestEvalutionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
