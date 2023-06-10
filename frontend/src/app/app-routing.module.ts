import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InitialScreenComponent } from './components/initial-screen/initial-screen.component';
import { ReviewsScreenComponent } from './components/reviews-screen/reviews-screen.component';

const routes: Routes = [
  { path: 'movies', component: InitialScreenComponent },
  { path: 'reviews', component: ReviewsScreenComponent },
  { path: '', redirectTo: 'movies', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
