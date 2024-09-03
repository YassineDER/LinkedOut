import {Component, Input} from '@angular/core';
import { User } from '../../../../models/user';
import {Profile} from "../../../../models/social/profile";
import {Education} from "../../../../models/job/education";
import {UtilsService} from "../../../../services/utils.service";

@Component({
  selector: 'app-education-profile',
  templateUrl: './education-profile.component.html',
  styleUrl: './education-profile.component.css'
})
export class EducationProfileComponent {
    @Input() studies: Education[] = [];

    constructor(protected utils: UtilsService) {
    }

}
