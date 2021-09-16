import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowMatchesDialogComponent } from './show-matches-dialog.component';

describe('ShowMatchesDialogComponent', () => {
  let component: ShowMatchesDialogComponent;
  let fixture: ComponentFixture<ShowMatchesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowMatchesDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowMatchesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
