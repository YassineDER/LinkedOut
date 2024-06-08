import { Component } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../models/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent {
    user?: User;

    constructor(private auth: AuthService, private router: Router) {
        this.auth.getUser().then((user) => this.user = user);
    }
}
