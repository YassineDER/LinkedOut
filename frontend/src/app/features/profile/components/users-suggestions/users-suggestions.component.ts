import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../../models/user';
import {SocialService} from "../../../home/services/social.service";
import {UserService} from "../../../home/services/user.service";
import {UtilsService} from "../../../../services/utils.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {HttpClientError} from "../../../shared/utils/http-client.error";
import {Profile} from "../../../../models/social/profile";

@Component({
    selector: 'app-users-suggestions',
    templateUrl: './users-suggestions.component.html',
    styleUrl: './users-suggestions.component.css',
})
export class UsersSuggestionsComponent implements OnInit {
    @Input() user!: User;
    suggestedUsers: SuggestedUser[] = [];

    constructor(private social: SocialService, protected users: UserService,
                private utils: UtilsService) {
    }

    ngOnInit() {
        this.users.suggestUsers()
            .then((result) => result.map((u) => {
                return {user: u, connected: false} as SuggestedUser
            }))
            .then((suggested) => {
                suggested.forEach((s) =>
                    this.checkConnection(s.user.profile).then((connected) => s.connected = connected)
                        .then(() => this.suggestedUsers.push(s)))
            })
            .catch((err) => this.utils.alert(err.error.error));
    }

    async checkConnection(profile: Profile) {
        return await this.social.isConnection(profile)
            .then((connected) => connected)
    }

    async connect(profile: Profile) {
        return await this.social.connect(profile)
            .then(() => {
                this.suggestedUsers.find((suggested) => suggested.user.profile.profileId === profile.profileId)!.connected = true;
                this.utils.alert("La connexion avec ce profil a été établie avec succès", AlertType.SUCCESS);
            })
            .catch((e: HttpClientError) => this.utils.alert(e.error.error));
    }
}

interface SuggestedUser {
    user: User;
    connected: boolean;
}
