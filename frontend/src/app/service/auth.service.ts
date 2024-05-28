import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ReCaptchaV3Service} from "ng-recaptcha";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private _isAuthenticated = false;
    url = environment.hostUrl + '/api/auth';

    constructor(private client : HttpClient, private recaptchaV3: ReCaptchaV3Service) {}

    login() {

    }

    register() {

    }

    executeRecaptchaV3(action: string): Promise<string> {
        return new Promise((resolve, reject) =>
            this.recaptchaV3.execute(action)
                .subscribe({
                    next: (token) => resolve(token),
                    error: (error) => reject(error)})
        )
    }


    logout() {
        this._isAuthenticated = false;
    }

    isAuthenticated() {
        return this._isAuthenticated;
    }
}
