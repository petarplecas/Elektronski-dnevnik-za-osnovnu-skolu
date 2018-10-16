import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetRazredComponent } from './get-razred.component';

describe('GetRazredComponent', () => {
  let component: GetRazredComponent;
  let fixture: ComponentFixture<GetRazredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetRazredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetRazredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
