import { Component } from '@angular/core';
import { Router} from '@angular/router';
import { Director } from './../../models/Director.model';
import { DirectorsService } from 'src/app/services/director.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-directors-screen',
  templateUrl: './directors-screen.component.html',
  styleUrls: ['./directors-screen.component.css']
})
export class DirectorsScreenComponent {

  constructor(private router: Router, private directorService: DirectorsService, public loginService: LoginService) { }

 /* ngOnInit() {
    this.directorService.getDirectors(this.tam).subscribe(
      directors => this.directorService = directors.content,
      error => console.log(error)
    );
  }
 */
  home() {
    this.router.navigate(['/movies']);
  }

  appLogo() {
		return '/assets/images/logoMF1.png';
	}

}
