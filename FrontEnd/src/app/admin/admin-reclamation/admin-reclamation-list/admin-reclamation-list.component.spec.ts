import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminReclamationListComponent } from './admin-reclamation-list.component';

describe('AdminReclamationListComponent', () => {
  let component: AdminReclamationListComponent;
  let fixture: ComponentFixture<AdminReclamationListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminReclamationListComponent]
    });
    fixture = TestBed.createComponent(AdminReclamationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
