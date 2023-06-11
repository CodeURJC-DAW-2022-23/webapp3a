import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { User } from '../models/User.model';

/* REVISAR CLASE */

const BASE_URL = '/api/auth';
const USER_URL = '/api/users';

@Injectable({ providedIn: 'root' })
export class LoginService {
	
	tam: number = 10;
	logged: boolean = false;
	user: any;

	constructor(private https: HttpClient) {
		this.reqIsLogged();
	}

	reqIsLogged() {
		this.https.get(USER_URL + '/current', { withCredentials: true }).subscribe(
			response => {
				this.user = response as User;
				this.logged = true;
			},
			error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
		);
	}

	logIn(user: string, pass: string) {
		this.https.post(BASE_URL + "/login", { username: user, password: pass }, { withCredentials: true })
		.subscribe(
			response => this.reqIsLogged(),
			error => alert("Wrong credentials")
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
		//console.log(this.user.roles);
		return this.user && this.user.roles.indexOf('USER') !== -1;
	}

	currentUser() {
		return this.user;
	}

    handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError(() => "Server error (" + error.status + "): " + error.text());
	}
}