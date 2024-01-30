import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {SnackBarService} from "../service/snack-bar.service";

export const loginGuard: CanActivateFn = (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const snack = inject(SnackBarService)

    if (!auth.isAuthenticated()) {
        router.navigateByUrl('/login')
            .then(r => snack.alert('You must be logged in to access this page', 'OK'))
        return false
    }
    return true
};
