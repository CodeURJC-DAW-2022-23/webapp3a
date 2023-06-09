import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/models/Movie.model';
import { LoginService } from 'src/app/services/login.service';
import { MoviesService } from 'src/app/services/movie.service';

import { NgxSpinnerService } from "ngx-spinner";
import { BaseChartDirective } from 'ng2-charts';

import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

import { DataLabelsPlugin } from 'chartjs-plugin-datalabels';
b@Component({
  selector: 'app-initial-screen',
  templateUrl: './initial-screen.component.html',
  styleUrls: ['./initial-screen.component.css']
})
export class InitialScreenComponent {

	@ViewChild(BaseChartDirective) chart?: BaseChartDirective;

public someAction(): void {
  this.chart?.toBase64Image();
}


  movies: Movie[] = [];
  private tam: number = 10;
  capacity: number = 0;
  moviesFounded: Movie[] = [];
  movieToSearch: any;

	constructor(private router: Router, private spinner: NgxSpinnerService, private movieService: MoviesService, public loginService: LoginService) {}
	
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
		this.spinner.show();
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
		this.spinner.hide();
	}

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
