import { Component, OnInit } from '@angular/core';
import {FootballClubServiceService} from "../../footballClubService.service";


@Component({
  selector: 'app-teams-stats',
  templateUrl: './teams-stats.component.html',
  styleUrls: ['./teams-stats.component.css']
})

export class TeamsStatsComponent implements OnInit {

  tableHeadingClubRegister: String[] = ['nameofClub', 'clubID', 'numOfWins', 'numOfLosses', 'numOfDraws', 'goalsScored', 'goalsReceived', 'totalPlayedMatches', 'totalPoints'];

  tableDataSourceClubRegisteredData : any;

  constructor( private footballClubService: FootballClubServiceService) { }

  ngOnInit() {
    this.footballClubService.getFootballClubsStats().subscribe((clubs)=>{
      this.tableDataSourceClubRegisteredData = clubs;
    })
  }

  sortByOption(varValue: any){
    return function(a:any,b:any){
      if (a[varValue] < b[varValue]){
        return 1;
      } else if(a[varValue] > b[varValue]){
        return -1;
      }
      return 0;
    }
  }

  sortByWins(){
    this.footballClubService.getFootballClubsStats().subscribe((sortedData)=>{
      this.tableDataSourceClubRegisteredData = sortedData;
      this.tableDataSourceClubRegisteredData.sort(this.sortByOption("numberOfWins"));
    });
  }

  sortByGoals(){
    this.footballClubService.getFootballClubsStats().subscribe((sortedData)=>{
      this.tableDataSourceClubRegisteredData = sortedData;
      this.tableDataSourceClubRegisteredData.sort(this.sortByOption("numberOfGoalsScored"));
    });
  }
}
