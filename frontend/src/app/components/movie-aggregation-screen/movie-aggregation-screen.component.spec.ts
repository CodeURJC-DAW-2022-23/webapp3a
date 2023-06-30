import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieAggregationScreenComponent } from './movie-aggregation-screen.component';

describe('MovieAggregationScreenComponent', () => {
  let component: MovieAggregationScreenComponent;
  let fixture: ComponentFixture<MovieAggregationScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieAggregationScreenComponent]
    });
    fixture = TestBed.createComponent(MovieAggregationScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
