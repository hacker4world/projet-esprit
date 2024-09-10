import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSubjectCreateComponent } from './admin-subject-create.component';

describe('AdminSubjectCreateComponent', () => {
  let component: AdminSubjectCreateComponent;
  let fixture: ComponentFixture<AdminSubjectCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminSubjectCreateComponent]
    });
    fixture = TestBed.createComponent(AdminSubjectCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
