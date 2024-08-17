import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {ProfileService} from "../../services/profile.service";
import {Profile} from "../../../../models/social/profile";

@Component({
  selector: 'app-about-profile',
  templateUrl: './about-profile.component.html',
  styleUrl: './about-profile.component.css'
})
export class AboutProfileComponent implements OnInit {
    @Input() profile!: Profile;
    expandable = false;

    constructor() {
    }

    ngOnInit() {
        this.expandable = this.profile.bio.length > 100;
    }
}
