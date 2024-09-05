import {HttpInterceptorFn} from '@angular/common/http';

/**
 * Interceptor to add the JWT token to the Authorization header
 * @param req
 * @param next
 */
export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
    const token = localStorage.getItem('token');

    if (!token)
        return next(req);

    let headers = req.headers;

    // Exclude the ipify.org API from the Authorization header and the authentication endpoints (except for the status one)
    if (!req.url.includes('api.ipify.org') &&
        (!req.url.includes('auth/status')))
        headers = req.headers.set('Authorization', `Bearer ${token}`);

    const cloned = req.clone({headers});
    return next(cloned);
};
