import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../../models/user';
import {SocialService} from "../../../home/services/social.service";
import {UserService} from "../../../home/services/user.service";
import {UtilsService} from "../../../../services/utils.service";

@Component({
    selector: 'app-users-suggestions',
    templateUrl: './users-suggestions.component.html',
    styleUrl: './users-suggestions.component.css',
})
export class UsersSuggestionsComponent implements OnInit {
    @Input() user!: User;
    suggested_users: User[] = [];
    initiazlized = false;

    constructor(private social: SocialService, protected users: UserService,
                private utils: UtilsService) {
    }

    ngOnInit() {

        // this.users.suggestUsers()
        //     .then((suggested) => {
        //         this.suggested_users = suggested
        //         this.initiazlized = true;
        //     })
        //     .catch((err) => console.error(err));
    }

    // the problem is the suggested users are being fetched in same time as checkConnection

    async checkConnection(user: User) {
        // if (this.suggested_users.length !== 0)
        //     return await this.social.isConnection(user)
        // else return false
        return false;
    }

    async connect(user: User) {
        return await this.social.connect(user)
            .then(() => {
                this.utils.alert("La connexion avec ce profil a été établie avec succès");
                this.users.suggestUsers()
                    .then((suggested) => this.suggested_users = suggested)
                    .catch((err) => console.error(err));
            })
            .catch((err) => console.error(err));
    }
}
