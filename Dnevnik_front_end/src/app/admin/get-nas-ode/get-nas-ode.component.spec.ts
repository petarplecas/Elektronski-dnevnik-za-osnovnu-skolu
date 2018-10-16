import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetNasOdeComponent } from './get-nas-ode.component';

describe('GetNasOdeComponent', () => {
  let component: GetNasOdeComponent;
  let fixture: ComponentFixture<GetNasOdeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetNasOdeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetNasOdeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
