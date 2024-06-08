import { Component } from '@angular/core';
import {AuthService} from "../../../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
    constructor(private auth: AuthService, private router: Router) {}


    async disconnect() {
        await this.auth.logout()
            .then(() => this.router.navigate(['/login']));
    }
}
