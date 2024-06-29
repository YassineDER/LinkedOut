import {HttpInterceptorFn} from '@angular/common/http';

export const requestsInterceptor: HttpInterceptorFn = (req, next) => {
    const token = localStorage.getItem('token');

    if (!token)
        return next(req);

    let headers = req.headers;

    // Exclude the ipify.org API from the Authorization header and the auth endpoints (except for the status one)
    if (!req.url.includes('api.ipify.org') &&
        (!req.url.includes('auth') || req.url.includes('auth/status')))
        headers = req.headers.set('Authorization', `Bearer ${token}`);

    const cloned = req.clone({headers});
    return next(cloned);
};
