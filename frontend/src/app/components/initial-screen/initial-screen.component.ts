import { Component, ViewChild } from '@angular/core';
import { Router} from '@angular/router';
import { Movie } from 'src/app/models/Movie.model';
import { LoginService } from 'src/app/services/login.service';
import { MoviesService } from 'src/app/services/movie.service';

import { NgxSpinnerService } from "ngx-spinner";

import { ChartOptions, ChartDataset, ChartType } from 'chart.js';
//import * as pluginDataLabels from 'chartjs-plugin-datalabels';
//import * from 'ng2-charts';

@Component({
  selector: 'app-initial-screen',
  templateUrl: './initial-screen.component.html',
  styleUrls: ['./initial-screen.component.css']
})
export class InitialScreenComponent {

	  

	movies: Movie[] = [];
	private tam: number = 10;
	capacity: number = 0;
	moviesFounded: Movie[] = [];
	public barChartLabels: string[] = [];
	public barChartData: ChartDataset[] = [];
	movieToSearch: any;
	label1: string[]=[];
	label11: number[]=[];

	constructor(private router: Router, private spinner: NgxSpinnerService, private movieService: MoviesService, public loginService: LoginService) {		
	}

  	ngOnInit() {
		this.spinner.show();
		this.movieService.getMovies(this.tam).subscribe(
			movies => {
				this.capacity = movies.totalElements,
				this.movies = movies.content;

				for(var i=0; i<movies.content.length; i++){
					//console.log(movies.content[i].title);
					this.label1.push(movies.content[i].title);
				//	console.log(movies.content[i].reviews.length);
					this.label11.push(movies.content[i].reviews.length);
				}
				this.barChartLabels = this.label1;
				this.barChartData = [{ 
					data: this.label11,
					label: 'reviews amount per movie'
				}];
			},error => console.log(error)
		);
		this.spinner.hide();	
	}

	public barChartType: ChartType = 'bar';
	public barChartLegend = true;

	moreResults () {
		this.tam += 10;
		this.spinner.show();
		this.movieService.getMoreMovies(this.tam).subscribe(
			movies => this.movies = movies.content,
			error => console.log(error)
		);
		this.spinner.hide();
	}
  
	movieImage(id: number | undefined){
		return '/api/movies/' + id + '/image';
	}

	appLogo() {
		return '/frontend/src/assets/images/logoMF1.png';
	}

	newMovie() {
  		this.router.navigate(['/movies/release/admin']);
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
   	this.router.navigate(['/profile']);  
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
