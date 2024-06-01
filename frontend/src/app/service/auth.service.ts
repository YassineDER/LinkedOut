import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ReCaptchaV3Service} from "ng-recaptcha";
import {Jobseeker} from "../models/jobseeker";
import {LoginCredentials} from "../shared/auth/login-credentials";
import {User} from "../models/user";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private _isAuthenticated: boolean | undefined;
    private user: User | undefined;
    url = environment.hostUrl + '/api/auth';

    constructor(private client: HttpClient, private recaptchaV3: ReCaptchaV3Service) {
    }

    async isAuthenticated(): Promise<boolean> {
        return await this.checkAuthStatus().then((res) => this._isAuthenticated = res.authenticated)
            .catch(() => this._isAuthenticated = false);
    }

    login(credentials: LoginCredentials): Promise<string> {
        return new Promise((resolve, reject) => {
            this.client.post<LoginCredentials>(this.url + '/authenticate', credentials)
                .subscribe({
                    next: (res: any) => resolve(res.response),
                    error: (error) => reject(error)
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


    logout() {
        localStorage.removeItem('token');
        this._isAuthenticated = false;
    }


    registerJobseeker(userObject: any): Promise<string>  {
        return new Promise((resolve, reject) => {
            this.client.post<Jobseeker>(this.url + '/register/jobseeker', userObject)
                .subscribe({
                    next: (res: any) => resolve(res.response),
                    error: (err) => reject(err)
                });
        });
    }

    registerCompany(userObject: any) {
        // TODO
    }

    registerAdmin(userObject: any) {
        // TODO: Implement this functionnality in backend
    }


    verifyEmail(code: number): Promise<string> {
        return new Promise((resolve, reject) => {
            this.client.get(this.url + '/verify/' + code)
                .subscribe({
                    next: (res: any) => resolve(res.response),
                    error: (err) => reject(err)
                });
        });
    }


    resetPassword(objectRequest: any): Promise<string> {
        return new Promise((resolve, reject) => {
            this.client.post(this.url + '/verify/password', objectRequest)
                .subscribe({
                    next: (res: any) => resolve(res.response),
                    error: (err) => reject(err)
                });
        });
    }

    requestPasswordReset(objectRequest: any): Promise<string> {
        return new Promise((resolve, reject) => {
            this.client.post(this.url + '/reset-password', objectRequest)
                .subscribe({
                    next: (success: any) => resolve(success),
                    error: (err) => reject(err)
                });
        });
    }

    private checkAuthStatus(): Promise<any> {
        const token = localStorage.getItem('token');
        return new Promise((resolve, reject) => {
            this.client.get(this.url + '/status', {headers: {Authorization: 'Bearer ' + token}})
                .subscribe({
                    next: (res: any) => resolve(res),
                    error: (err) => {
                        localStorage.removeItem('token');
                        reject(err);
                    }
                });
        });
    }

    async getUser(): Promise<User> {
        return await this.checkAuthStatus().then((res) => this.user = res.principal)
            .catch(() => this.user = undefined);
    }
}
