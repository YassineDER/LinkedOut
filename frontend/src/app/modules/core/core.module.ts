import {APP_INITIALIZER, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule, provideHttpClient, withInterceptors} from "@angular/common/http";
import {LoadingBarHttpClientModule} from "@ngx-loading-bar/http-client";
import {LoadingBarRouterModule} from "@ngx-loading-bar/router";
import {LoadingBarModule} from "@ngx-loading-bar/core";
import {provideAnimations} from "@angular/platform-browser/animations";
import {requestsInterceptor} from "../shared/interceptors/requests.interceptor";
import {AlertComponent} from "./components/alert/alert.component";
import {UtilsService} from "../../services/utils.service";
import {ipInterceptor} from "../shared/interceptors/ip.interceptor";

export function initializeApp(utils: UtilsService) {
    return (): Promise<any> => {
        return new Promise((resolve, reject) => {
            utils.fetchIp().subscribe({
                next: (response) => {
                    utils.ip = response.ip;
                    resolve(response);
                },
                error: (error) => reject(error)
            });
        });
    };
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
        provideHttpClient(withInterceptors([ipInterceptor, requestsInterceptor])),
        {
            provide: APP_INITIALIZER,
            useFactory: initializeApp,
            deps: [UtilsService],
            multi: true
        }
    ]
})
export class CoreModule {
}
