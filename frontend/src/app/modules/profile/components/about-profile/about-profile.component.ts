import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";
import {ProfileService} from "../../services/profile.service";
import {Profile} from "../../../../models/profile";

@Component({
  selector: 'app-about-profile',
  templateUrl: './about-profile.component.html',
  styleUrl: './about-profile.component.css'
})
export class AboutProfileComponent implements OnInit {
    @Input() user!: User;
    expandable = false;
    user_profile!: Profile;

    constructor(protected profile: ProfileService) {
    }

    ngOnInit() {
        this.user_profile = this.profile.getProfileOf(this.user);
        this.expandable = this.user_profile.about.length > 100;
    }
}
