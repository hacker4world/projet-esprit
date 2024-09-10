import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileFinalResultByStudentComponent } from './user-profile-final-result-by-student.component';

describe('UserProfileFinalResultByStudentComponent', () => {
  let component: UserProfileFinalResultByStudentComponent;
  let fixture: ComponentFixture<UserProfileFinalResultByStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserProfileFinalResultByStudentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserProfileFinalResultByStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
