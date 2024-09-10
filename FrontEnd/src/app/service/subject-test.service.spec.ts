import { TestBed } from '@angular/core/testing';

import { SubjectTestService } from './subject-test.service';

describe('SubjectTestService', () => {
  let service: SubjectTestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubjectTestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
