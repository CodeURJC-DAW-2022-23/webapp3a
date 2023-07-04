import { Component } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Review } from 'src/app/models/Review.model';
import { ReviewsService } from 'src/app/services/review.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-reviews-screen',
  templateUrl: './reviews-screen.component.html',
  styleUrls: ['./reviews-screen.component.css']
})
export class ReviewsScreenComponent {

  reviews: Review[] = [];
  userReviews: Review[] = [];
  private tam: number = 10;
  public logService: LoginService;
  
  constructor(private router: Router, activatedRoute: ActivatedRoute, private spinner: NgxSpinnerService, private reviewService: ReviewsService, public loginService: LoginService) {
    this.logService = loginService;
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
      (_:any) => this.router.navigate(['/reviews']),
      (error: any) => console.error(error)
    );
    window.location.reload();  
  }

  createPDF(): void {
    var cargaDatos = false;
    const doc = new jsPDF();

    doc.setFontSize(60);
    doc.text("REVIEWS", 55, 30);
    doc.setFontSize(12);
    for (var i = 1; i <= this.reviews.length; i++) {
      console.log(this.reviews[i-1].coments);

      doc.text(String(this.reviews[i-1].rating + ' /5'),25,(i+1)*30-7);
      doc.text(doc.splitTextToSize(this.reviews[i-1].coments, 163), 25, (i+1)*30);
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
