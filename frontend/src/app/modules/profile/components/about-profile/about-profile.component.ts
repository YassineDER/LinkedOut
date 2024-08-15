import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {ProfileService} from "../../services/profile.service";

@Component({
  selector: 'app-about-profile',
  templateUrl: './about-profile.component.html',
  styleUrl: './about-profile.component.css'
})
export class AboutProfileComponent implements OnInit {
    @Input() user!: User;
    expandable = false;

    constructor(protected profile: ProfileService) {
    }

    ngOnInit() {
        this.expandable = this.user.profile.bio.length > 100;
    }
}
