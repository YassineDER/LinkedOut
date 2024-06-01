import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterCompanyComponent} from "./components/auth/register/register-company/register-company.component";
import {ResetPasswordComponent} from "./components/auth/reset-password/reset-password.component";
import {AuthGuard} from "./guards/auth.guard";
import {ConfirmationComponent} from "./components/auth/confirmation/confirmation.component";
import {HomeComponent} from "./components/home/home.component";
import {OffersComponent} from "./components/offers/offers.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterJobseekerComponent} from "./components/auth/register/register-jobseeker/register-jobseeker.component";
import {AlreadyAuthGuard} from "./guards/already-auth.guard";

const routes: Routes = [
    {path: 'login', component: LoginComponent, data: {animation: 'LoginPage'}},
    {path: 'register', children: [
            {path: '', redirectTo: 'jobseeker', pathMatch: 'full'},
            {path: 'register/company', component: RegisterCompanyComponent, data: {animation: 'RegisterCompanyPage'}},
            {path: 'register/jobseeker', component: RegisterJobseekerComponent, data: {animation: 'RegisterJobseekerPage'}},
        ]},
    {path: 'request-password-reset', component: ResetPasswordComponent, data: {animation: 'ResetPasswordRequestPage'}},
    {path: 'password/reset', component: ConfirmationComponent, data: {animation: 'ResetPasswordPage'}},
    {path: 'account/verify', component: ConfirmationComponent, data: {animation: 'EmailVerificationPage'}},
    {path: '', component: HomeComponent, data: {animation: 'HomePage'}},
    {path: 'offers', canActivate: [AuthGuard], component: OffersComponent, data: {animation: 'OffersPage'}},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], data: {animation: 'ProfilePage'}},

    {path: '**', component: NotFoundComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
