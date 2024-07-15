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

    isJobseeker(user: User): user is Jobseeker {
        return (user as Jobseeker).authorities[0].authority === Role.JOBSEEKER.toString();
    }

    isCompany(user: User): user is Company {
        return (user as Company).authorities[0].authority === Role.COMPANY.toString();
    }

    isAdmin(user: User): user is Admin {
        return (user as Admin).authorities[0].authority === Role.ADMIN.toString();
    }

    changeUser(user: User) {
        this.userSource.next(user);
    }

    suggestJobseekers(): Observable<Jobseeker[]> {
        return this.http.get(this.api + '/suggested').pipe(
            map((res: any) => res as Jobseeker[])
        );
    }

}
