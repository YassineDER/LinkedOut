import {Component} from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {fadeAnimation} from "../../animations";

@Component({
   selector: 'app-root',
   templateUrl: './app.component.html',
   styleUrl: './app.component.css',
    animations: [fadeAnimation]
})
export class AppComponent {
    constructor() {}

    /**
     * Prepare route animation
     * @param outlet the outlet of the changing route
     */
    prepareOutlet(outlet: RouterOutlet) {
        return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
    }
}
