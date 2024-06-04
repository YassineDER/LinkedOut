import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AlreadyAuthGuard} from "../../guards/already-auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {RegisterCompanyComponent} from "./components/register/register-company/register-company.component";
import {RegisterJobseekerComponent} from "./components/register/register-jobseeker/register-jobseeker.component";
import {ResetPasswordRequestComponent} from "./components/reset-password/reset-password-request.component";
import {ConfirmationComponent} from "./components/confirmation/confirmation.component";

const routes: Routes = [
    {
        path: 'login', component: LoginComponent, canActivate: [AlreadyAuthGuard], title: 'Se connecter - LinkedOut',
        data: {animation: 'LoginPage'}
    },
    {
        path: 'register', canActivate: [AlreadyAuthGuard], title: 'S\'inscrire - LinkedOut', children: [
            {path: '', redirectTo: 'jobseeker', pathMatch: 'full'},
            {path: 'company', component: RegisterCompanyComponent, data: {animation: 'RegisterCompanyPage'}},
            {path: 'jobseeker', component: RegisterJobseekerComponent, data: {animation: 'RegisterJobseekerPage'}},
        ]
    },
    {
        path: 'request-password-reset', canActivate: [AlreadyAuthGuard], component: ResetPasswordRequestComponent,
        data: {animation: 'ResetPasswordRequestPage'}, title: 'Mot de passe oublié - LinkedOut'
    },
    {
        path: 'password/reset', canActivate: [AlreadyAuthGuard], component: ConfirmationComponent,
        data: {animation: 'ResetPasswordPage'}, title: 'Réinitialisation du mot de passe - LinkedOut'
    },
    {
        path: 'account/verify', canActivate: [AlreadyAuthGuard], component: ConfirmationComponent,
        data: {animation: 'EmailVerificationPage'}, title: 'Vérification de l\'adresse email - LinkedOut'
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AuthRoutingModule {
}
