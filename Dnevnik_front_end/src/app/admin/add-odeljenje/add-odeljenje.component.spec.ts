import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOdeljenjeComponent } from './add-odeljenje.component';

describe('AddOdeljenjeComponent', () => {
  let component: AddOdeljenjeComponent;
  let fixture: ComponentFixture<AddOdeljenjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddOdeljenjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOdeljenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
