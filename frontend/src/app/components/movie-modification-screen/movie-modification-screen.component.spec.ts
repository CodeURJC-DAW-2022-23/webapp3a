import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieModificationScreenComponent } from './movie-modification-screen.component';

describe('MovieModificationScreenComponent', () => {
  let component: MovieModificationScreenComponent;
  let fixture: ComponentFixture<MovieModificationScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieModificationScreenComponent]
    });
    fixture = TestBed.createComponent(MovieModificationScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
