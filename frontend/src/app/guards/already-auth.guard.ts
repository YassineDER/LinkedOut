import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {UtilsService} from "../service/utils.service";
import {AlertType} from "../shared/utils/AlertType";

export const AlreadyAuthGuard: CanActivateFn = async (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)


    const isLoggedIn = await auth.isAuthenticated();
    if (isLoggedIn) {
        auth.getUser()
            .then((user) => router.navigate(['/offers'])
                .then(() => console.log('Already logged in as ' + user.username)))
    }

    return !isLoggedIn;
};

