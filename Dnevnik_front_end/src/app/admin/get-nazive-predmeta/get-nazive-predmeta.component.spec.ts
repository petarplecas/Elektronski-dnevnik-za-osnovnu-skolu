import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetNazivePredmetaComponent } from './get-nazive-predmeta.component';

describe('GetNazivePredmetaComponent', () => {
  let component: GetNazivePredmetaComponent;
  let fixture: ComponentFixture<GetNazivePredmetaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetNazivePredmetaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetNazivePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
