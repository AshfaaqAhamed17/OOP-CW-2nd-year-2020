import { Component, OnInit } from '@angular/core';
import { ShowMatchesDialogComponent } from '../show-matches-dialog/show-matches-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import {FootballClubServiceService} from "../../footballClubService.service";

export interface DialogData {
  matchPlayedDate: string;
}

@Component({
  selector: 'app-matches-stats',
  templateUrl: './matches-stats.component.html',
  styleUrls: ['./matches-stats.component.css']
})

export class MatchesStatsComponent implements OnInit {

  tableDataSourceMatchData : any;

  tableHeading: String[] = ['date', 'homeTeam', 'scoreHomeTeam', 'awayTeam', 'scoreAwayTeam'];

  constructor(public dialog:MatDialog, private footballClubService: FootballClubServiceService) {  }

  ngOnInit() {
    this.footballClubService.getMatchStats().subscribe((matches)=>{
      this.tableDataSourceMatchData = matches;
    });
  }

  openRepDialog(){

      this.footballClubService.getGenerateMatch().subscribe((generate) => {

          this.dialog.open(ShowMatchesDialogComponent,{
            data: {generate},
            width : '450px',
            height: '286px'
          });
          console.log(generate);
      });
      this.footballClubService.getMatchStats().subscribe((matches)=>{
          this.tableDataSourceMatchData = matches;
      });
  }


  searchByDate() {

    let getDateOfMatch = (<HTMLInputElement>document.getElementById("dateOfMatch")).value;
    if (!getDateOfMatch.match('^[0-9-]+$')){
        alert("Please enter valid Inputs!")
    }else{
      this.footballClubService.getMatchStats().subscribe((matches)=>{

        let matchesOnThisDate = this.tableDataSourceMatchData.filter((date: { dateOfMatchPlayed: string; }) => date.dateOfMatchPlayed == getDateOfMatch);

        console.log(matchesOnThisDate);
        this.tableDataSourceMatchData = matchesOnThisDate;
      })
    }
  }

  refresh() {
    location.reload();
  }
}
