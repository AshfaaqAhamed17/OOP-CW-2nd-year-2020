import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TeamsStatsComponent } from './teams-stats/teams-stats.component';


const routes: Routes = [
  {
    path: '',
    component : TeamsStatsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeamsRoutingModule { }
