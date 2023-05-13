import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadSubscribedBookComponent } from './read-subscribed-book.component';

describe('ReadSubscribedBookComponent', () => {
  let component: ReadSubscribedBookComponent;
  let fixture: ComponentFixture<ReadSubscribedBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReadSubscribedBookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReadSubscribedBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
