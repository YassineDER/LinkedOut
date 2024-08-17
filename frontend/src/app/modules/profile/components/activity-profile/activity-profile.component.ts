import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";
import {SocialService} from "../../../home/services/social.service";
import {Path} from "../../../shared/utils/path";
import {Profile} from "../../../../models/social/profile";

@Component({
  selector: 'app-activity-profile',
  templateUrl: './activity-profile.component.html',
  styleUrl: './activity-profile.component.css'
})
export class ActivityProfileComponent {
    @Input() profile!: Profile;
    actualTab: Tabs = Tabs.POSTS;

    constructor() {
    }


    setTab(tab: Tabs) {
        this.actualTab = tab;
    }


    protected readonly Tabs = Tabs;
    protected readonly Path = Path;
}


enum Tabs {
    POSTS,
    COMMENTS
}
