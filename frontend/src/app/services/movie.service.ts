import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { Movie } from '../models/Movie.model';
import { Review } from '../models/Review.model';

/* REVISAR CLASE */

const BASE_URL = '/api/movies/';//esta ruta debería devolver las pelis paginadas (1 num de pag, 10 elem en la pag)

@Injectable({providedIn: 'root'})
export class MoviesService {

	http: HttpClient;

	constructor(http: HttpClient) {
		this.http = http;
	 }

	getMovies(tam: number): Observable<Movie[]> {
		return this.http.get('https://localhost:8443/api/movies?size=2').pipe(
			map(response => this.extractMovies(response as any)),
			catchError(error => this.handleError(error))
		)as Observable<Movie[]>;
	}

    private extractMovies(response: { items: any; }) {
        return response.items.map((movies: { volumeInfo: Movie[]; }) => movies.volumeInfo)
    } 
	
	getMoreMovies(tam: number): Observable<Movie[]> {
		return this.http.get('http://localhost:8443/movies?size=' + tam).pipe(
			map(response => this.extractMovies(response as any)),
			catchError(error => this.handleError(error))
		)as Observable<Movie[]>;
	}
	
	getFoundedMovies(movieToSearch: String,tam: number): Observable<Movie[]> {
		return this.http.get('http://localhost:8443/api/movies/' + movieToSearch + '?size=' + tam).pipe(
			map(response => this.extractMovies(response as any)),
			catchError(error => this.handleError(error))
		)as Observable<Movie[]>;
	}

	getMovie(id: number | string): Observable<Movie> {
		return this.http.get(BASE_URL + id).pipe(
            map(response => this.extractMovie(response as any)),
			catchError(error => this.handleError(error))
		)as Observable<Movie>;
	}   

    private extractMovie(response: { items: any; }) {
        return response.items.map((movie: { volumeInfo: Movie; }): Movie => movie.volumeInfo)
    }

	addMovie(movie: Movie) {
		if(!movie.id) {
			return this.http.post(BASE_URL + 'addition/new', movie).pipe(
				catchError(error => this.handleError(error))
			);
		} else {
            return this.http.put(BASE_URL + movie.id + '/edition', movie).pipe(
                catchError(error => this.handleError(error))
            );
        }
	}

    addReview(movie: Movie, review: Review) {
        
    	if(movie.id && !review.id) {
    		return this.http.post(BASE_URL + movie.id + '/review/new', review).pipe(
    			catchError(error => this.handleError(error))
    		);
    	} else {
			return this.http.get(BASE_URL + movie.id).pipe(
				map(response => this.extractMovie(response as any)),
				catchError(error => this.handleError(error))
			);
		}
    }

	setMovieImage(movie: Movie, formData: FormData) {
		return this.http.post(BASE_URL + 'addition/new/' + movie.id + '/image', formData).pipe(
            catchError(error => this.handleError(error))
		);
	}

	updateMovie(movie: Movie) {
		return this.http.put(BASE_URL + movie.id + '/edition',movie).pipe(
			catchError(error => this.handleError(error))
		);
	}	

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError(() => "Server error (" + error.status + "): " + error.text());
	}
}