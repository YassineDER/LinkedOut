import {Component, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent implements OnInit{
    user!: User;

    constructor(protected users: UserService){
    }

    ngOnInit() {
        this.users.currentUser.subscribe(user => {
            if (user) this.user = user
        });
    }
}
