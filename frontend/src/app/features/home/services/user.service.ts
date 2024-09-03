import {Injectable} from '@angular/core';
import {User} from "../../../models/user";
import {environment} from "../../../../environments/environment";
import {Jobseeker} from "../../../models/jobseeker";
import {Company} from "../../../models/company";
import {Admin} from "../../../models/admin";
import {BehaviorSubject, map, Observable} from "rxjs";
import {Role} from "../../../models/role";
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserService {
    api = environment.hostUrl + '/api/user';
    private userSource = new BehaviorSubject<User | null>(null);
    currentUser = this.userSource.asObservable();

    constructor(private http: HttpClient) {
    }

    /**
     * Check if the user is a jobseeker. To be used in the template to display the right content.
     * @param user
     * @return boolean true if the user is a jobseeker
     */
    isJobseeker(user: User): user is Jobseeker {
        return (user as Jobseeker).role.name === Role.JOBSEEKER.toString();
    }

    /**
     * Check if the user is a company. To be used in the template to display the right content.
     * @param user
     * @return boolean true if the user is a company
     */
    isCompany(user: User): user is Company {
        return (user as Company).role.name === Role.COMPANY.toString();
    }

    /**
     * Check if the user is an admin. To be used in the template to display the right content.
     * @param user
     * @return boolean true if the user is an admin
     */
    isAdmin(user: User): user is Admin {
        return (user as Admin).role.name === Role.ADMIN.toString();
    }

    /**
     * Get the current user
     * @return User
     */
    changeUser(user: User) {
        this.userSource.next(user);
    }

    /**
     * Get a list of users with the same role
     * @return Observable<User[]> list of users
     */
    suggestJobseekers(): Observable<User[]> {
        return this.http.get(this.api + '/suggested').pipe(
            map((res: any) => res as User[])
        );
    }

}
