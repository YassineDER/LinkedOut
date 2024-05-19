import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {AlertService} from "../service/alert.service";

export const LoginGuard: CanActivateFn = (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const snack = inject(AlertService)

    // if (!auth.isAuthenticated()) {
    //     router.navigateByUrl('/login')
    //     return false
    // }
    return true
};
