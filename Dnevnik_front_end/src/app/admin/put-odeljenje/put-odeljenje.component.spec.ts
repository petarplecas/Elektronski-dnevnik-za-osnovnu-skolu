import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PutOdeljenjeComponent } from './put-odeljenje.component';

describe('PutOdeljenjeComponent', () => {
  let component: PutOdeljenjeComponent;
  let fixture: ComponentFixture<PutOdeljenjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PutOdeljenjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PutOdeljenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
