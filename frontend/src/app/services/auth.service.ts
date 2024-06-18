import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ReCaptchaV3Service} from "ng-recaptcha";
import {LoginCredentials} from "../modules/auth/utils/login-credentials";
import {Role} from "../models/role";
import {BehaviorSubject, catchError, map, Observable, of, switchMap} from "rxjs";
import {User} from "../models/user";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private userSubject = new BehaviorSubject<User | null>(null);
    url = environment.hostUrl + '/api/auth';

    constructor(private http: HttpClient, private recaptchaV3: ReCaptchaV3Service) {
    }


    getUser(): Observable<[User | null, boolean]> {
        return this.checkAuthStatus().pipe(
            switchMap((res) => {
                this.userSubject.next(res.principal);
                return of<[User | null, boolean]>([res.principal, res.authenticated]);
            }),
            catchError(() => {

                return of<[User | null, boolean]>([null, false]);
            })
        );
    }

    login(credentials: LoginCredentials): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post<LoginCredentials>(this.url + '/authenticate', credentials)
                .subscribe(this.handleResponse(resolve, reject));
        });
    }

    logout(): Promise<void> {
        return new Promise((resolve, reject) => {
            localStorage.removeItem('token');
            this.userSubject.next(null);
            resolve();
        });
    }


    /**
     * Register a userSubject with the given object and role.
     * @param userObject The object containing the userSubject information
     * @param entity The role of the userSubject
     */
    register(userObject: any, entity: Role): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/register/' + entity.toLowerCase(), userObject)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    /**
     * Verify the email of the userSubject with the given code.
     * @param code The code received by email
     */
    verifyEmail(code: number): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.get(this.url + '/verify/' + code)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    /**
     * Reset the password of the userSubject with the given object.
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

    private checkAuthStatus(): Observable<any> {
        return this.http.get(this.url + '/status');
    }

    private handleResponse(resolve: (value: any) => void, reject: (reason: any) => void) {
        return {
            next: (res: any) => resolve(res.response),
            error: (err: any) => reject(err)
        }
    }

    executeRecaptchaV3(action: string): Promise<string> {
        return new Promise((resolve, reject) =>
            this.recaptchaV3.execute(action)
                .subscribe({
                    next: (token) => resolve(token),
                    error: (error) => reject(error)
                }))
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
