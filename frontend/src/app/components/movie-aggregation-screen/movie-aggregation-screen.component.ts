//import { Component, ViewChild } from '@angular/core';
//import { Router, ActivatedRoute } from '@angular/router';
//import { Movie } from 'src/app/models/Movie.model';
//import { LoginService } from 'src/app/services/login.service';
//import { MoviesService } from 'src/app/services/movie.service';
//
//@Component({
//  selector: 'app-movie-aggregation-screen',
//  templateUrl: './movie-aggregation-screen.component.html',
//  styleUrls: ['./movie-aggregation-screen.component.css']
//})
//export class MovieAggregationScreenComponent {
//
//  movie: Movie;
//
//  @ViewChild("file")
//  file: any;
//
//  constructor(private router: Router, public movieService: MoviesService) { 
//    this.movie = {title: '', category: '', image: false, description: '', votes:0, reviews: [], directors: []}
//  }
//
//  add() {
//    if(this.movie.title == '' && this.movie.category == '') {
//      alert('some fields are empty');
//    }else{
//      this.movieService.addMovie(this.movie).subscribe(
//        (movie: any) => { 
//          console.log(movie.id);
//          this.uploadImage(movie);
//        },
//        (_: any) => alert('failed to register')
//      ); 
//    }   
//  }
//
//  uploadImage(movie: Movie): void {
//    const image = this.file.nativeElement.files[0];
//    if(image) {
//      let formData = new FormData();
//      formData.append("imageFile",image);
//      movie.image=true;
//      this.movieService.setMovieImage(movie,formData).subscribe(
//        (_: any) => { 
//          alert('movie added successfully');
//          this.router.navigate(['/movies/release/admin']) },
//        (error: any) => alert('error uploading user image')
//      );
//    }
//  }
//
//  appLogo() {
//		return '/assets/images/logoMF1.png';
//	}
//
//  movieImage(id: number){
//		return '/api/movies/' + id + '/image';
//	}
//
//  home() {
//    this.router.navigate(['/movies']);
//  }
//}
//