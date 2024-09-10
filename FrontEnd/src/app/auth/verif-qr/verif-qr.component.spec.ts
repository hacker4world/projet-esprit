import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifQrComponent } from './verif-qr.component';

describe('VerifQrComponent', () => {
  let component: VerifQrComponent;
  let fixture: ComponentFixture<VerifQrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerifQrComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerifQrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
