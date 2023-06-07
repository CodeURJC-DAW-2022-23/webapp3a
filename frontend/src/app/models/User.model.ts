import { Review } from "./Review.model";

export interface User {
    map(arg0: (user: { reviews: any; }) => any): unknown;
	id?: number;
	username: string;
	name: string;
	email: string;
	roles: string;
	reviews: Review[];
}