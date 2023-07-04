import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Director } from 'src/app/models/Director.model';
import { LoginService } from 'src/app/services/login.service';
import { MoviesService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-movie-screen',
  templateUrl: './movie-screen.component.html',
  styleUrls: ['./movie-screen.component.css']
})
export class MovieScreenComponent {

  movie: any;
  id: any;
  review: any;
  lastReview: any;
  directors: Director[] = [];

  constructor(private router: Router, private activatedRoute: ActivatedRoute, public movieService: MoviesService, public loginService: LoginService) {
    this.review = {rating: '', coments: ''}  
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.movieService.getMovie(this.id).subscribe(
      movie => { this.movie = movie;
        this.lastReview = this.movie.reviews[this.movie.reviews.length-1];
        this.directors = this.movie.directors;
      },
      error => console.error(error)
    );
  }

  appLogo() {
		return '/images/logoMF1.png';
	}

  movieImage(id: number | undefined){
		return '/api/movies/' + id + '/image';
	}

  home() {
    this.router.navigate(['/movies']);
  }

  writeMovieReview() {
    this.movieService.addReview(this.movie,this.review,this.loginService.currentUser().username).subscribe(
      (_:any) => { this.lastReview = this.review;
        this.router.navigate(['/movies/',this.movie.id]);                   
      },
      (error: any) => console.log(error)
    );
  }
}
