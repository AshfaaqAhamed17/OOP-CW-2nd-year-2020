import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatchesRoutingModule } from './matches-routing.module';

//Materials
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatchesRoutingModule,

    //Materials
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule
  ]
})
export class MatchesModule { }
