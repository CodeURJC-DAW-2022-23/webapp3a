import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


const BASE_URL = '/api/directors';

@Injectable({providedIn: 'root'})
export class DirectorsService {

  constructor(private http: HttpClient) { }

	getDirectors(tam: number): Observable<any> {
		return this.http.get(BASE_URL + '?size=' + tam).pipe(
            catchError((error) => {
                return this.handleError(error);
            })
		)as Observable<any>;
	}

  getDirector(id: number): Observable<any> {
		return this.http.get(BASE_URL + '/' + id).pipe(
			catchError((error) => {
                return this.handleError(error);
            })
		)as Observable<any>;
	}

  /*
  createDirector(director: Director): Observable<any> {
    return this.http.post(BASE_URL, director).pipe(
      catchError((error) => {
        return this.handleError(error);
      })
    ) as Observable<any>;
  }
  */

  private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError(() => "Server error (" + error.status + "): " + error.text());
	}

}
