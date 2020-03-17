import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserModel} from '../../../models/user.model';
import {OrderModel} from '../../../models/order.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthFormService {

  constructor(private http: HttpClient) {
  }
  addUser(user: UserModel): Promise<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return  new Promise((resolve, reject) => {
      this.http.get<UserModel>(`/shop/api/v1/customers/${user.email}`).subscribe(
        result => {
          reject('That email is taken, please try another');
        },
        error => {
          this.http.post<UserModel>('/shop/api/v1/customers', JSON.stringify(user), {headers}).subscribe();
          resolve(user);
        }
        );
    });
  }
  loginUser(user: UserModel): Promise<any> {
    return  new Promise((resolve, reject) => {
      // this.http.get<UserModel>(`/shop/api/v1/customers/${user.email}`).subscribe(
      //   result => {
      //     if (result.password === user.password) {
      //       resolve(result);
      //     } else {
      //       reject('Wrong password');
      //     }
      //   },
      //   error => {
      //     reject(`No user with email ${user.email}`);
      //     }
      //   );
      this.http.get<UserModel>(`/shop/api/v1/customers/login?email=${user.email}&password=${user.password}`).subscribe(
        result => {
          resolve(result);
        },
        error => {
          reject(`Your email or password is incorrect.`);
        }
      );
    });
  }

  getLastOrderByEmailAndOrderStatus(email: string, orderStatus: string): Observable<OrderModel> {
    return this.http.get<OrderModel>( `/shop/api/v1/orders/email/${email}?order-status=${orderStatus}`);
  }
}
