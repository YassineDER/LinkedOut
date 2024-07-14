import {Component, Input} from '@angular/core';
import {User} from '../../../../models/user';
import {Company} from "../../../../models/company";
import {Jobseeker} from "../../../../models/jobseeker";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {SocialService} from "../../../home/services/social.service";
import {UserService} from "../../../home/services/user.service";

@Component({
    selector: 'app-about-profile',
    templateUrl: './about.component.html',
    styleUrl: './about.component.css',
    standalone: true,
    imports: [
        NgOptimizedImage,
        NgIf
    ],
})
export class AboutComponent {
    @Input() user!: User;
    profiles: Jobseeker[] = [];

    constructor(protected social: SocialService, protected users: UserService) {
    }



}
