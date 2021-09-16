import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatchesStatsComponent } from './matches/matches-stats/matches-stats.component'
import { TeamsStatsComponent } from './teams/teams-stats/teams-stats.component';

const routes: Routes = [
  {
    path:'matches',
    component : MatchesStatsComponent
  },
  {
    path:'teams',
    component : TeamsStatsComponent
  },
  {
    path:'',
    redirectTo:'teams',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
