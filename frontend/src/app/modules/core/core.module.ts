import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {HttpClientModule, provideHttpClient, withInterceptors} from "@angular/common/http";
import {LoadingBarHttpClientModule} from "@ngx-loading-bar/http-client";
import {LoadingBarRouterModule} from "@ngx-loading-bar/router";
import {LoadingBarModule} from "@ngx-loading-bar/core";
import {provideAnimations} from "@angular/platform-browser/animations";
import {requestsInterceptor} from "../../shared/interceptors/requests.interceptor";
import {UtilsService} from "../../services/utils.service";
import {AuthService} from "../../services/auth.service";
import {FormsService} from "../../services/forms.service";
import {AuthModule} from "../auth/auth.module";
import {AlertComponent} from "../../components/utils/alert/alert.component";


@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        AuthModule,
        AlertComponent,
    ],
    exports: [
        HttpClientModule,
        LoadingBarHttpClientModule,
        LoadingBarRouterModule,
        LoadingBarModule,
        NgOptimizedImage,
        AlertComponent,
    ],
    providers: [
        provideAnimations(),
        provideHttpClient(withInterceptors([requestsInterceptor])),
    ]
})
export class CoreModule {
}
