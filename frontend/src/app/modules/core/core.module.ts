import {APP_INITIALIZER, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule, provideHttpClient, withInterceptors} from "@angular/common/http";
import {LoadingBarHttpClientModule} from "@ngx-loading-bar/http-client";
import {LoadingBarRouterModule} from "@ngx-loading-bar/router";
import {LoadingBarModule} from "@ngx-loading-bar/core";
import {provideAnimations} from "@angular/platform-browser/animations";
import {jwtInterceptor} from "./interceptors/jwt.interceptor";
import {AlertComponent} from "./components/alert/alert.component";
import {ipInterceptor} from "./interceptors/ip.interceptor";
import {AppInitializerService} from "../../services/app-initializer.service";

/**
 * Initialize the application by fetching the IP address of the client.
 */
export function initializeApp(initService: AppInitializerService) {
    return () => initService.getUserIP();
}

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
        provideHttpClient(withInterceptors([ipInterceptor, jwtInterceptor])),
        {
            provide: APP_INITIALIZER,
            useFactory: initializeApp,
            deps: [AppInitializerService],
            multi: true
        }
    ]
})
export class CoreModule {
}
