import { Component } from '@angular/core';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrl: './offers.component.css'
})
export class OffersComponent {

    constructor(private auth: AuthService) {}

    disconnect() {
        this.auth.logout();
        location.reload();
    }
}
