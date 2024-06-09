import {User} from "./user";

/**
 * Company model, matchs the one in the backend
 */
export interface Company extends User {
    company_name: string;
    siren: string;
    description: string;
    headquarters: string;
    foundedDate: string;
    sector: string;
}
