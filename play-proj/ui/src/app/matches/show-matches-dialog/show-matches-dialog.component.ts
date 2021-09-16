import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../matches-stats/matches-stats.component";
import {MatchesModule} from "../matches.module";

@Component({
  selector: 'app-show-matches-dialog',
  templateUrl: './show-matches-dialog.component.html',
  styleUrls: ['./show-matches-dialog.component.css']
})
export class ShowMatchesDialogComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
  }
}
