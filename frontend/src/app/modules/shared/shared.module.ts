import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavComponent} from "./components/nav/nav.component";
import {FooterComponent} from "./components/footer/footer.component";
import {RouterModule} from "@angular/router";


@NgModule({
    declarations: [NavComponent, FooterComponent],
    imports: [
        CommonModule,
        RouterModule,
    ],
    exports: [NavComponent, FooterComponent]
})
export class SharedModule {
}
