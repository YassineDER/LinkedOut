import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from "./app.routes";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CoreModule} from "./features/core/core.module";
import {AuthModule} from "./features/authentication/auth.module";

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        // system app features
        BrowserModule,
        BrowserAnimationsModule,
        // nested features before routing
        CoreModule,
        AuthModule,

        AppRoutingModule, // routing module must be last
    ],
    bootstrap: [AppComponent],
    providers: []
})
export class AppModule {
}
