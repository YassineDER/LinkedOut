import { Component } from '@angular/core';
import {environment} from "../../../../../environments/environment";

@Component({
  selector: 'app-network',
  templateUrl: './network.component.html',
  styleUrl: './network.component.css'
})
export class NetworkComponent {
    isDev = false;

    constructor() {
        this.isDev = !environment.production;
    }

}
