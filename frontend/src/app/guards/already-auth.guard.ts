import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";

export const AlreadyAuthGuard: CanActivateFn = (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)

    return true;
    // return !auth.checkAuthStatus();
};

