import { TestBed } from '@angular/core/testing';

import { CategoryBookService } from './category-book.service';

describe('CategoryBookService', () => {
  let service: CategoryBookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryBookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
