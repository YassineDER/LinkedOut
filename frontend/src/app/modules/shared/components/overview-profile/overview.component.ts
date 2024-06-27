import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";
import {NgOptimizedImage} from "@angular/common";
import {Router} from "@angular/router";
import {Jobseeker} from "../../../../models/jobseeker";

@Component({
    selector: 'app-overview-profile',
    templateUrl: './overview.component.html',
    styleUrl: './overview.component.css',
    standalone: true,
    imports: [
        NgOptimizedImage
    ],
})
export class OverviewComponent {
    @Input() user!: User | null;

    constructor(private router: Router) {
    }

}
