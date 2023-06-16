//import { Component, ViewChild } from '@angular/core';
//import { Router, ActivatedRoute } from '@angular/router';
//import { Director } from 'src/app/models/Director.model';
//import { MoviesService } from 'src/app/services/movie.service';
//
//@Component({
//  selector: 'app-movie-modification-screen',
//  templateUrl: './movie-modification-screen.component.html',
//  styleUrls: ['./movie-modification-screen.component.css']
//})
//export class MovieModificationScreenComponent {
//
//  movie:any;
//  id:any;
//  directors: Director[] = [];
//
//  @ViewChild("newMovie")
//  newMovie: any;
//
//  @ViewChild("newTitle")
//  newTitle: any;
//
//  @ViewChild("newCategory")
//  newCategory: any;
//
//  @ViewChild("newDescription")
//  newDescription: any;
//
//  constructor(private router: Router, private activatedRoute: ActivatedRoute, public movieService: MoviesService) {
//  }
//
//  ngOnInit() {
//    this.id = this.activatedRoute.snapshot.params['id'];
//    this.movieService.getMovie(this.id).subscribe(
//      movie => { this.movie = movie;
//        this.directors = this.movie.directors;
//      },
//      error => console.error(error)
//    );
//  }
//
//  updateMovie() {
//    this.newMovie = {title: this.newTitle.nativeElement.value, category: this.newCategory.nativeElement.value, image: true, description: this.newDescription.nativeElement.value, votes: 0, reviews: [], directors: []}
//    if(this.newTitle == '' && this.newCategory == ''){
//      alert('some fields might be empty');
//    }else{
//      this.movieService.updateMovie(this.movie.id,this.newMovie).subscribe(
//        (movie: any) => { 
//          console.log(movie.title);
//          console.log(movie.votes);
//          alert('movie successfully updated');
//          this.router.navigate(['/movies/admin/' + movie.id]); 
//        },
//        (_: any) => alert('error updating movie')
//      );    
//    }
//  }
//
//  appLogo() {
//		return '/assets/images/logoMF1.png';
//	}
//
//  movieImage(id: number | undefined){
//		return '/api/movies/' + id + '/image';
//	}
//
//  home() {
//    this.router.navigate(['/movies']);
//  }
//}
//