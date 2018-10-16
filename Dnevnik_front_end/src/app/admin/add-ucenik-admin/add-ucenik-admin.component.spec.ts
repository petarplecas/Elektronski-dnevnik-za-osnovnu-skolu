import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUcenikAdminComponent } from './add-ucenik-admin.component';

describe('AddUcenikAdminComponent', () => {
  let component: AddUcenikAdminComponent;
  let fixture: ComponentFixture<AddUcenikAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddUcenikAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUcenikAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
