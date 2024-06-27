import {Component, Input} from '@angular/core';
import {AuthService} from "../../../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../../../models/user";
import {Path} from "../../utils/path";
import {Jobseeker} from "../../../../models/jobseeker";
import {Company} from "../../../../models/company";
import {Admin} from "../../../../models/admin";
import {UserService} from "../../../home/services/user.service";

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrl: './nav.component.css'
})
export class NavComponent{
    @Input() user!: User;
    hasNotifications: boolean = false;

    constructor(private auth: AuthService, private router: Router,
                public userServ: UserService) {
    }

    async disconnect() {
        await this.auth.logout()
            .then(() => this.router.navigate([Path.HOME_LOGIN.toString()]))
    }

    protected readonly Path = Path;
}
