import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from '../models/User.model';
import { Review } from '../models/Review.model';

/* REVISAR CLASE */

const BASE_URL = '/api/auth';
const USER_URL = '/api/users';
const USER_REVIEWS_URL = 'api/reviews/user/';

@Injectable({ providedIn: 'root' })
export class LoginService {
	
	tam: number = 10;
	logged: boolean = false;
	user!: User | undefined;

	constructor(private https: HttpClient) {
		this.reqIsLogged();
	}

	reqIsLogged() {
		this.https.get(USER_URL + '/current', { withCredentials: true }).subscribe(
			(response: any) => {
				this.user = response as User;
				this.logged = true;
			}
		);
	}

	logIn(user: string, pass: string) {
		this.https.post(BASE_URL + "/login", { username: user, password: pass }, { withCredentials: true })
		.subscribe(
			(response: any) => this.reqIsLogged()
		);
	}

	logOut() {
		return this.https.post(BASE_URL + '/logout', { withCredentials: true })
		.subscribe((resp: any) => {
			console.log("LOGOUT: succesfully");
			this.logged = false;
			this.user = undefined;
		});
	}

	isLogged() {
		return this.logged;
	}

	isAdmin() {
		return this.user && this.user.roles.indexOf('ADMIN') !== -1;
	}

    isUser() {
		return this.user && this.user.roles.indexOf('USER') !== -1;
	}

	currentUser() {
		return this.user;
	}

	getUserReviews(tam: number): Observable<Review[]> {
		return this.https.get(USER_REVIEWS_URL + this.user?.username + '?' + tam).pipe(
            map(response => this.extractReviews(response as any)),
			catchError(error => this.handleError(error))
		)as Observable<Review[]>;
	}

    handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError(() => "Server error (" + error.status + "): " + error.text());
	}

    private extractReviews(response: { items: any; }) {
        return response.items.map((user: { reviews: Review[]; }) => user.reviews)
    }
}