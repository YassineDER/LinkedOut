import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AlreadyAuthGuard} from "./guards/already-auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {RegisterCompanyComponent} from "./components/register/register-company/register-company.component";
import {RegisterJobseekerComponent} from "./components/register/register-jobseeker/register-jobseeker.component";
import {ResetPasswordRequestComponent} from "./components/reset-password/reset-password-request.component";
import {ConfirmationComponent} from "./components/confirmation/confirmation.component";
import {HomeLoginComponent} from "./components/home-login/home-login.component";

const routes: Routes = [
    {
        path: 'auth',  canActivate: [AlreadyAuthGuard],
        children: [
            {path: '', component: HomeLoginComponent, data: {animation: 'HomePage'}, title: 'LinkedOut'},
            {
                path: 'login',
                component: LoginComponent,
                title: 'Se connecter - LinkedOut',
                data: {animation: 'LoginPage'}
            },
            {
                path: 'register', title: 'S\'inscrire - LinkedOut', children: [
                    {path: '', redirectTo: 'jobseeker', pathMatch: 'full'},
                    {path: 'company', component: RegisterCompanyComponent, data: {animation: 'RegisterCompanyPage'}},
                    {
                        path: 'jobseeker',
                        component: RegisterJobseekerComponent,
                        data: {animation: 'RegisterJobseekerPage'}
                    },
                ]
            },
            {
                path: 'password/reset/request',
                component: ResetPasswordRequestComponent,
                data: {animation: 'ResetPasswordRequestPage'},
                title: 'Mot de passe oublié - LinkedOut'
            },
            {
                path: 'password/reset', component: ConfirmationComponent,
                data: {animation: 'ResetPasswordPage'}, title: 'Réinitialisation du mot de passe - LinkedOut'
            },
            {
                path: 'email/verify', component: ConfirmationComponent,
                data: {animation: 'EmailVerificationPage'}, title: 'Vérification de l\'adresse email - LinkedOut'
            },
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AuthRoutingModule {
}
