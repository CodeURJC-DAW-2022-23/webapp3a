import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InitialScreenComponent } from './components/initial-screen/initial-screen.component';

import { NgxSpinnerModule } from "ngx-spinner";
import { NgChartsModule } from 'ng2-charts';
import { ReviewsScreenComponent } from './components/reviews-screen/reviews-screen.component';
import { LoginScreenComponent } from './components/login-screen/login-screen.component';
import { UserReviewsScreenComponent } from './components/userReviews-screen/userReviews-screen.component';
import { ReviewsModificationScreenComponent } from './components/reviews-modification-screen/reviews-modification-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    InitialScreenComponent,
    ReviewsScreenComponent,
    LoginScreenComponent,
    UserReviewsScreenComponent,
    ReviewsModificationScreenComponent
  ],
  imports: [
    BrowserModule,
    NgxSpinnerModule,
    FormsModule,
    HttpClientModule,
    NgChartsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
