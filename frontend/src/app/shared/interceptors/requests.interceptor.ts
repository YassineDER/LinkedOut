import {HttpInterceptorFn} from '@angular/common/http';

export const requestsInterceptor: HttpInterceptorFn = (req, next) => {
    const token = localStorage.getItem('token');

    if (!token)
        return next(req);

    let headers = req.headers;
    if (!req.url.includes('auth') || req.url.includes('auth/status'))
        headers = req.headers.set('Authorization', `Bearer ${token}`);

    const cloned = req.clone({headers});
    return next(cloned);
};
