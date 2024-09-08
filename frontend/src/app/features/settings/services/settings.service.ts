import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthResponse} from "../../shared/utils/auth-response";
import {UserService} from "../../home/services/user.service";
import {HttpClientError} from "../../shared/utils/http-client.error";
import {LoginCredentials} from "../../authentication/utils/login-credentials";

@Injectable()
export class SettingsService {

    constructor(private http: HttpClient, private users: UserService) {
    }

    requestMfa(): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.get<AuthResponse>(this.users.api + '/request-mfa')
                .subscribe({
                    next: (response: AuthResponse) => resolve(response),
                    error: (error: HttpClientError) => reject(error)
                })
        });
    }


    mfa(action: string, credentials: LoginCredentials): Promise<AuthResponse> {
        return new Promise((resolve, reject) => {
            this.http.post<AuthResponse>(this.users.api + '/mfa/' + action, credentials)
                .subscribe({
                    next: (response: AuthResponse) => resolve(response),
                    error: (error: HttpClientError) => reject(error)
                })
        });
    }
}
