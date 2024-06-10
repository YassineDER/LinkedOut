import { Component } from '@angular/core';
import {User} from "../../../../models/user";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
    user: User = {} as User;

    constructor(private route: ActivatedRoute) {
        this.user = this.route.snapshot.data['user'];
    }

}
