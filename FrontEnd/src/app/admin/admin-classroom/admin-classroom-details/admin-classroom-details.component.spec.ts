import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClassroomDetailsComponent } from './admin-classroom-details.component';

describe('AdminClassroomDetailsComponent', () => {
  let component: AdminClassroomDetailsComponent;
  let fixture: ComponentFixture<AdminClassroomDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminClassroomDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminClassroomDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
