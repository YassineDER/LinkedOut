import {Component, Input} from '@angular/core';
import {AuthService} from "../../../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../../../models/user";

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrl: './nav.component.css'
})
export class NavComponent{
    @Input() user!: User;

    constructor(private auth: AuthService, private router: Router) {
    }


    async disconnect() {
        await this.auth.logout()
            .then(() => this.router.navigate(['/auth']));
    }
}
