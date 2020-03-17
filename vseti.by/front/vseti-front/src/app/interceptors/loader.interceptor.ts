import {LoaderService} from '../services/loader-service.service';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError, finalize, skip, tap} from 'rxjs/operators';
import {MatDialog} from '@angular/material';
import {DialogComponent} from '../components/dialog/dialog.component';
import {DialogType} from '../models/dialog-data.model';

@Injectable()
export class LoaderInterceptor implements HttpInterceptor {
  constructor(public loaderService: LoaderService, private dialog: MatDialog) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!(req.url.includes('/categories') || req.url.includes('/processor'))) {
      this.loaderService.show();
      console.log('loader interceptor');
      return next.handle(req).pipe(
        catchError((err: any) => {
          return this.errorProcessor(next, err);
        }),
        finalize(() => setTimeout(() => this.loaderService.hide(), 1000))
      );
    }
    return next.handle(req).pipe(
      catchError((err: any) => {
        return this.errorProcessor(next, err);
      }));
  }
  errorProcessor(next: HttpHandler, err: any): Observable<HttpEvent<any>> {
    if (err.status >= 500) {
      console.log('server error');
      console.log(err);
      this.dialog.open(DialogComponent, {
        data: {message: err.statusText, type: DialogType.error}
      });
      return of(err);
    }
    return next.handle(err);
  }
}
