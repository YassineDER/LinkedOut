import {Component, Input, } from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";
import {SocialService} from "../../../home/services/social.service";

@Component({
    selector: 'app-overview-profile',
    templateUrl: './overview.component.html',
    styleUrl: './overview.component.css',
})
export class OverviewComponent{
    @Input() user!: User;

    constructor(protected users: UserService, protected social: SocialService) {
    }

}
