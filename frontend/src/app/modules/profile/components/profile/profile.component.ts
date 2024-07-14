import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{
    user!: User;

    constructor(protected users: UserService){

    }

    ngOnInit() {
        this.users.currentUser.subscribe(user => {
            if (user) this.user = user
        });
    }

}
