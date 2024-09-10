import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSubjectTestAddComponent } from './admin-subject-test-add.component';

describe('AdminSubjectTestAddComponent', () => {
  let component: AdminSubjectTestAddComponent;
  let fixture: ComponentFixture<AdminSubjectTestAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminSubjectTestAddComponent]
    });
    fixture = TestBed.createComponent(AdminSubjectTestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
