import {Component, Input} from '@angular/core';
import { User } from '../../../../models/user';
import {Path} from "../../../shared/utils/path";

@Component({
  selector: 'app-skills-profile',
  templateUrl: './skills-profile.component.html',
  styleUrl: './skills-profile.component.css'
})
export class SkillsProfileComponent {
    @Input() user!: User;

    constructor() {
    }

    protected readonly Path = Path;
}
