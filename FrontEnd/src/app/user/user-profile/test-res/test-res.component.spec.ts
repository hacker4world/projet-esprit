import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestResComponent } from './test-res.component';

describe('TestResComponent', () => {
  let component: TestResComponent;
  let fixture: ComponentFixture<TestResComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TestResComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TestResComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
