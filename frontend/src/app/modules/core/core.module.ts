import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule, provideHttpClient, withInterceptors} from "@angular/common/http";
import {LoadingBarHttpClientModule} from "@ngx-loading-bar/http-client";
import {LoadingBarRouterModule} from "@ngx-loading-bar/router";
import {LoadingBarModule} from "@ngx-loading-bar/core";
import {provideAnimations} from "@angular/platform-browser/animations";
import {requestsInterceptor} from "../shared/interceptors/requests.interceptor";
import {AlertComponent} from "./components/alert/alert.component";

@NgModule({
    declarations: [AlertComponent],
    imports: [
        CommonModule,
    ],
    exports: [
        HttpClientModule,
        LoadingBarHttpClientModule,
        LoadingBarRouterModule,
        LoadingBarModule,
        AlertComponent,
    ],
    providers: [
        provideAnimations(),
        provideHttpClient(withInterceptors([requestsInterceptor])),
    ]
})
export class CoreModule {
}
