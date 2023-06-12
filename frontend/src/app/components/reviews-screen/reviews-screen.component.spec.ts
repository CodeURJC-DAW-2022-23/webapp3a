import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewsScreenComponent } from './reviews-screen.component';

describe('ReviewsScreenComponent', () => {
  let component: ReviewsScreenComponent;
  let fixture: ComponentFixture<ReviewsScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviewsScreenComponent]
    });
    fixture = TestBed.createComponent(ReviewsScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
