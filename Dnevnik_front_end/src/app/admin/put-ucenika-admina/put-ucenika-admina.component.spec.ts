import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PutUcenikaAdminaComponent } from './put-ucenika-admina.component';

describe('PutUcenikaAdminaComponent', () => {
  let component: PutUcenikaAdminaComponent;
  let fixture: ComponentFixture<PutUcenikaAdminaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PutUcenikaAdminaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PutUcenikaAdminaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
