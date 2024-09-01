import {User} from "./user";

/**
 * Admin model, matches the one in the backend
 */
export interface Admin extends User {
    first_name: string;
    last_name: string;
    phone: string;
    title: string;
}
