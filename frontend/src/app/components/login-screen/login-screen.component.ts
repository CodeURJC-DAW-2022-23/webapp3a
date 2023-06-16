import { Component } from '@angular/core';
import { Router} from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login-screen',
  templateUrl: './login-screen.component.html',
  styleUrls: ['./login-screen.component.css']
})
export class LoginScreenComponent {

  constructor(private router: Router, public loginService: LoginService) { }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(user, pass);
    this.router.navigate(['/movies']);  
  }

  home() {
    this.router.navigate(['/movies']);
  }

  appLogo() {
		return '/assets/images/logoMF1.png';
	}
}
