import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../../models/user";
import {AuthService} from "../../../services/auth.service";
import {Router} from "@angular/router";
import {Jobseeker} from "../../../models/jobseeker";
import {Company} from "../../../models/company";
import {Admin} from "../../../models/admin";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent{
    user$: Observable<User | null>;

    constructor(private auth: AuthService, private router: Router) {
        this.user$ = this.auth.getUser();
    }

}
