import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {ResetPasswordComponent} from "./components/auth/reset-password/reset-password.component";
import {LoginGuard} from "./guards/loginGuard";
import { ConfirmationComponent } from "./components/auth/confirmation/confirmation.component";
import {HomeComponent} from "./components/home/home.component";
import { OffersComponent } from "./components/offers/offers.component";
import { ProfileComponent } from "./components/profile/profile.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";

const routes: Routes = [
    {path: 'login', component: LoginComponent, data: {animation: 'LoginPage'}},
    {path: 'register', component: RegisterComponent, data: {animation: 'RegisterPage'}},
    {path: 'request-password-reset', component: ResetPasswordComponent, data: {animation: 'ResetPasswordRequestPage'}},
    {path: 'password/reset/:token   ', component: ConfirmationComponent, data: {animation: 'ResetPasswordPage'}},
    {path: 'account/verify/:token', component: ConfirmationComponent, data: {animation: 'EmailVerificationPage'}},
    {path: '', component: HomeComponent, data: {animation: 'HomePage'} },
    {path: 'offers', canActivate: [LoginGuard], component: OffersComponent, data: {animation: 'HomePage'},
        children: [
        {path: 'profile', component: ProfileComponent, data: {animation: 'ProfilePage'}},
    ]},

    {path : '**', component: NotFoundComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
