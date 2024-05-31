import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {AlertService} from "../service/alert.service";

export const AuthGuard: CanActivateFn = (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const alert = inject(AlertService)

    return auth.isAuthenticated;
};
