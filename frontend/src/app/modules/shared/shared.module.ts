import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {NavComponent} from "./components/nav/nav.component";
import {FooterComponent} from "./components/footer/footer.component";
import {RouterModule} from "@angular/router";
import {DateFormatDirective} from "./directives/date-format.directive";

/**
 * Shared module to hold all the shared components, directives, and pipes
 */
@NgModule({
    declarations: [NavComponent, FooterComponent, DateFormatDirective],
    imports: [
        CommonModule,
        RouterModule,
        NgOptimizedImage,
    ],
    exports: [NavComponent, FooterComponent, DateFormatDirective],
    providers: []
})
export class SharedModule {
}
