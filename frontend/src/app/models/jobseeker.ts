import {User} from "./user";

/**
 * Jobseeker model, matchs the one in the backend
 */
export interface Jobseeker extends User {
    first_name: string;
    last_name: string;
    address: string;
    phone: string;
    cv_url: string;
    title: string;
}
