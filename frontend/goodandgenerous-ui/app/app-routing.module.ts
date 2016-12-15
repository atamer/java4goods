import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard.component';
import { ImmigrantForm }      from './immigrantform.component';
import { HostingForm }  from './hostingform.component';
import { DonationForm }  from './donationform.component';
import { AppComponent }  from './app.component';


const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'immigrantform', component: ImmigrantForm },
  { path: 'hostingform', component: HostingForm },
  { path: 'donationform', component: DonationForm }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
