import { Review } from "./Review.model";

export interface User {
	id?: number;
	username: string;
	name: string;
	email: string;
	roles: string;
	reviews: Review[];
}