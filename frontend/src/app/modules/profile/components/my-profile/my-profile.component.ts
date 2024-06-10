import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {User} from "../../../../models/user";
import {AuthService} from "../../../../services/auth.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-preferences-page',
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css'
})
export class MyProfileComponent {
    user: User | null = null;

    constructor(private auth: AuthService){
        this.auth.getUser().subscribe((user) => this.user = user);
    }
}
