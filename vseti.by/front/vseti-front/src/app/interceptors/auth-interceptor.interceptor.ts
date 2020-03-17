import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!req.url.includes('/customers')) {
      req = req.clone({
        setHeaders: {
          email: JSON.parse(localStorage.getItem('user_info')).email
        }
      });
    }
    return next.handle(req);
  }
}
