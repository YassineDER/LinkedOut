import { Component } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../models/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrl: './offers.component.css'
})
export class OffersComponent {
    user?: User;

    constructor(private auth: AuthService, private router: Router) {
        this.auth.getUser().then((user) => this.user = user);
    }

    async disconnect() {
        await this.auth.logout()
            .then(() => this.router.navigate(['/login']));
    }
}
