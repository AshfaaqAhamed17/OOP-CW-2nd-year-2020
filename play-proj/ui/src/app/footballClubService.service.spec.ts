/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { FootballClubServiceService } from './footballClubService.service';

describe('Service: FootballClubService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FootballClubServiceService]
    });
  });

  it('should ...', inject([FootballClubServiceService], (service: FootballClubServiceService) => {
    expect(service).toBeTruthy();
  }));
});
