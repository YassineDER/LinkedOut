import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import { fadeAnimation } from '../../animations';
import {AuthService} from "../../service/auth.service";

@Component({
   selector: 'app-root',
   templateUrl: './app.component.html',
   styleUrl: './app.component.css',
   animations: [fadeAnimation]
})
export class AppComponent implements OnInit {
    constructor(private router: Router, private auth: AuthService) {}

    ngOnInit() {
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) this.renitialize();
        });
    }

    prepareRoute(outlet: RouterOutlet) {
      return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
   }

    private renitialize() {
        this.auth.checkAuthStatus();
        console.log('Auth status:', this.auth.isAuthenticated);
    }
}
