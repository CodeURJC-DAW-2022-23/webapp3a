import { Component } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Review } from 'src/app/models/Review.model';
import { ReviewsService } from 'src/app/services/review.service';
import jsPDF from 'jspdf';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-user-reviews-screen',
  templateUrl: './userReviews-screen.component.html',
  styleUrls: ['./userReviews-screen.component.css']
})
export class UserReviewsScreenComponent {

  public userReviews: Review[] = [];
  private tam: number = 10;
  
  constructor(private router: Router, private spinner: NgxSpinnerService, private reviewService: ReviewsService, public loginService: LoginService) {
  }

  ngOnInit() {
    
    this.reviewService.getUserReviews(this.loginService.currentUser().username).subscribe(
      reviews => this.userReviews = reviews.content,
      error => console.log(error)
    );
  }

  createPDF(): void {
    var cargaDatos = false;
    const doc = new jsPDF();

    doc.setFontSize(60);
    doc.text("REVIEWS", 55, 30);
    doc.setFontSize(12);
    for (var i = 1; i <= this.userReviews.length; i++) {
      console.log(this.userReviews[i-1].coments);

      doc.text(String(this.userReviews[i-1].rating + ' /5'),25,(i+1)*30-7);
      doc.text(doc.splitTextToSize(this.userReviews[i-1].coments, 163), 25, (i+1)*30);
    }
    cargaDatos = true;
    if(cargaDatos){
      doc.save("Reviews listing.pdf");
    }
  }

  home() {
    this.router.navigate(['/movies']);
  }

  moreResults () {
		this.tam += 10;
		this.spinner.show();
		this.reviewService.getMoreUserReviews(this.tam, this.loginService.currentUser().name).subscribe(
			reviews => this.userReviews = reviews.content,
			error => console.log(error)
		);
		this.spinner.hide();
	}

  appLogo() {
		return '/images/logoMF1.png';
	}
}
