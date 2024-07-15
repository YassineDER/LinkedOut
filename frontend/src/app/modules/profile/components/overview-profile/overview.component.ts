import {Component, Input, } from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";

@Component({
    selector: 'app-overview-profile',
    templateUrl: './overview.component.html',
    styleUrl: './overview.component.css',
})
export class OverviewComponent{
    @Input() user!: User;

    constructor(protected users: UserService) {
    }

}
