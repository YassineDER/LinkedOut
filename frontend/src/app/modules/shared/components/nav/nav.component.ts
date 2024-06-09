import {Component, Input} from '@angular/core';
import {AuthService} from "../../../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../../../models/user";
import {Path} from "../../utils/path";
import {Jobseeker} from "../../../../models/jobseeker";
import {Company} from "../../../../models/company";
import {Admin} from "../../../../models/admin";

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrl: './nav.component.css'
})
export class NavComponent{
    @Input() user!: User;
    hasNotifications: boolean = false;

    constructor(private auth: AuthService, private router: Router) {
    }


    isJobseeker(user: User): user is Jobseeker {
        return (user as Jobseeker).title !== undefined && (user as Jobseeker).cv_url !== null;
    }

    isCompany(user: User): user is Company {
        return (user as Company).company_name !== undefined && (user as Company).company_name !== null;
    }

    isAdmin(user: User): user is Admin {
        return (user as Admin).admin_title !== undefined && (user as Admin).admin_title !== null;
    }

    async disconnect() {
        await this.auth.logout()
            .then(() => this.router.navigate([Path.HOME_LOGIN.toString()]))
    }

    protected readonly Path = Path;
}
