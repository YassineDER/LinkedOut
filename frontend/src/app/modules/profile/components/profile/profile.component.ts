import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
    user!: User | null;

    constructor(private userServ: UserService){
        this.userServ.currentUser.subscribe(user =>
            this.user = user);
    }
}
