import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {SocialService} from "../../../home/services/social.service";
import {Profile} from "../../../../models/social/profile";

@Component({
  selector: 'app-stats-profile',
  templateUrl: './stats-profile.component.html',
  styleUrl: './stats-profile.component.css'
})
export class StatsProfileComponent implements OnInit{
    @Input() profile!: Profile;

    constructor() {
    }

    ngOnInit() {

    }
}
