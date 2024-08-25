import {Component, Input} from '@angular/core';
import {Profile} from "../../../../models/social/profile";

@Component({
  selector: 'app-experience-profile',
  templateUrl: './experience-profile.component.html',
  styleUrl: './experience-profile.component.css'
})
export class ExperienceProfileComponent {
    @Input() profile!: Profile;
    PAR = localStorage.getItem('PAR') as string;

    constructor() {
    }

}
