import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsClaimsComponent } from './details-claims.component';

describe('DetailsClaimsComponent', () => {
  let component: DetailsClaimsComponent;
  let fixture: ComponentFixture<DetailsClaimsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailsClaimsComponent]
    });
    fixture = TestBed.createComponent(DetailsClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
