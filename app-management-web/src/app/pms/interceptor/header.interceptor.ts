import { Injectable } from '@angular/core';
import {
    HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders
} from '@angular/common/http';

@Injectable()
export class HeaderInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler) {
        const modifieReq = req.clone({
            headers: new HttpHeaders().set('content-type', 'application/json')
        });
        return next.handle(modifieReq);
    }
}
