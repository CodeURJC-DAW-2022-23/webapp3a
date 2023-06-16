import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorScreenComponent } from './director-screen.component';

describe('DirectorScreenComponent', () => {
  let component: DirectorScreenComponent;
  let fixture: ComponentFixture<DirectorScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DirectorScreenComponent]
    });
    fixture = TestBed.createComponent(DirectorScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
