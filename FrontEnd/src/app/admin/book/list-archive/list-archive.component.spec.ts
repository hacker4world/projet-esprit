import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListArchiveComponent } from './list-archive.component';

describe('ListArchiveComponent', () => {
  let component: ListArchiveComponent;
  let fixture: ComponentFixture<ListArchiveComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListArchiveComponent]
    });
    fixture = TestBed.createComponent(ListArchiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
