import { Director } from "./Director.model";
import { Review } from "./Review.model";

export interface Movie {
	id?: number;
	title: string;
	gender: string;
	image: boolean;
	movie_description: string;
	movie_votes: number;
	movie_img: Blob;
	reviews: Review[];
	directors: Director[];
}