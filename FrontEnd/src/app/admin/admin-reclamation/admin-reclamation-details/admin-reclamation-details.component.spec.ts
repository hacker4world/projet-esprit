import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminReclamationDetailsComponent } from './admin-reclamation-details.component';

describe('AdminReclamationDetailsComponent', () => {
  let component: AdminReclamationDetailsComponent;
  let fixture: ComponentFixture<AdminReclamationDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminReclamationDetailsComponent]
    });
    fixture = TestBed.createComponent(AdminReclamationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
