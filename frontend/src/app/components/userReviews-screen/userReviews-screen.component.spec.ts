import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReviewsScreenComponent } from './userReviews-screen.component';

describe('UserReviewsScreenComponent', () => {
  let component: UserReviewsScreenComponent;
  let fixture: ComponentFixture<UserReviewsScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserReviewsScreenComponent]
    });
    fixture = TestBed.createComponent(UserReviewsScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
