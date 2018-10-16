import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetUcenikeAdminaComponent } from './get-ucenike-admina.component';

describe('GetUcenikeAdminaComponent', () => {
  let component: GetUcenikeAdminaComponent;
  let fixture: ComponentFixture<GetUcenikeAdminaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetUcenikeAdminaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetUcenikeAdminaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
