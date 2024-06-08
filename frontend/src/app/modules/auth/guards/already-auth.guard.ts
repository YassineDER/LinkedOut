import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {Path} from "../../shared/utils/path";

export const AlreadyAuthGuard: CanActivateFn = async (route, state) => {
    const router = inject(Router)

    const isLoggedIn = localStorage.getItem('token') !== null;
    if (isLoggedIn)
        router.navigate([Path.HOME.toString()]);

    return !isLoggedIn;
};

