import { Component } from '@angular/core';
import {User} from "../../../../models/user";
import {ActivatedRoute} from "@angular/router";
import {Path} from "../../../shared/utils/path";

@Component({
  selector: 'app-security-settings',
  templateUrl: './security-preferences.component.html',
  styleUrl: './security-preferences.component.css'
})
export class SecurityPreferencesComponent {
    user!: User;

    constructor(private route: ActivatedRoute){
        this.route.params.subscribe(params => {
            if (this.route.snapshot.data['user'])
                this.user = this.route.snapshot.data['user'];
        })
    }


    protected readonly Path = Path;
}
