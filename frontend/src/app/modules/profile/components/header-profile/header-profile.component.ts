import {Component, Input, } from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";
import {SocialService} from "../../../home/services/social.service";
import {Path} from "../../../shared/utils/path";

@Component({
    selector: 'app-header-profile',
    templateUrl: './header-profile.component.html',
    styleUrl: './header-profile.component.css',
})
export class HeaderProfileComponent {
    @Input() user!: User;
    PAR = localStorage.getItem('PAR');

    constructor(protected users: UserService, protected social: SocialService) {
    }

    protected readonly Path = Path;
}
