import {Component, OnInit} from '@angular/core';
import {Observable, shareReplay} from "rxjs";
import {User} from "../../../models/user";
import {RouterOutlet} from "@angular/router";
import {fadeInUpAnimation} from "../../../animations";
import {AuthService} from "../../../services/auth.service";
import {UserService} from "../services/user.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrl: './home.component.css',
    animations: [fadeInUpAnimation]
})
export class HomeComponent implements OnInit{
    user$: Observable<User | null>;

    constructor(private auth: AuthService, private users: UserService) {
        // Share the user observable object once to avoid multiple subscriptions
        this.user$ = this.auth.getAuthenticatedUser().pipe(shareReplay(1));
    }

    ngOnInit() {
        this.user$.subscribe((user) => {
            if (user)
                this.users.changeUser(user);
        });
    }

    // Prepare the outlet for the animation (different from the one in app module)
    prepareOutlet(outlet: RouterOutlet) {
        return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']
    }
}
