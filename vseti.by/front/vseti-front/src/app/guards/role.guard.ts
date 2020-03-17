import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private router: Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if ((localStorage.getItem('user_info') != null)) {
      if (state.url.includes('wizard') || state.url.includes('checkout')) {
        return true;
      } else if (state.url.includes('admin')) {
        return (JSON.parse(localStorage.getItem('user_info')).role === 'ADMIN');
      }
    } else {
      this.router.navigate(['auth/login']);
      return false;
    }
  }
}
