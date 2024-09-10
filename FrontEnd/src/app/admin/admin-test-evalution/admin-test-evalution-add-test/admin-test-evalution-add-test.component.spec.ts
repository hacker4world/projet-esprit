import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTestEvalutionAddTestComponent } from './admin-test-evalution-add-test.component';

describe('AdminTestEvalutionAddTestComponent', () => {
  let component: AdminTestEvalutionAddTestComponent;
  let fixture: ComponentFixture<AdminTestEvalutionAddTestComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminTestEvalutionAddTestComponent]
    });
    fixture = TestBed.createComponent(AdminTestEvalutionAddTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
