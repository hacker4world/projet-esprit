import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClassroomListComponent } from './admin-classroom-list.component';

describe('AdminClassroomListComponent', () => {
  let component: AdminClassroomListComponent;
  let fixture: ComponentFixture<AdminClassroomListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminClassroomListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminClassroomListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
