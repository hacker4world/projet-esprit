import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClassroomModifyComponent } from './admin-classroom-modify.component';

describe('AdminClassroomModifyComponent', () => {
  let component: AdminClassroomModifyComponent;
  let fixture: ComponentFixture<AdminClassroomModifyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminClassroomModifyComponent]
    });
    fixture = TestBed.createComponent(AdminClassroomModifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
