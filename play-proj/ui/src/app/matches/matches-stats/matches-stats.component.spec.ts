import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchesStatsComponent } from './matches-stats.component';

describe('MatchesStatsComponent', () => {
  let component: MatchesStatsComponent;
  let fixture: ComponentFixture<MatchesStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchesStatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchesStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
