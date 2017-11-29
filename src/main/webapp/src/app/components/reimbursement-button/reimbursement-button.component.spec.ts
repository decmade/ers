import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimbursementButtonComponent } from './reimbursement-button.component';

describe('ReimbursementButtonComponent', () => {
  let component: ReimbursementButtonComponent;
  let fixture: ComponentFixture<ReimbursementButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReimbursementButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbursementButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
