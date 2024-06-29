import {HttpEvent, HttpHandlerFn, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {inject} from "@angular/core";
import {UtilsService} from "../../../services/utils.service";

export function ipInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    const utils = inject(UtilsService);
    if (utils.ip)
        req = req.clone({
            setHeaders: {"X-FORWARDED-FOR": utils.ip}
        });

    return next(req);
}
