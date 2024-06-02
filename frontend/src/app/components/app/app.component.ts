import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import { fadeAnimation } from '../../animations';
import {AuthService} from "../../services/auth.service";

@Component({
   selector: 'app-root',
   templateUrl: './app.component.html',
   styleUrl: './app.component.css',
   animations: [fadeAnimation]
})
export class AppComponent implements OnInit {
    constructor() {}

    ngOnInit() {

    }

    prepareRoute(outlet: RouterOutlet) {
      return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
   }
}
