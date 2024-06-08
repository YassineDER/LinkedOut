import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {NgOtpInputModule} from "ng-otp-input";
import {RECAPTCHA_V3_SITE_KEY, RecaptchaFormsModule, RecaptchaV3Module, ReCaptchaV3Service} from "ng-recaptcha";
import {ConfirmationComponent} from "./components/confirmation/confirmation.component";
import {RegisterJobseekerComponent} from "./components/register/register-jobseeker/register-jobseeker.component";
import {ResetPasswordRequestComponent} from "./components/reset-password/reset-password-request.component";
import {RegisterCompanyComponent} from "./components/register/register-company/register-company.component";
import {LoginComponent} from "./components/login/login.component";
import {MatchPasswordDirective} from "./utils/directives/matching-password.directive";
import {environment} from "../../../environments/environment.development";
import {HomeLoginComponent} from "./components/home-login/home-login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthRoutingModule} from "./auth.routes";

@NgModule({
    declarations: [
        RegisterCompanyComponent,
        RegisterJobseekerComponent,
        ResetPasswordRequestComponent,
        ConfirmationComponent,
        LoginComponent,
        MatchPasswordDirective,
        HomeLoginComponent,
    ],
    imports: [
        CommonModule,
        NgOtpInputModule,
        RecaptchaV3Module,
        RecaptchaFormsModule,
        NgOptimizedImage,
        FormsModule,
        ReactiveFormsModule,

        AuthRoutingModule,
    ],
    providers: [
        {
            provide: RECAPTCHA_V3_SITE_KEY,
            useValue: environment.RECAPTCHA_V3_SITE_KEY
        },
    ]
})
export class AuthModule {
}
