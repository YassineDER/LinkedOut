import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {map, Observable} from "rxjs";
import {User} from "../../../models/user";
import {ActivatedRoute, RouterOutlet} from "@angular/router";
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
    user$: Observable<[User | null, boolean]>;

    constructor(private auth: AuthService, private userServ:UserService, private cdREf: ChangeDetectorRef) {
        this.user$ = this.auth.getAuthenticatedUser();
    }

    ngOnInit() {
        this.user$.subscribe(([user, authenticated]) => {
            this.userServ.changeUser(authenticated ? user : null);
        });
    }

    prepareOutlet(outlet: RouterOutlet) {
        return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']
    }
}
