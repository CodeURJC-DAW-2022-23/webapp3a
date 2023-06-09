import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-signup-screen',
  templateUrl: './signup-screen.component.html',
  styleUrls: ['./signup-screen.component.css']
})
export class SignupScreenComponent {

  user: User;

  @ViewChild("file")
  file: any;

  constructor(private router: Router, public loginService: LoginService) {
    this.user = {username: '', encodedPassword: '', name: '', email: '', roles:'USER', reviews: []}
  }

  register() {
    if(this.user.username == '' && this.user.encodedPassword == '' && this.user.name == '' && this.user.email == ''){
      alert('some fields might be empty');
    }else{
      this.loginService.addUser(this.user).subscribe(
        (user: any) => { 
          if(user == null){
            alert('It might be some empty fields');
          }
          this.uploadImage(user);
        },
        (_: any) => alert('failed to register')
      );   
    } 
  }

  uploadImage(user: User): void {
    const image = this.file.nativeElement.files[0];
    if(image) {
      let formData = new FormData();
      formData.append("imageFile",image);
      this.loginService.setUserImage(user,formData).subscribe(
        (_: any) => { 
          alert('registered successfully');
          this.router.navigate(['/signup']) },
        (error: any) => alert('error uploading user image')
      );
    }
  }
  
  home() {
    this.router.navigate(['/movies']);
  }

  appLogo() {
		return '/images/logoMF1.png';
	}
}
