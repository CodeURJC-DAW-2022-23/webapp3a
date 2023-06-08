import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/models/Movie.model';
import { LoginService } from 'src/app/services/login.service';
import { MoviesService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-initial-screen',
  templateUrl: './initial-screen.component.html',
  styleUrls: ['./initial-screen.component.css']
})
export class InitialScreenComponent {

  movies: Movie[] = [];
  private tam: number = 10;
  capacity: number = 0;

/*	moviesFounded: Movie[] = [];
	capacity: number = 0; 
	loginService: LoginService;
*/
	constructor(private router: Router, private movieService: MoviesService, public loginService: LoginService) {}
	
  /*private movie!: Movie;
	//private titlesGraph: string[] = [];
	//private reviewsGraph: number[] = [];
	//
	//public graph = {
	//	data: {
	//		x: this.titlesGraph,
	//		y: this.reviewsGraph,
	//		type: 'bar'
	//	},
	//	layout: {width: 400, height: 150, title: 'A Fancy Plot'}
	//};*/
  
  ngOnInit() {
		this.movieService.getMovies(this.tam).subscribe(
			movies => {
				this.capacity = movies.totalElements,
				this.movies = movies.content;
	
	//			for(var i=0; i<movies.length; i++){
	//				//console.log(movies[i]);
	//				this.titlesGraph.push(movies[i].title);
	//			}
	//			
	//			for(var i=0; i<movies.length; i++){
	//				//console.log(movies[i].reviews.length);
	//				this.reviewsGraph.push(movies[i].reviews.length);
	//			}
			},
      error => console.log(error)
		);
	}
  
  movieImage(id: number){
    return '/api/movies/' + id + '/image';
  }

	appLogo() {
		return '/assets/images/logoMF1.png';
	}

	newMovie() {
  	this.router.navigate(['/movies/new']);
	}
	
	logout(){
  	this.router.navigate(['/movies']);  
	}
	
	login(){
  	this.router.navigate(['/login']);  
	}
	
	signup(){
  	this.router.navigate(['/signup']);  
	}

	myProfile(){
   	this.router.navigate(['/news']);  
	}
	
	reviews(){
  	this.router.navigate(['/reviews']);  
	}
	
	userReviews(){
		this.router.navigate(['/userReviews']);  
	}
	
	/*searchMovies(movieToSearch: String) {
		
		this.spinner.show();
		this.movieService.getFoundedMovies(movieToSearch,moviesSize).subscribe(
						
			response => {
				
				let data: any = response;
				if(data.size != 0 && this.tam-10 < data.items.length){
				for (var i = this.tam-10; i < data.items.length; i++) {
						let mv = data.items[i];
						this.moviesFounded.push(mv);
					}
				}
			},
			error => alert('error founding movies according to this name: ' + error)
		);
		this.spinner.hide();
	}
	
	moreResults () {
		this.tam += 10;
		this.spinner.show();
		this.movieService.getMoreMovies(this.tam).subscribe(
			response => {
				
				let data: any = response;
				if(data.size != 0 && this.tam-10 < data.items.length){
					for (var i = this.tam-10; i < data.items.length; i++) {
						let mv = data.items[i];
						this.movies.push(mv);
					}
				}
			}
			//,error => alert('error showing more movies: ' + error)	
		);
		this.spinner.hide();
	
		//window.location.reload();
	}
*/
}
