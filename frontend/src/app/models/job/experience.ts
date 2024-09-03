import {Company} from "../company";

export interface Experience {
    experience_id: number;
    title: string;
    company: Company;
    location: string;
    start_date: number[];
    end_date: number[];
    description: string;
}
