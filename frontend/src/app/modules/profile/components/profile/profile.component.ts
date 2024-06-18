import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
    user!: User;

    constructor(private users: UserService){
    }
}
