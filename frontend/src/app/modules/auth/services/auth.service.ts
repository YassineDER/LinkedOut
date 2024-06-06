import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ReCaptchaV3Service} from "ng-recaptcha";
import {LoginCredentials} from "../sub-modules/auth/utils/login-credentials";
import {User} from "../models/user";
import {Role} from "../models/role";

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


    /**
     * Register a user with the given object and role.
     * @param userObject The object containing the user information
     * @param entity The role of the user
     */
    register(userObject: any, entity: Role): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/register/' + entity.toString(), userObject)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    /**
     * Verify the email of the user with the given code.
     * @param code The code received by email
     */
    verifyEmail(code: number): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.get(this.url + '/verify/' + code)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    /**
     * Reset the password of the user with the given object.
     * @param objectRequest The object containing the password, its confirmation and the code received by email.
     * @return A promise containing the response of the server (success or error message)
     */
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
