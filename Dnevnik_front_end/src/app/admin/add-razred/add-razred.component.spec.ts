import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRazredComponent } from './add-razred.component';

describe('AddRazredComponent', () => {
  let component: AddRazredComponent;
  let fixture: ComponentFixture<AddRazredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddRazredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRazredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
