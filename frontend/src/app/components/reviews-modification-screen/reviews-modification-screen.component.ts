import { Component } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Review } from 'src/app/models/Review.model';
import { ReviewsService } from 'src/app/services/review.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-reviews-modification-screen',
  templateUrl: './reviews-modification-screen.component.html',
  styleUrls: ['./reviews-modification-screen.component.css']
})
export class ReviewsModificationScreenComponent {

  reviews: Review[] = [];
  userReviews: Review[] = [];
  private tam: number = 10;
  
  constructor(private router: Router, activatedRoute: ActivatedRoute, private spinner: NgxSpinnerService, private reviewService: ReviewsService, public loginService: LoginService) {
  }

  ngOnInit() {
    this.reviewService.getReviews(this.tam).subscribe(
      reviews => this.reviews = reviews.content,
      error => console.log(error)
    );
  }
  
  deleteReview(review: Review) {
    console.log(review.id);
    this.reviewService.deleteReview(review).subscribe(
      (_:any) => window.location.reload(),
      (error: any) => console.error(error)
    );
    //window.location.reload();  
  }

  home() {
    this.router.navigate(['/movies']);
  }

  moreResults () {
		this.tam += 10;
		this.spinner.show();
		this.reviewService.getMoreReviews(this.tam).subscribe(
			reviews => this.reviews = reviews.content,
			error => console.log(error)
		);
		this.spinner.hide();
	}

  appLogo() {
		return '/images/logoMF1.png';
	}

}
