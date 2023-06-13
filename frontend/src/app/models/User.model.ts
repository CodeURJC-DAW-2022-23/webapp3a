import { Review } from "./Review.model";

export interface User {
	id?: number;
	username: string;
	encodedPassword: string;
	name: string;
	email: string;
	roles: string;
	reviews: Review[];
}