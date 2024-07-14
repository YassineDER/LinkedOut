import {Component, Input} from '@angular/core';
import {User} from '../../../../models/user';
import {Company} from "../../../../models/company";
import {Jobseeker} from "../../../../models/jobseeker";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {SocialService} from "../../../home/services/social.service";
import {UserService} from "../../../home/services/user.service";
import {JobseekerService} from "../../../home/services/jobseeker.service";

@Component({
    selector: 'app-users-suggestions',
    templateUrl: './users-suggestions.component.html',
    styleUrl: './users-suggestions.component.css',
    standalone: true,
    imports: [
        NgOptimizedImage,
        NgIf
    ],
})
export class UsersSuggestionsComponent {
    @Input() user!: User;
    profiles: Jobseeker[] = [];

    constructor(protected social: SocialService, protected users: UserService,
                protected jobseeker: JobseekerService) {
    }

    ngOnInit() {
        this.jobseeker.suggestJobseekers().subscribe((profiles: Jobseeker[]) => {
            this.profiles = profiles;
        });
    }


}
