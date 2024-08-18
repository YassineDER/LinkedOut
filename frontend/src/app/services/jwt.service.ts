import {Injectable} from '@angular/core';
import {jwtDecode, JwtPayload} from 'jwt-decode';

@Injectable({
    providedIn: 'root'
})
export class JwtService {
    constructor() {}

    /**
     * Get the decoded token
     * @param token The JWT token
     */
    private getDecodedToken(token: string): JwtPayload {
        return jwtDecode(token);
    }

    /**
     * Check if the token is expired
     * @param token The JWT token
     */
    isTokenExpired(token: string): boolean {
        const decodedToken = this.getDecodedToken(token);
        if (!decodedToken.exp)
            return true;

        const currentTime = Math.floor(new Date().getTime() / 1000);
        return decodedToken.exp < currentTime;
    }
}
