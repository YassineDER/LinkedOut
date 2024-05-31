import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ReCaptchaV3Service} from "ng-recaptcha";
import {FormGroup} from "@angular/forms";
import {Role} from "../models/role";
import {AlertService} from "./alert.service";
import {Jobseeker} from "../models/jobseeker";
import {Router} from "@angular/router";
import {AlertType} from "../shared/utils/AlertType";
import {LoginCredentials} from "../shared/auth/login-credentials";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private _isAuthenticated = false;
    url = environment.hostUrl + '/api/auth';

    constructor(private client : HttpClient, private alert: AlertService,
                private recaptchaV3: ReCaptchaV3Service, private router: Router) {
        this.checkAuthStatus();
    }

    get isAuthenticated() {
        return this._isAuthenticated;
    }

    login(credentials: LoginCredentials) {
        this.client.post(this.url + '/authenticate', credentials)
            .subscribe({
                next: (res: any) => {
                    localStorage.setItem('token', res.response);
                    this.router.navigate(['/offers']);
                },
                error: (error) => {
                    const msg: string = error.error.error;
                    if (msg.startsWith("Account is not verified yet"))
                        this.router.navigate(['/account/verify'])
                            .then(() => this.alert.show(msg, AlertType.ERROR));
                    else this.alert.show(error.error.error, AlertType.ERROR)
                }
            });
    }


    checkAuthStatus() {
        const token = localStorage.getItem('token');
        this.client.get(this.url + '/status',
            {headers: {Authorization: 'Bearer ' + token}})
            .subscribe({
                next: (response: any) => this._isAuthenticated = (response.name !== 'anonymousUser') && token !== null,
                error: (err) => {
                    this._isAuthenticated = false;
                    localStorage.removeItem('token');
                    this.alert.show(err.error.error, AlertType.ERROR);
                }
            });

        console.log()
    }


    executeRecaptchaV3(action: string): Promise<string> {
        return new Promise((resolve, reject) =>
            this.recaptchaV3.execute(action)
                .subscribe({
                    next: (token) => resolve(token),
                    error: (error) => reject(error)
                }))
    }


    logout() {
        localStorage.removeItem('token');
        this.checkAuthStatus();
    }


    registerJobseeker(userObject: any) {
        const jobseeker: Jobseeker = userObject;
        this.client.post(this.url + '/register/jobseeker', jobseeker)
            .subscribe({
                next: (res: any) => {
                    if (typeof res.response === 'string')
                        this.router.navigate(['/account/verify'])
                            .then(() => this.alert.show(res.response, AlertType.SUCCESS));

                    console.error(res);
                },
                error: (err) => this.alert.show(err.error.error, AlertType.ERROR)
            });
    }

    registerCompany(userObject: any) {

    }

    registerAdmin(userObject: any) {
        // TODO: Implement this functionnality in backend
    }


    verifyEmail(code: number) {
        this.client.get(this.url + '/verify/' + code)
            .subscribe({
                next: (res: any) => {
                    if (typeof res.response === 'string') {
                        localStorage.setItem('token', res.response)
                        this.router.navigate(['/offers']).then(() =>
                            this.alert.show('Adresse mail vérifiée.', AlertType.SUCCESS));
                    }
                    else console.error(res);
                },
                error: (err) => this.alert.show(err.error.error, AlertType.ERROR)
            });
    }


    resetPassword(objectRequest: any) {
        this.client.post(this.url + '/verify/password', objectRequest)
            .subscribe({
                next: (res: any) => {
                    if (typeof res.response === 'string')
                        this.router.navigate(['/login']).then(() =>
                            this.alert.show('Mot de passe réinitialisé. vous pouvez maintenant vous connecter avec votre nouveau mot de passe', AlertType.SUCCESS));

                    console.error(res);
                },
                error: (err) => this.alert.show(err.error.error, AlertType.ERROR)
            });
    }
}
