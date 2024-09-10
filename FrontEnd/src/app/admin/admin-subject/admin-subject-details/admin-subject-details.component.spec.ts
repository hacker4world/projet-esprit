import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSubjectDetailsComponent } from './admin-subject-details.component';

describe('AdminSubjectDetailsComponent', () => {
  let component: AdminSubjectDetailsComponent;
  let fixture: ComponentFixture<AdminSubjectDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminSubjectDetailsComponent]
    });
    fixture = TestBed.createComponent(AdminSubjectDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
