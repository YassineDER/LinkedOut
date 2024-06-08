import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../../../services/auth.service";
import {AlertType} from "../../shared/utils/alert-type";
import {UtilsService} from "../../../services/utils.service";
import {Path} from "../../shared/utils/path";

export const AuthGuard: CanActivateFn = async (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)

    const isLoggedIn = await auth.isAuthenticated();
    if (!isLoggedIn)
        router.navigate([Path.HOME_LOGIN.toString()]);
    return isLoggedIn;
};
