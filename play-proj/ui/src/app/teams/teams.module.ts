import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeamsRoutingModule } from './teams-routing.module';
//Materials
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import {MatDialogModule} from "@angular/material/dialog";
import {ShowMatchesDialogComponent} from "../matches/show-matches-dialog/show-matches-dialog.component";

@NgModule({
  declarations: [ShowMatchesDialogComponent],
  imports: [
    CommonModule,
    TeamsRoutingModule,

    //Materials
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatDialogModule

  ],
  entryComponents:[
    ShowMatchesDialogComponent
  ]
})
export class TeamsModule { }
