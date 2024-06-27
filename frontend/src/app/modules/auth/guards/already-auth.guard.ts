import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {Path} from "../../shared/utils/path";
import {JwtService} from "../../../services/jwt.service";

export const AlreadyAuthGuard: CanActivateFn = async (route, state) => {
    const router = inject(Router)
    const jwt = inject(JwtService)

    const token = localStorage.getItem('token');
    const loggedIn = token && !jwt.isTokenExpired(token);

    if (loggedIn)
        await router.navigate([Path.HOME.toString()]);

    return !loggedIn as boolean;
};
