import {Component, Input} from '@angular/core';
import {AuthService} from "../../../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../../../models/user";
import {Path} from "../../utils/path";
import {UserService} from "../../../home/services/user.service";

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrl: './nav.component.css'
})
export class NavComponent{
    @Input() user!: User;
    PAR = localStorage.getItem('PAR') as string;

    constructor(private auth: AuthService, private router: Router,
                protected userServ: UserService) {
    }

    disconnect() {
        this.auth.logout();
        this.router.navigate([Path.HOME_LOGIN.toString()]);
    }

    protected readonly Path = Path;
}
