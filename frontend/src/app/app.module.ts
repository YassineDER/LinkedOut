import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from "./app.routes";
import {LoginComponent} from "./components/auth/login/login.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {RegisterCompanyComponent} from "./components/auth/register/register-company/register-company.component";
import {ResetPasswordRequestComponent} from "./components/auth/reset-password/reset-password-request.component";
import {BrowserAnimationsModule, provideAnimations} from "@angular/platform-browser/animations";
import {ConfirmationComponent} from './components/auth/confirmation/confirmation.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HomeComponent} from './components/home/home.component';
import {NgOtpInputModule} from 'ng-otp-input';
import {RECAPTCHA_V3_SITE_KEY, RecaptchaFormsModule, RecaptchaV3Module} from 'ng-recaptcha';
import {environment} from '../environments/environment.development';
import {HttpClientModule, provideHttpClient, withInterceptors} from '@angular/common/http';
import {OffersComponent} from './components/offers/offers.component';
import {NgOptimizedImage} from "@angular/common";
import {MatchPasswordDirective} from "./shared/directives/matching-password.directive";
import {AlertComponent} from "./components/utils/alert/alert.component";
import {RegisterJobseekerComponent} from "./components/auth/register/register-jobseeker/register-jobseeker.component";
import {LoadingBarHttpClientModule} from "@ngx-loading-bar/http-client";
import {LoadingBarRouterModule} from "@ngx-loading-bar/router";
import {LoadingBarModule} from "@ngx-loading-bar/core";
import {requestsInterceptor} from "./shared/interceptors/requests.interceptor";


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ProfileComponent,
        RegisterCompanyComponent,
        RegisterJobseekerComponent,
        ResetPasswordRequestComponent,
        ConfirmationComponent,
        HomeComponent,
        OffersComponent,
        MatchPasswordDirective
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        ReactiveFormsModule,
        NgOtpInputModule,
        RecaptchaV3Module,
        RecaptchaFormsModule,
        HttpClientModule,
        NgOptimizedImage,
        AlertComponent,
        FormsModule,
        LoadingBarHttpClientModule,
        LoadingBarRouterModule,
        LoadingBarModule
    ],
    bootstrap: [AppComponent],
    providers: [
        {
            provide: RECAPTCHA_V3_SITE_KEY,
            useValue: environment.RECAPTCHA_V3_SITE_KEY
        },
        provideAnimations(),
        provideHttpClient(withInterceptors([requestsInterceptor]))
    ]
})
export class AppModule {
}
