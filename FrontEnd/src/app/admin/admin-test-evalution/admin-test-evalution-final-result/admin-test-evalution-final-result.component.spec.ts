import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTestEvalutionFinalResultComponent } from './admin-test-evalution-final-result.component';

describe('AdminTestEvalutionFinalResultComponent', () => {
  let component: AdminTestEvalutionFinalResultComponent;
  let fixture: ComponentFixture<AdminTestEvalutionFinalResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminTestEvalutionFinalResultComponent]
    });
    fixture = TestBed.createComponent(AdminTestEvalutionFinalResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
