import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClassroomCreateComponent } from './admin-classroom-create.component';

describe('AdminClassroomCreateComponent', () => {
  let component: AdminClassroomCreateComponent;
  let fixture: ComponentFixture<AdminClassroomCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminClassroomCreateComponent]
    });
    fixture = TestBed.createComponent(AdminClassroomCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
