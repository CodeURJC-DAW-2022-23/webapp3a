import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile-screen',
  templateUrl: './profile-screen.component.html',
  styleUrls: ['./profile-screen.component.css']
})
export class ProfileScreenComponent {

  @ViewChild("newUser")
  newUser: any;

  @ViewChild("username")
  username: any;

  @ViewChild("name")
  name: any;

  @ViewChild("email")
  email: any;

  @ViewChild("encodedPassword")
  encodedPassword: any;

  @ViewChild("file")
  file: any;

  constructor(private router: Router, public loginService: LoginService) {   
  }

  userImage(user: string){
		return '/api/users/' + user + '/image';
	}

  update() {
    this.newUser = {username: this.username.nativeElement.value, encodedPassword: this.encodedPassword.nativeElement.value, name: this.name.nativeElement.value, email: this.email.nativeElement.value, roles:'USER', reviews: []}
    this.loginService.updateUser(this.loginService.currentUser().username,this.newUser).subscribe(
      (user: any) => { 
        console.log(user.username);
        console.log(user.encodedPassword);
        
        //this.updateImage(user);
        alert('registration successfully updated');
        this.loginService.setCurrentUser(user);
        this.router.navigate(['/profile']); 
      },
      (error: any) => alert(error)
    );    
  }

  //updateImage(user: User): void {
  //  const image = this.file.nativeElement.files[0];
  //
  //    let formData = new FormData();
  //    formData.append("imageFile",image);
  //    this.loginService.setUserImage(user,formData).subscribe(
  //      (_: any) => { 
  //        console.log(user.username);
  //      console.log(user.encodedPassword);
  //        alert('registration successfully updated');
  //        //this.router.navigate(['/profile']) 
  //      },
  //      (error: any) => alert('error uploading user image')
  //    );
  //  
  //}
  
  home() {
    this.router.navigate(['/movies']);
  }

  appLogo() {
		return '/assets/images/logoMF1.png';
	}
}
