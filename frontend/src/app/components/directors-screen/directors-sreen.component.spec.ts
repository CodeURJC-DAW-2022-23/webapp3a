import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorsScreenComponent } from './directors-sreen.component';

describe('DirectorsScreenComponent', () => {
  let component: DirectorsScreenComponent;
  let fixture: ComponentFixture<DirectorsScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DirectorsScreenComponent]
    });
    fixture = TestBed.createComponent(DirectorsScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
