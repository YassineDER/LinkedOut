import {Injectable} from '@angular/core';
import {User} from "../../../models/user";
import {environment} from "../../../../environments/environment";
import {Jobseeker} from "../../../models/jobseeker";
import {Company} from "../../../models/company";
import {Admin} from "../../../models/admin";
import {BehaviorSubject} from "rxjs";

@Injectable()
export class UserService {
    url = environment.hostUrl + '/api/user';
    private userSource = new BehaviorSubject<User | null>(null);
    currentUser = this.userSource.asObservable();

    constructor() {
    }

    isJobseeker(user: User): user is Jobseeker {
        return (user as Jobseeker).title !== undefined && (user as Jobseeker).title !== null;
    }

    isCompany(user: User): user is Company {
        return (user as Company).company_name !== undefined && (user as Company).company_name !== null;
    }

    isAdmin(user: User): user is Admin {
        return (user as Admin).admin_title !== undefined && (user as Admin).admin_title !== null;
    }

    changeUser(user: User | null) {
        this.userSource.next(user);
    }
}
