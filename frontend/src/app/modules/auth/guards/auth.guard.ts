import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {Path} from "../../shared/utils/path";
import {JwtService} from "../../../services/jwt.service";
import {AuthService} from "../../../services/auth.service";

export const AuthGuard: CanActivateFn = async (route, state) => {
    const router = inject(Router)
    const jwt = inject(JwtService)
    const auth = inject(AuthService)

    const token = localStorage.getItem('token');
    const isLoggedIn = token && !jwt.isTokenExpired(token);

    if (!isLoggedIn)
        await router.navigate([Path.LOGIN.toString()]);

    return isLoggedIn as boolean;
};
