import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class FootballClubServiceService {

  private urlForMatches = '/api/football';
  private urlForClubsStats = '/api/clubsData';
  private urlForGenerateMatch = '/api/generateMatch';

  constructor(private http: HttpClient) {  }

  getMatchStats(){
    return this.http.get(this.urlForMatches)
  }

  getFootballClubsStats(){
    return this.http.get(this.urlForClubsStats)
  }

  getGenerateMatch(){
    return this.http.get(this.urlForGenerateMatch)
  }
}
