import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Movie } from '../models/Movie.model';
import { Review } from '../models/Review.model';

/* REVISAR CLASE */

const BASE_URL = '/api/movies';//esta ruta debería devolver las pelis paginadas (1 num de pag, 10 elem en la pag)

@Injectable({providedIn: 'root'})
export class MoviesService {

	constructor(private http: HttpClient) { }

	getMovies(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=' + tam).pipe(
            catchError((error) => {
                return this.handleError(error);
            })
		)as Observable<any>;
	}
	
	getMoreMovies(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=' + tam).pipe(
			catchError((error)  => {
				return this.handleError(error);
			})
		)as Observable<any>;
	}
	
	getFoundedMovies(): Observable<any> {
		return this.http.get(BASE_URL).pipe(
			catchError((error) => {
				return this.handleError(error);
			})
		)as Observable<any>;
	}

	getMovie(id: number): Observable<any> {
		return this.http.get(BASE_URL + '/' + id).pipe(
			catchError((error) => {
				return this.handleError(error);
			})
		)as Observable<any>;
	}   

	/*addMovie(movie: Movie) {
		if(!movie.id) {
			return this.http.post(BASE_URL + 'addition/new', movie).pipe(
				catchError(error => this.handleError(error))
			);
		} else {
            return this.http.put(BASE_URL + movie.id + '/edition', movie).pipe(
                catchError(error => this.handleError(error))
            );
        }
	}*/

    addReview(movie: Movie, review: Review, user: string) {    
		return this.http.post(BASE_URL + '/' + movie.id + '/review/' + user, review).pipe(
			catchError((error) => {
				return this.handleError(error)
			})
		);
    }

	/*setMovieImage(movie: Movie, formData: FormData) {
		return this.http.post(BASE_URL + 'addition/new/' + movie.id + '/image', formData).pipe(
            catchError(error => this.handleError(error))
		);
	}

	updateMovie(movie: Movie) {
		return this.http.put(BASE_URL + movie.id + '/edition',movie).pipe(
			catchError(error => this.handleError(error))
		);
	}*/	

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError(() => "Server error (" + error.status + "): " + error.text());
	}
}