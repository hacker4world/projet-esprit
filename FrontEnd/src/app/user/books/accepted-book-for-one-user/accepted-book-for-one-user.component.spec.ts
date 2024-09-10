import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedBookForOneUserComponent } from './accepted-book-for-one-user.component';

describe('AcceptedBookForOneUserComponent', () => {
  let component: AcceptedBookForOneUserComponent;
  let fixture: ComponentFixture<AcceptedBookForOneUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AcceptedBookForOneUserComponent]
    });
    fixture = TestBed.createComponent(AcceptedBookForOneUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
