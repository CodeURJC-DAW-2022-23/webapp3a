import { Director } from "./Director.model";
import { Review } from "./Review.model";

export interface Movie {
	id?: number;
	title: string;
	category: string;
	image: boolean;
	description: string;
	votes: number;
	reviews: Review[];
	directors: Director[];
}
