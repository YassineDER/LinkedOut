import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from "./app.routes";
import {ProfileComponent} from "./components/profile/profile.component";
import {BrowserAnimationsModule, provideAnimations} from "@angular/platform-browser/animations";
import {HomeComponent} from './components/home/home.component';
import {HttpClientModule, provideHttpClient, withInterceptors} from '@angular/common/http';
import {OffersComponent} from './components/offers/offers.component';
import {NgOptimizedImage} from "@angular/common";
import {AlertComponent} from "./components/utils/alert/alert.component";
import {LoadingBarHttpClientModule} from "@ngx-loading-bar/http-client";
import {LoadingBarRouterModule} from "@ngx-loading-bar/router";
import {LoadingBarModule} from "@ngx-loading-bar/core";
import {requestsInterceptor} from "./shared/interceptors/requests.interceptor";
import {AuthModule} from "./modules/auth/auth.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CoreModule} from "./modules/core/core.module";

@NgModule({
    declarations: [
        AppComponent,
        ProfileComponent,
        HomeComponent,
        OffersComponent,
    ],
    imports: [
        // nested modules before routing
        CoreModule,

        // system app modules
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
    ],
    bootstrap: [AppComponent],
    providers: []
})
export class AppModule {
}
