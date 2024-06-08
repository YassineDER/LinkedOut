import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from "./app.routes";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CoreModule} from "./modules/core/core.module";
import {AuthModule} from "./modules/auth/auth.module";

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        // system app modules
        BrowserModule,
        BrowserAnimationsModule,
        // nested modules before routing
        CoreModule,
        AuthModule,

        AppRoutingModule,
    ],
    bootstrap: [AppComponent],
    providers: []
})
export class AppModule {
}
