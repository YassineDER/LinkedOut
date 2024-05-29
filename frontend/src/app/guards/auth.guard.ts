import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {AlertService} from "../service/alert.service";
import {AlertType} from "../shared/utils/AlertType";

export const LoginGuard: CanActivateFn = (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)
    const alert = inject(AlertService)

    if(route.component )

    const canAccess = auth.checkAuthStatus();
    if (!canAccess)
        router.navigate(['/login']).then(() => alert.show('Vous devez être connecté pour accéder à cette page', AlertType.ERROR));

    return canAccess;
};
