import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieScreenComponent } from './movie-screen.component';

describe('MovieScreenComponent', () => {
  let component: MovieScreenComponent;
  let fixture: ComponentFixture<MovieScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieScreenComponent]
    });
    fixture = TestBed.createComponent(MovieScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
