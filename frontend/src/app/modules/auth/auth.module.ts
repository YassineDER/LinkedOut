import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgOtpInputModule} from "ng-otp-input";
import {RECAPTCHA_V3_SITE_KEY, RecaptchaFormsModule, RecaptchaV3Module} from "ng-recaptcha";
import {HttpClientModule} from "@angular/common/http";
import {AlertComponent} from "../../components/utils/alert/alert.component";
import {ConfirmationComponent} from "./components/confirmation/confirmation.component";
import {RegisterJobseekerComponent} from "./components/register/register-jobseeker/register-jobseeker.component";
import {ResetPasswordRequestComponent} from "./components/reset-password/reset-password-request.component";
import {RegisterCompanyComponent} from "./components/register/register-company/register-company.component";
import {LoginComponent} from "./components/login/login.component";
import {MatchPasswordDirective} from "./utils/directives/matching-password.directive";
import {environment} from "../../../environments/environment.development";
import {AuthRoutingModule} from "./auth.routes";
import {AuthService} from "../../services/auth.service";

@NgModule({
    declarations: [
        RegisterCompanyComponent,
        RegisterJobseekerComponent,
        ResetPasswordRequestComponent,
        ConfirmationComponent,
        LoginComponent,
        MatchPasswordDirective,
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        NgOtpInputModule,
        RecaptchaV3Module,
        RecaptchaFormsModule,
        HttpClientModule,
        FormsModule,
        NgOptimizedImage,
        AuthRoutingModule,
    ],
    exports: [
        NgOptimizedImage,
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
