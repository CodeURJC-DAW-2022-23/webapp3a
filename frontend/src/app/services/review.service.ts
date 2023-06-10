import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Review } from 'src/app/models/Review.model';

const BASE_URL = '/api/reviews';

@Injectable({providedIn: 'root'})
export class ReviewsService {

	constructor(private http: HttpClient) { }

	getReviews(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=' + tam).pipe(
            catchError((error) => {
                return this.handleError(error);
            })
		)as Observable<any>;
	}
	
	getMoreReviews(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=' + tam).pipe(
			catchError((error)  => {
				return this.handleError(error);
			})
		)as Observable<any>;
	}

	getReview(id: number): Observable<any> {
		return this.http.get(BASE_URL + '/' + id).pipe(
			catchError((error) => {
                return this.handleError(error);
            })
		)as Observable<any>;
	}   

    deleteReview(review: Review) {
        return this.http.delete(BASE_URL + '/' + review.id).pipe(
            catchError((error) => {
                return this.handleError(error);
            })
		)as Observable<any>;
    }

    /*private extractMovie(response: { items: any; }) {
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
	}*/	

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError(() => "Server error (" + error.status + "): " + error.text());
	}
}