import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InitialScreenComponent } from './components/initial-screen/initial-screen.component';
import { ReviewsScreenComponent } from './components/reviews-screen/reviews-screen.component';
import { LoginScreenComponent } from './components/login-screen/login-screen.component';
import { UserReviewsScreenComponent } from './components/userReviews-screen/userReviews-screen.component';
import { ReviewsModificationScreenComponent } from './components/reviews-modification-screen/reviews-modification-screen.component';
import { MovieScreenComponent } from './components/movie-screen/movie-screen.component';
import { SignupScreenComponent } from './components/signup-screen/signup-screen.component';
import { ProfileScreenComponent } from './components/profile-screen/profile-screen.component';

const routes: Routes = [
  { path: 'movies', component: InitialScreenComponent },
  { path: 'reviews', component: ReviewsScreenComponent },
  { path: 'login', component: LoginScreenComponent },
  { path: 'reviews/:user', component: UserReviewsScreenComponent },
  { path: 'reviews/admin/:admin', component: ReviewsModificationScreenComponent },
  { path: 'movies/:id', component: MovieScreenComponent },
  { path: 'signup', component: SignupScreenComponent },
  { path: 'profile', component: ProfileScreenComponent },
  { path: '', redirectTo: 'movies', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
