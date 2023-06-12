import { Component, ViewChild } from '@angular/core';
import { Router} from '@angular/router';
import { Movie } from 'src/app/models/Movie.model';
import { LoginService } from 'src/app/services/login.service';
import { MoviesService } from 'src/app/services/movie.service';

import { NgxSpinnerService } from "ngx-spinner";
import { BaseChartDirective } from 'ng2-charts';

import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';

@Component({
  selector: 'app-initial-screen',
  templateUrl: './initial-screen.component.html',
  styleUrls: ['./initial-screen.component.css']
})
export class InitialScreenComponent {

	@ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

	movies: Movie[] = [];
	private tam: number = 10;
	capacity: number = 0;
	moviesFounded: Movie[] = [];
	movieToSearch: any;
	graphicTitles: string[] = [];
	graphicData: number[] = [];

	constructor(private router: Router, private spinner: NgxSpinnerService, private movieService: MoviesService, public loginService: LoginService) {}

  	ngOnInit() {
		this.spinner.show();
		this.movieService.getMovies(this.tam).subscribe(
			movies => {
				this.capacity = movies.totalElements,
				this.movies = movies.content;

				//for(var i=0; i<this.movies.length; i++){
				//	this.graphicTitles.push(this.movies[i].title);
				//	console.log(this.graphicTitles[i]);
				//}
				//
				//for(var i=0; i<this.movies.length; i++){
				//	console.log(movies[i].reviews.length);
				//	this.graphicData.push(this.movies[i].reviews.length);
				//}
			},error => console.log(error)
		);
		this.spinner.hide();
	}

	public barChartOptions: ChartConfiguration['options'] = {
		responsive: true,
		// We use these empty structures as placeholders for dynamic theming.
		scales: {
			x: {},
			y: {
				min: 10
			}
		},
		plugins: {
			legend: {
				display: true,
			}
		}
	};

	public barChartType: ChartType = 'bar';

	public barChartData: ChartData<'bar'> = {
		labels: [ 1,2,3 ],
		datasets: [
		  	{ data: [14,15,11] }
		]
	};

	moreResults () {
		this.tam += 10;
		this.spinner.show();
		this.movieService.getMoreMovies(this.tam).subscribe(
			movies => this.movies = movies.content,
			error => console.log(error)
		);
		this.spinner.hide();
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
		this.loginService.logOut();
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
		this.router.navigate(['/reviews/' + this.loginService.currentUser()?.username]);  
	}

	reviewsModification() {
		this.router.navigate(['/reviews/admin/' + this.loginService.currentUser()?.username]);
	}
	
	searchMovies() {
		
		this.spinner.show();
		this.movieService.getFoundedMovies().subscribe(
			movies => { 
				let data: any = movies;
				for(var i=0; i < data.content.length; i++) {
					console.log(this.movieToSearch);
					if(data.content[i].title == this.movieToSearch){
						console.log(data.content[i]);
						this.moviesFounded.push(data.content[i]);
					}
				}
			},
			error => alert('error founding movies according to this name: ' + error)
		);
		this.spinner.hide();
	}
	
}
