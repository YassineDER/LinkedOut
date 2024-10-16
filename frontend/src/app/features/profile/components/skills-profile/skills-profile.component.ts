import {Component, Input} from '@angular/core';
import { User } from '../../../../models/user';
import {Path} from "../../../shared/utils/path";
import {Profile} from "../../../../models/social/profile";
import {Skill} from "../../../../models/job/skill";

@Component({
  selector: 'app-skills-profile',
  templateUrl: './skills-profile.component.html',
  styleUrl: './skills-profile.component.css'
})
export class SkillsProfileComponent {
    @Input() skills: Skill[] = [];

    constructor() {
    }

    protected readonly Path = Path;
}
