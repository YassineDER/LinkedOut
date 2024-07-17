import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";
import {ProfileService} from "../../services/profile.service";

@Component({
  selector: 'app-experience-profile',
  templateUrl: './experience-profile.component.html',
  styleUrl: './experience-profile.component.css'
})
export class ExperienceProfileComponent {
    @Input() user!: User;

    constructor(private profile: ProfileService) {
    }




}
