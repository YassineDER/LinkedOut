import {AfterViewInit, ChangeDetectorRef, Component} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../../models/user";
import {AuthService} from "../../../services/auth.service";
import {RouterOutlet} from "@angular/router";
import {fadeInUpAnimation} from "../../../animations";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrl: './home.component.css',
    animations: [fadeInUpAnimation]
})
export class HomeComponent implements AfterViewInit{
    user$: Observable<[User | null, boolean]>;

    constructor(private auth: AuthService, private cdREf: ChangeDetectorRef) {
        this.user$ = this.auth.getUser();
    }

    ngAfterViewInit() {
        this.cdREf.detectChanges();
    }

    prepareOutlet(outlet: RouterOutlet) {
        return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']
    }
}
