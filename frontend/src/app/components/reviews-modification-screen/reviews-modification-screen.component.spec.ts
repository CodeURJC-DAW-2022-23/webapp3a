import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewsModificationScreenComponent } from './reviews-modification-screen.component';

describe('ReviewsModificationScreenComponent', () => {
  let component: ReviewsModificationScreenComponent;
  let fixture: ComponentFixture<ReviewsModificationScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviewsModificationScreenComponent]
    });
    fixture = TestBed.createComponent(ReviewsModificationScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
