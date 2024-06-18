import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../../../services/auth.service";
import {Path} from "../../shared/utils/path";
import {filter, firstValueFrom} from "rxjs";

export const AuthGuard: CanActivateFn = async (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)

    const result = await firstValueFrom(auth.getUser()
        .pipe(filter((res) => res[0] !== null)));
    const isLoggedIn = result[1] && typeof result[0] === 'object';

    if (!isLoggedIn)
        router.navigate([Path.HOME_LOGIN.toString()]);
    return isLoggedIn;
};
