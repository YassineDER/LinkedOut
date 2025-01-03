import { Component } from '@angular/core';
import {environment} from "../../../../../environments/environment";

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrl: './messaging.component.css'
})
export class MessagingComponent {
    isDev = false;

    constructor() {
        this.isDev = !environment.production;
    }
}
