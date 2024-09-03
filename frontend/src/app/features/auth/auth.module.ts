import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {NgOtpInputModule} from "ng-otp-input";
import {ConfirmationComponent} from "./components/confirmation/confirmation.component";
import {RegisterJobseekerComponent} from "./components/register/register-jobseeker/register-jobseeker.component";
import {ResetPasswordRequestComponent} from "./components/reset-password/reset-password-request.component";
import {RegisterCompanyComponent} from "./components/register/register-company/register-company.component";
import {LoginComponent} from "./components/login/login.component";
import {MatchPasswordDirective} from "./utils/directives/matching-password.directive";
import {HomeLoginComponent} from "./components/home-login/home-login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthRoutingModule} from "./auth.routes";

/**
 * Auth module for all authentication related components. Its separate from the home-component module.
 */
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
        NgOptimizedImage,
        FormsModule,
        ReactiveFormsModule,

        AuthRoutingModule, // Any routing module must stay at the end
    ],
})
export class AuthModule {
}
