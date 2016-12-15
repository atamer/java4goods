import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent }  from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { HostingForm }   from './hostingform.component';
import { ImmigrantForm }   from './immigrantform.component';
import { DonationForm }   from './donationform.component';
import { HttpModule }    from '@angular/http';
import { NgModule }      from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { RouterModule }   from '@angular/router';
import { Rating } from './components/rating.component';


@NgModule({
  imports: [BrowserModule, AppRoutingModule, HttpModule, FormsModule],
  declarations: [AppComponent, DashboardComponent, HostingForm, ImmigrantForm, DonationForm, Rating],
  bootstrap: [AppComponent],
})
export class AppModule { }
