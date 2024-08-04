import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {LoginCredentials} from "../modules/auth/utils/login-credentials";
import {Role} from "../models/role";
import {BehaviorSubject, catchError, Observable, of, switchMap} from "rxjs";
import {User} from "../models/user";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private userSubject = new BehaviorSubject<User | null>(null);
    url = environment.hostUrl + '/api/auth';

    constructor(private http: HttpClient) {
    }

    get user() {
        return this.userSubject.asObservable();
    }

    getAuthenticatedUser(): Observable<User | null> {
        return this.checkAuthStatus().pipe(
            switchMap((user: User) => {
                this.userSubject.next(user);
                return of(user);
            }),
            catchError(() => {
                this.logout();
                return of(null);
            })
        );
    }

    login(credentials: LoginCredentials): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post<LoginCredentials>(this.url + '/authenticate', credentials)
                .subscribe(this.handleResponse(resolve, reject));
        });
    }

    logout() {
        localStorage.removeItem('token');
        this.userSubject.next(null);
        window.location.reload();
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
        return this.http.get(environment.hostUrl + '/api/user/status/v2');
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
