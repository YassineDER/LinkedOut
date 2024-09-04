import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {RouterModule} from "@angular/router";
import {ElpasedDateFormatDirective} from "./directives/elpased-date-format.directive";
import { ParFilepathDirective } from './directives/par-filepath.directive';
import { ModalComponent } from './components/modal/modal.component';
import { SettingsTemplatesComponent } from './components/settings-templates/settings-templates.component';

/**
 * Shared module to hold all the shared components, directives, and pipes
 */
@NgModule({
    declarations: [ElpasedDateFormatDirective, ParFilepathDirective, ModalComponent, SettingsTemplatesComponent],
    imports: [
        CommonModule,
        RouterModule,
        NgOptimizedImage,
    ],
    exports: [ElpasedDateFormatDirective, NgOptimizedImage, ParFilepathDirective, ModalComponent],
    providers: []
})
export class SharedModule {
}
