import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClassroomAssignStudentComponent } from './admin-classroom-assign-student.component';

describe('AdminClassroomAssignStudentComponent', () => {
  let component: AdminClassroomAssignStudentComponent;
  let fixture: ComponentFixture<AdminClassroomAssignStudentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminClassroomAssignStudentComponent]
    });
    fixture = TestBed.createComponent(AdminClassroomAssignStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
