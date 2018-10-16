import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNasOdeComponent } from './add-nas-ode.component';

describe('AddNasOdeComponent', () => {
  let component: AddNasOdeComponent;
  let fixture: ComponentFixture<AddNasOdeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNasOdeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNasOdeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
