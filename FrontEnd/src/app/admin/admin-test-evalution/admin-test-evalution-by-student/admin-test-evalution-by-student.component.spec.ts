import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTestEvalutionByStudentComponent } from './admin-test-evalution-by-student.component';

describe('AdminTestEvalutionByStudentComponent', () => {
  let component: AdminTestEvalutionByStudentComponent;
  let fixture: ComponentFixture<AdminTestEvalutionByStudentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminTestEvalutionByStudentComponent]
    });
    fixture = TestBed.createComponent(AdminTestEvalutionByStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
