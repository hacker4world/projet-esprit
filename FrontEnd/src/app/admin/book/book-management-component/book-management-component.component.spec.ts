import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookManagementComponentComponent } from './book-management-component.component';

describe('BookManagementComponentComponent', () => {
  let component: BookManagementComponentComponent;
  let fixture: ComponentFixture<BookManagementComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookManagementComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookManagementComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
