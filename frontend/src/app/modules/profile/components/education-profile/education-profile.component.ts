import {Component, Input} from '@angular/core';
import { User } from '../../../../models/user';

@Component({
  selector: 'app-education-profile',
  templateUrl: './education-profile.component.html',
  styleUrl: './education-profile.component.css'
})
export class EducationProfileComponent {
    @Input() user!: User;

    constructor() {
    }

}
