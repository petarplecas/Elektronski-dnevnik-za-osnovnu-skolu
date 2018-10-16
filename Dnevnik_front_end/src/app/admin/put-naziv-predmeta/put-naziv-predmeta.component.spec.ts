import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PutNazivPredmetaComponent } from './put-naziv-predmeta.component';

describe('PutNazivPredmetaComponent', () => {
  let component: PutNazivPredmetaComponent;
  let fixture: ComponentFixture<PutNazivPredmetaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PutNazivPredmetaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PutNazivPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
