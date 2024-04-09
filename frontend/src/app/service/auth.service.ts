import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private _isAuthenticated = false;

    constructor() {}

    login() {
        this._isAuthenticated = true;
    }

    logout() {
        this._isAuthenticated = false;
    }

    isAuthenticated() {
        return this._isAuthenticated;
    }
}
