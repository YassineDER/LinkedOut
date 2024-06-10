import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../../../services/auth.service";
import {Path} from "../../shared/utils/path";
import {filter, firstValueFrom, lastValueFrom} from "rxjs";

export const AuthGuard: CanActivateFn = async (route, state) => {
    const auth = inject(AuthService)
    const router = inject(Router)

    const result = await firstValueFrom(auth.getUser()
        .pipe(filter(res => res !== null)))
    const isLoggedIn = typeof result === 'object';

    if (!isLoggedIn)
        router.navigate([Path.HOME_LOGIN.toString()]);
    return isLoggedIn;
};
