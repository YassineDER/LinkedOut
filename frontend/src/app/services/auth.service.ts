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

    constructor(private http: HttpClient, private recaptchaV3: ReCaptchaV3Service) {
    }

    async isAuthenticated(): Promise<boolean> {
        return await this.checkAuthStatus().then((res) => this._isAuthenticated = res.authenticated)
            .catch(() => this._isAuthenticated = false);
    }

    login(credentials: LoginCredentials): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post<LoginCredentials>(this.url + '/authenticate', credentials)
                .subscribe(this.handleResponse(resolve, reject))
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

    logout(): Promise<void> {
        return new Promise((resolve, reject) => {
            localStorage.removeItem('token');
            this._isAuthenticated = false;
            this.user = undefined;
            resolve();
        });
    }

    registerJobseeker(userObject: any): Promise<string>  {
        return new Promise((resolve, reject) => {
            this.http.post<Jobseeker>(this.url + '/register/jobseeker', userObject)
                .subscribe(this.handleResponse(resolve, reject))
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
            this.http.get(this.url + '/verify/' + code)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }


    resetPassword(objectRequest: any): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/verify/password', objectRequest)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    requestPasswordReset(objectRequest: any): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/reset-password', objectRequest)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    async getUser(): Promise<User> {
        return await this.checkAuthStatus().then((res) => this.user = res.principal)
            .catch(() => this.user = undefined);
    }

    private checkAuthStatus(): Promise<any> {
        return new Promise((resolve, reject) => {
            this.http.get(this.url + '/status')
                .subscribe({
                    next: (res: any) => resolve(res),
                    error: (err) => {
                        localStorage.removeItem('token');
                        reject(err);
                    }
                });
        });
    }

    private handleResponse(resolve: (value: any) => void, reject: (reason: any) => void) {
        return {
            next: (res: any) => resolve(res.response),
            error: (err: any) => reject(err)
        }
    }

    simulateLongRequest() :Promise<string>{
        return new Promise((resolve, reject) => {
            this.http.get(this.url + '/sleep', {responseType: 'text'})
                .subscribe({
                    next: (res: any) => resolve(res as string),
                    error: (err) => reject(err)
                });
        });
    }
}
