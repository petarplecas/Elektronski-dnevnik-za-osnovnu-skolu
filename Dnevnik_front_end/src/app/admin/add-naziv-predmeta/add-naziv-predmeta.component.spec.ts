import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNazivPredmetaComponent } from './add-naziv-predmeta.component';

describe('AddNazivPredmetaComponent', () => {
  let component: AddNazivPredmetaComponent;
  let fixture: ComponentFixture<AddNazivPredmetaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNazivPredmetaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNazivPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
