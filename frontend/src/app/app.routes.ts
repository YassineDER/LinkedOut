import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {LoginGuard} from "./guards/loginGuard";
import { ConfirmationComponent } from "./components/confirmation/confirmation.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
    {path: 'login', component: LoginComponent, data: {animation: 'LoginPage'}},
    {path: 'register', component: RegisterComponent, data: {animation: 'RegisterPage'}},
    {path: 'password/reset', component: ResetPasswordComponent},
    {path: 'password/reset/:token', component: ConfirmationComponent},
    {path: 'account/verify/:token', component: ConfirmationComponent},
    { path: '', component: HomeComponent, canActivate: [LoginGuard],
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
