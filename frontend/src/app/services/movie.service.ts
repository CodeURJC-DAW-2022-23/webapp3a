import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { Movie } from '../models/movie.model';
import { Review } from '../models/review.model';

const BASE_URL = '/api/movies';//esta ruta debería devolver las pelis paginadas (1 num de pag, 10 elem en la pag)

@Injectable({providedIn: 'root'})
export class MoviesService {

	constructor(private http: HttpClient) {
	 }

	getMovies(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=2'
		)as Observable<any>;
	}
	
	getMoreMovies(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=' + tam).pipe(
			catchError((error) => {
				return throwError(error);
		   })
		)as Observable<any>;
	}
	
	getFoundedMovies(movieToSearch: String,tam: number): Observable<any> {
		return this.http.get(BASE_URL + '/' + movieToSearch + '?size=' + tam).pipe(
			catchError((error) => {
				return throwError(error);
		   })
		)as Observable<any>;
	}

	getMovie(id: number | string): Observable<any> {
		return this.http.get(BASE_URL + id).pipe(
            catchError((error) => {
				return throwError(error);
		   })
		)as Observable<any>;
	}   

	addMovie(movie: Movie) {
		if(!movie.id) {
			return this.http.post(BASE_URL + 'addition/new', movie).pipe(
				catchError(error => throwError(error))
			);
		} else {
            return this.http.put(BASE_URL + movie.id + '/edition', movie).pipe(
                catchError(error => throwError(error))
            );
        }
	}

    addReview(movie: Movie, review: Review) {
        
    	if(movie.id && !review.id) {
    		return this.http.post(BASE_URL + movie.id + '/review/new', review).pipe(
    			catchError(error => throwError(error))
    		);
    	} else {
			return this.http.get(BASE_URL + movie.id).pipe(
				catchError((error) => {
					return throwError(error);
			   })
			);
		}
    }

	setMovieImage(movie: Movie, formData: FormData) {
		return this.http.post(BASE_URL + 'addition/new/' + movie.id + '/image', formData).pipe(
            catchError(error => throwError(error))
		);
	}

	updateMovie(movie: Movie) {
		return this.http.put(BASE_URL + movie.id + '/edition',movie).pipe(
			catchError(error => throwError(error))
		);
	}	

}