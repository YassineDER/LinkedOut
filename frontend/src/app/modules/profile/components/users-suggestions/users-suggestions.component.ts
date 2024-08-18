import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../../models/user';
import {SocialService} from "../../../home/services/social.service";
import {UserService} from "../../../home/services/user.service";

@Component({
    selector: 'app-users-suggestions',
    templateUrl: './users-suggestions.component.html',
    styleUrl: './users-suggestions.component.css',
})
export class UsersSuggestionsComponent implements OnInit {
    @Input() user!: User;
    profiles: User[] = [];

    constructor(protected social: SocialService, protected users: UserService) {
    }

    ngOnInit() {
        this.users.suggestJobseekers()
            .subscribe((profiles: User[]) => this.profiles = profiles);
    }
}
