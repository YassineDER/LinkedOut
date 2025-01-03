import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {LoginCredentials} from "../features/authentication/utils/login-credentials";
import {Role} from "../models/role";
import {BehaviorSubject, catchError, Observable, of, switchMap} from "rxjs";
import {User} from "../models/user";
import {AuthResponse} from "../features/shared/utils/auth-response";
import {HttpClientError} from "../features/shared/utils/http-client.error";

/**
 * Service to handle the authentication system of the application
 */
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
    login(credentials: LoginCredentials): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.post<AuthResponse>(this.url + '/authenticate', credentials)
                .subscribe(this.handleAuthResponse(resolve, reject));
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
    register(userObject: any, entity: Role): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.post<AuthResponse>(this.url + '/register/' + entity.toLowerCase(), userObject)
                .subscribe(this.handleAuthResponse(resolve, reject))
        });
    }

    /**
     * Verify the email of the userSubject with the given code.
     * @param code The code received by email
     */
    verifyEmail(code: number): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.get<AuthResponse>(this.url + '/verify/' + code)
                .subscribe(this.handleAuthResponse(resolve, reject))
        });
    }

    /**
     * Reset the password of the userSubject with the given object.
     * @param objectRequest The object containing the password, its confirmation and the code received by email.
     * @return A promise containing the response of the server (success or error message)
     */
    resetPassword(objectRequest: any): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.post<AuthResponse>(this.url + '/verify/password', objectRequest)
                .subscribe(this.handleAuthResponse(resolve, reject))
        });
    }

    /**
     * Request a password reset for the userSubject with the given object.
     * @param objectRequest The object containing the email of the userSubject
     */
    requestPasswordReset(objectRequest: any): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.post<AuthResponse>(this.url + '/reset-password', objectRequest)
                .subscribe(this.handleAuthResponse(resolve, reject))
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
     * Helper function to format the response of the server as a promise.
     */
    private handleAuthResponse(resolve: (value: AuthResponse) => void, reject: (reason: HttpClientError) => void) {
        return {
            next: (res: AuthResponse) => resolve(res),
            error: (err: HttpClientError) => reject(err)
        }
    }

    /**
     * Simulate a long request to the server.
     */
    simulateLongRequest() :Promise<AuthResponse>{
        return new Promise((resolve, reject) => {
            this.http.get<AuthResponse>(this.url + '/sleep')
                .subscribe(this.handleAuthResponse(resolve, reject));
        });
    }

}
