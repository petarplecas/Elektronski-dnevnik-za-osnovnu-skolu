import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetOdeljenjaComponent } from './get-odeljenja.component';

describe('GetOdeljenjaComponent', () => {
  let component: GetOdeljenjaComponent;
  let fixture: ComponentFixture<GetOdeljenjaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetOdeljenjaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetOdeljenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
