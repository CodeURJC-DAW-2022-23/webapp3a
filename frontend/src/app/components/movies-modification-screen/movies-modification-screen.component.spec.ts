import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviesModificationScreenComponent } from './movies-modification-screen.component';

describe('MoviesModificationScreenComponent', () => {
  let component: MoviesModificationScreenComponent;
  let fixture: ComponentFixture<MoviesModificationScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MoviesModificationScreenComponent]
    });
    fixture = TestBed.createComponent(MoviesModificationScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
