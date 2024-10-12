import { Component } from '@angular/core';
import {environment} from "../../../../../environments/environment";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrl: './jobs.component.css'
})
export class JobsComponent {
    isDev = false;

    constructor() {
        this.isDev = !environment.production;
    }

}
