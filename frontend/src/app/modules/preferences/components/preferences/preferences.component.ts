import {Component, OnInit} from '@angular/core';
import {User} from "../../../../models/user";
import {UserService} from "../../../home/services/user.service";
import {Path} from "../../../shared/utils/path";

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrl: './preferences.component.css'
})
export class PreferencesComponent implements OnInit{
    user!: User;

    constructor(private users: UserService){
    }

    ngOnInit() {
        this.users.currentUser.subscribe(user => {
            if (user) this.user = user
        });
    }

    protected readonly Path = Path;
}
