import { Director } from "./director.model";
import { Review } from "./review.model";

export interface Movie {
	id?: number;
	title: string;
	category: string;
	//image: boolean;
	//movie_description: string;
	//movie_votes: number;
	//movie_img: Blob;
	//reviews: Review[];
	//directors: Director[];
}