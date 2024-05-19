import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from "./app.routes";
import {LoginComponent} from "./components/login/login.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {RegisterComponent} from "./components/register/register.component";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {BrowserAnimationsModule, provideAnimations} from "@angular/platform-browser/animations";
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { AlertComponent } from './components/alert/alert.component';


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
        ReactiveFormsModule
    ],
    bootstrap: [AppComponent],
    providers: [
        provideAnimations(),
    ]
})
export class AppModule {
}
