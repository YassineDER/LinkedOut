import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {SnackBarService} from "../service/snack-bar.service";

export const LoginGuard: CanActivateFn = (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const snack = inject(SnackBarService)

    // if (!auth.isAuthenticated()) {
    //     router.navigateByUrl('/login')
    //     return false
    // }
    return true
};
