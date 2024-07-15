import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {SocialService} from "../../../home/services/social.service";

@Component({
  selector: 'app-stats-profile',
  templateUrl: './stats-profile.component.html',
  styleUrl: './stats-profile.component.css'
})
export class StatsProfileComponent implements OnInit{
    @Input() user!: User;
    profile_views: number = 0;
    today_views: number = 0;
    search_apperances: number = 0;

    constructor(private social: SocialService) {
    }

    ngOnInit() {

    }
}
