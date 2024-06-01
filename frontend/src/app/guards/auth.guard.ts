import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {UtilsService} from "../service/utils.service";
import {AlertType} from "../shared/utils/AlertType";

export const AuthGuard: CanActivateFn = async (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const utils = inject(UtilsService)

    const isLoggedIn = await auth.isAuthenticated();

    if (!isLoggedIn) {
        router.navigate(['/login'])
            .then(() => utils.alert('You must be logged in to access this page', AlertType.ERROR))
    }

    return isLoggedIn;
};
