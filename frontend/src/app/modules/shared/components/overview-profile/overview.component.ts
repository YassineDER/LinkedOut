import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {Router} from "@angular/router";
import {Jobseeker} from "../../../../models/jobseeker";
import {UserService} from "../../../home/services/user.service";

@Component({
    selector: 'app-overview-profile',
    templateUrl: './overview.component.html',
    styleUrl: './overview.component.css',
    standalone: true,
    imports: [
        NgOptimizedImage,
        NgIf
    ],
})
export class OverviewComponent {
    @Input() user!: User;

    constructor(private router: Router, protected users: UserService) {
    }

}
