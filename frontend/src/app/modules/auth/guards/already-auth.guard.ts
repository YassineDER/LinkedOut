import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {Path} from "../../shared/utils/path";
import {filter, firstValueFrom} from "rxjs";
import {AuthService} from "../../../services/auth.service";

export const AlreadyAuthGuard: CanActivateFn = async (route, state) => {
    const router = inject(Router)
    const auth = inject(AuthService)

    const result = await firstValueFrom(auth.getUser()
        .pipe(filter((res) => res[0] !== null)));
    const isLoggedIn = result[1] && typeof result[0] === 'object';

    if (isLoggedIn)
        router.navigate([Path.HOME.toString()]);

    return !isLoggedIn;
};

