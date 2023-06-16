//import { Component } from '@angular/core';
//import { Router, ActivatedRoute } from '@angular/router';
//import { DirectorService } from 'src/app/services/director.service';
//
//@Component({
//  selector: 'app-director-screen',
//  templateUrl: './director-screen.component.html',
//  styleUrls: ['./director-screen.component.css']
//})
//export class DirectorScreenComponent {
//
//  director: any;
//  id: any;
//  genres: String[] = [];
//
//  constructor(private router: Router, private activatedRoute: ActivatedRoute, public directorService: DirectorService) {
//  }
//
//  ngOnInit() {
//    this.id = this.activatedRoute.snapshot.params['id'];
//    this.directorService.getDirector(this.id).subscribe(
//      director => { this.director = director
//        this.genres = this.director.genre;
//      },
//      error => console.error(error)
//    );
//  }
//
//  appLogo() {
//		return '/assets/images/logoMF1.png';
//	}
//
//  directorImage(id: number){
//		return '/api/movies/' + id + '/director/image';
//	}
//
//  home() {
//    this.router.navigate(['/movies']);
//  }
//}
//