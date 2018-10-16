import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PutRazredComponent } from './put-razred.component';

describe('PutRazredComponent', () => {
  let component: PutRazredComponent;
  let fixture: ComponentFixture<PutRazredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PutRazredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PutRazredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
