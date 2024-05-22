import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {ResetPasswordComponent} from "./components/auth/reset-password/reset-password.component";
import {LoginGuard} from "./guards/loginGuard";
import { ConfirmationComponent } from "./components/auth/confirmation/confirmation.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
    {path: 'login', component: LoginComponent, data: {animation: 'LoginPage'}},
    {path: 'register', component: RegisterComponent, data: {animation: 'RegisterPage'}},
    {path: 'request-password-reset', component: ResetPasswordComponent, data: {animation: 'ResetPasswordPage'}},
    {path: 'password/reset/:token', component: ConfirmationComponent},
    {path: 'account/verify/:token', component: ConfirmationComponent},
    { path: '', component: HomeComponent, canActivate: [LoginGuard], data: {animation: 'HomePage'},
    children: [
        {path: 'profile', component: LoginComponent},
    ]},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
