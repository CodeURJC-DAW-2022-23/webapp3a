import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InitialScreenComponent } from 'src/app/components/initial-screen/initial-screen.component';

const routes: Routes = [
  { path: 'movies', component: InitialScreenComponent },
  { path: '', redirectTo: 'movies', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
