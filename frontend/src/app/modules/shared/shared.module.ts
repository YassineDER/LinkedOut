import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {NavComponent} from "./components/nav/nav.component";
import {FooterComponent} from "./components/footer/footer.component";
import {RouterModule} from "@angular/router";


@NgModule({
    declarations: [NavComponent, FooterComponent],
    imports: [
        CommonModule,
        RouterModule,
        NgOptimizedImage,
    ],
    exports: [NavComponent, FooterComponent],
    providers: []
})
export class SharedModule {
}
