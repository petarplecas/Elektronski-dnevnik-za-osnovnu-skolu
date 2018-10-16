import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PutNasOdeComponent } from './put-nas-ode.component';

describe('PutNasOdeComponent', () => {
  let component: PutNasOdeComponent;
  let fixture: ComponentFixture<PutNasOdeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PutNasOdeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PutNasOdeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
