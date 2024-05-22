import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from "./app.routes";
import {LoginComponent} from "./components/auth/login/login.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {ResetPasswordComponent} from "./components/auth/reset-password/reset-password.component";
import {BrowserAnimationsModule, provideAnimations} from "@angular/platform-browser/animations";
import { ConfirmationComponent } from './components/auth/confirmation/confirmation.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { AlertComponent } from './components/alert/alert.component';
import { NgOtpInputModule } from 'ng-otp-input';
import { RECAPTCHA_V3_SITE_KEY, RecaptchaFormsModule, RecaptchaV3Module } from 'ng-recaptcha';
import { environment } from '../environments/environment.development';


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ProfileComponent,
        RegisterComponent,
        ResetPasswordComponent,
        ConfirmationComponent,
        HomeComponent,
        AlertComponent,
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        ReactiveFormsModule,
        NgOtpInputModule,
        RecaptchaV3Module,
        RecaptchaFormsModule,
    ],
    bootstrap: [AppComponent],
    providers: [
        {
            provide: RECAPTCHA_V3_SITE_KEY,
            useValue: environment.RECAPTCHA_V3_SITE_KEY
        },
        provideAnimations(),
    ]
})
export class AppModule {
}
