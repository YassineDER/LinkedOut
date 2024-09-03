import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {RouterModule} from "@angular/router";
import {DateFormatDirective} from "./directives/date-format.directive";
import { ParFilepathDirective } from './directives/par-filepath.directive';

/**
 * Shared module to hold all the shared components, directives, and pipes
 */
@NgModule({
    declarations: [DateFormatDirective, ParFilepathDirective],
    imports: [
        CommonModule,
        RouterModule,
        NgOptimizedImage,
    ],
    exports: [DateFormatDirective, NgOptimizedImage, ParFilepathDirective],
    providers: []
})
export class SharedModule {
}
