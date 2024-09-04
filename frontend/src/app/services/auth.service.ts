import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {LoginCredentials} from "../features/authentication/utils/login-credentials";
import {Role} from "../models/role";
import {BehaviorSubject, catchError, Observable, of, switchMap} from "rxjs";
import {User} from "../models/user";

/**
 * Service to handle the authentication system of the application
 */
@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private userSubject = new BehaviorSubject<User | null>(null);
    url = environment.hostUrl + '/api/authentication';

    constructor(private http: HttpClient) {
    }

    get user() {
        return this.userSubject.asObservable();
    }

    /**
     * Get the authenticated userSubject from the server.
     * @return An observable containing the userSubject, or null if the userSubject is not authenticated.
     */
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

    /**
     * Login the userSubject with the given credentials.
     * @param credentials The credentials of the userSubject
     * @see LoginCredentials
     */
    login(credentials: LoginCredentials): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post<LoginCredentials>(this.url + '/authenticate', credentials)
                .subscribe(this.handleResponse(resolve, reject));
        });
    }

    /**
     * Logout the userSubject from the application by removing the token from the local storage.
     */
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

    /**
     * Request a password reset for the userSubject with the given object.
     * @param objectRequest The object containing the email of the userSubject
     */
    requestPasswordReset(objectRequest: any): Promise<string> {
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/reset-password', objectRequest)
                .subscribe(this.handleResponse(resolve, reject))
        });
    }

    /**
     * Check the authentication status of the userSubject.
     * @return An observable containing the userSubject, or an error if the userSubject is not authenticated.
     * @see User
     */
    private checkAuthStatus(): Observable<User> {
        return this.http.get<User>(environment.hostUrl + '/api/user/status/v2');
    }

    /**
     * Format the response of the server to a promise.
     */
    private handleResponse(resolve: (value: any) => void, reject: (reason: any) => void) {
        return {
            next: (res: any) => resolve(res.response),
            error: (err: any) => reject(err)
        }
    }

    /**
     * Simulate a long request to the server.
     */
    simulateLongRequest() :Promise<string>{
        return new Promise((resolve, reject) => {
            this.http.get(this.url + '/sleep')
                .subscribe(this.handleResponse(resolve, reject));
        });
    }

}
