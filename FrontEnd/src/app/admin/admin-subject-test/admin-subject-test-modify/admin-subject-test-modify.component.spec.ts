import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSubjectTestModifyComponent } from './admin-subject-test-modify.component';

describe('AdminSubjectTestModifyComponent', () => {
  let component: AdminSubjectTestModifyComponent;
  let fixture: ComponentFixture<AdminSubjectTestModifyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminSubjectTestModifyComponent]
    });
    fixture = TestBed.createComponent(AdminSubjectTestModifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
