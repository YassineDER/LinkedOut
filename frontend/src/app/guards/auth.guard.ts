import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../sub-modules/auth/services/auth.service";
import {AlertType} from "../shared/utils/AlertType";
import {UtilsService} from "../services/utils.service";

export const AuthGuard: CanActivateFn = async (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const utils = inject(UtilsService)

    const isLoggedIn = await auth.isAuthenticated();
    if (!isLoggedIn)
        router.navigate(['/login'])
            .then(() => utils.alert('You must be logged in to access this page', AlertType.ERROR))
    console.log("called")
    return isLoggedIn;
};
