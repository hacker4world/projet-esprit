import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClamisComponent } from './clamis.component';

describe('ClamisComponent', () => {
  let component: ClamisComponent;
  let fixture: ComponentFixture<ClamisComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClamisComponent]
    });
    fixture = TestBed.createComponent(ClamisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
