import {Component, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
    user?: User;

    constructor(protected users: UserService, private route: ActivatedRoute) {
    }

    ngOnInit() {
        // get the :username parameter from the route
        const username_param = this.route.snapshot.params['username'];

        if (username_param)
            this.user = undefined;
            // this.users.getUserByUsername(username_param)
            //     .then(user => this.user = user)
            //     .catch(error => this.user = undefined);
        else this.users.currentUser.subscribe(user => {
                if (user) this.user = user
            });
    }

}
