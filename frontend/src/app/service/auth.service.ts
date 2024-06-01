import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ReCaptchaV3Service} from "ng-recaptcha";
import {UtilsService} from "./utils.service";
import {Jobseeker} from "../models/jobseeker";
import {Router} from "@angular/router";
import {AlertType} from "../shared/utils/AlertType";
import {LoginCredentials} from "../shared/auth/login-credentials";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private static _isAuthenticated = false;
    url = environment.hostUrl + '/api/auth';

    constructor(private client: HttpClient, private alert: UtilsService,
                private recaptchaV3: ReCaptchaV3Service, private router: Router) {
    }

    async isAuthenticated() {
        await this.checkAuthStatus();
        return AuthService._isAuthenticated;
    }

    async login(credentials: LoginCredentials) {
        this.client.post<LoginCredentials>(this.url + '/authenticate', credentials)
            .subscribe({
                next: (res: any) => {
                    localStorage.setItem('token', res.response);
                    this.router.navigate(['/offers']);
                },
                error: (error) => {
                    const msg: string = error.error.error;
                    if (msg.startsWith("Account is not verified yet"))
                        this.router.navigate(['/account/verify'])
                            .then(() => this.alert.alert(msg, AlertType.ERROR));
                    else this.alert.alert(msg, AlertType.ERROR)
                }
            });
    }


    checkAuthStatus(): Promise<void> {
        const token = localStorage.getItem('token');
        return new Promise((resolve, reject) => {
            this.client.get(this.url + '/status',
                {headers: {Authorization: 'Bearer ' + token}})
                .subscribe({
                    next: (res: any) => {
                        AuthService._isAuthenticated = res.authenticated;
                        resolve();
                    },
                    error: (err) => {
                        AuthService._isAuthenticated = false;
                        localStorage.removeItem('token');
                        this.alert.alert(err.error.error, AlertType.ERROR);
                        reject(err);
                    }
                });
        });
    }


    executeRecaptchaV3(action: string): Promise<string> {
        return new Promise((resolve, reject) =>
            this.recaptchaV3.execute(action)
                .subscribe({
                    next: (token) => resolve(token),
                    error: (error) => reject(error)
                }))
    }


    async logout() {
        localStorage.removeItem('token');
        AuthService._isAuthenticated = false;
    }


    async registerJobseeker(userObject: any) {
        this.client.post<Jobseeker>(this.url + '/register/jobseeker', userObject)
            .subscribe({
                next: (res: any) => {
                    if (typeof res.response === 'string')
                        this.router.navigate(['/account/verify'])
                            .then(() => this.alert.alert(res.response, AlertType.SUCCESS));

                    else console.error(res);
                },
                error: (err) => this.alert.alert(err.error.error, AlertType.ERROR)
            });
    }

    registerCompany(userObject: any) {

    }

    registerAdmin(userObject: any) {
        // TODO: Implement this functionnality in backend
    }


    async verifyEmail(code: number) {
        this.client.get(this.url + '/verify/' + code)
            .subscribe({
                next: (res: any) => {
                    if (typeof res.response === 'string') {
                        localStorage.setItem('token', res.response)
                        this.router.navigate(['/offers']).then(() =>
                            this.alert.alert('Adresse mail vérifiée.', AlertType.SUCCESS));
                    } else console.error(res);
                },
                error: (err) => this.alert.alert(err.error.error, AlertType.ERROR)
            });
    }


    async resetPassword(objectRequest: any) {
        this.client.post(this.url + '/verify/password', objectRequest)
            .subscribe({
                next: (res: any) => {
                    if (typeof res.response === 'string')
                        this.router.navigate(['/login']).then(() =>
                            this.alert.alert('Mot de passe réinitialisé. vous pouvez maintenant vous connecter avec votre nouveau mot de passe', AlertType.SUCCESS));

                    else console.error(res);
                },
                error: (err) => this.alert.alert(err.error.error, AlertType.ERROR)
            });
    }
}
