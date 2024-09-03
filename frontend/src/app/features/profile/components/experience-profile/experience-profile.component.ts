import {Component, Input} from '@angular/core';
import {Experience} from "../../../../models/job/experience";
import {UtilsService} from "../../../../services/utils.service";

@Component({
  selector: 'app-experience-profile',
  templateUrl: './experience-profile.component.html',
  styleUrl: './experience-profile.component.css'
})
export class ExperienceProfileComponent {
    @Input() experiences: Experience[] = [];

    constructor(protected utils: UtilsService) {
    }

}
