import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {UserModel} from '../../models/user.model';
import {forkJoin, Observable} from 'rxjs';
import {LoaderService} from '../../services/loader-service.service';
import {OrderItemModel} from '../../models/order-item.model';
import {takeUntil} from 'rxjs/operators';
import {OrderModel} from '../../models/order.model';
import {UpdateService} from '../shared/services/update-service.service';
import {RxUnsubscribe} from '../../classes/rx-unsubscribe';
import {HttpService} from '../shared/services/http-service.service';
import {MatDialog} from '@angular/material';
import {DialogComponent} from '../../components/dialog/dialog.component';
import {DialogType} from '../../models/dialog-data.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.less']
})
export class CheckoutComponent extends RxUnsubscribe implements OnInit {
  orderItems: OrderItemModel[];
  totalSum = 0.0;
  deliveryForm: FormGroup = null;
  user: UserModel;
  isLoading: Observable<boolean> = this.loaderService.isLoading;
  constructor( private loaderService: LoaderService, private updateService: UpdateService, private http: HttpService,
               private dialog: MatDialog, private router: Router) {
    super();
  }

  ngOnInit() {
    this.isLoading.subscribe();
    this.user = JSON.parse(localStorage.getItem('user_info'));
    this.updateService.getMessageToUpdate()
      .pipe(
        takeUntil((this.destroy$))
      )
      .subscribe(value => {
        if (value === true) {
          this.reloadOrder();
        }
      });
    this.reloadOrder();
  }
  isBasketEmpty(): boolean {
    const lastOrder = localStorage.getItem('last_order');
    if (lastOrder) {
      return JSON.parse(lastOrder).numItems === 0;
    } else {
      return true;
    }
  }
  reloadOrder(): void {
    if (localStorage.getItem('last_order')) {
      this.http.getOrderById(JSON.parse(localStorage.getItem('last_order')).id)
        .pipe(
          takeUntil((this.destroy$))
        )
        .subscribe(
          (order: OrderModel) => {
            this.orderItems = order.orderItems;
            this.totalSum = this.orderItems.reduce((curSum, orderItem) => curSum + orderItem.price, 0);
          }
        );
    }
  }
  emitForm($event) {
    this.deliveryForm = $event;
    console.log($event);
  }

  confirmOrder() {
    const orderId = JSON.parse(localStorage.getItem('last_order')).id;
    let paymentType = 'CASH';
    switch (this.deliveryForm.get('paymentType').value) {
      case 'cash':
        paymentType = 'CASH';
        break;
      case 'credit card':
        paymentType = 'CREDIT_CARD';
        break;
    }
    // const responseAddress = this.http.changeDeliveryAddress(orderId, this.deliveryForm.get('address').value);
    // const responsePhone = this.http.changeContactNumber(orderId, this.deliveryForm.get('phone').value);
    // const responsePayment = this.http.changePaymentType(orderId, paymentType);
    // forkJoin([responseAddress, responsePhone, responsePayment]).subscribe(
    //   order =>  this.http.changeOrderStatus(orderId, 'CONFIRMED').subscribe(
    //     confirmedOrder => {
    //       this.dialog.open(DialogComponent, {
    //         data: {message: 'Order successfully confirmed', type: DialogType.success}
    //       }).afterClosed().subscribe(() => {
    //         localStorage.removeItem('last_order');
    //         this.router.navigate(['wizard']);
    //       });
    //     }
    //   )
    // );
    this.http.changeDeliveryAddress(orderId, this.deliveryForm.get('address').value).subscribe(
      addressResp => this.http.changeContactNumber(orderId, this.deliveryForm.get('phone').value).subscribe(
        phoneResp => this.http.changePaymentType(orderId, paymentType).subscribe(
          typeResp => this.http.changeOrderStatus(orderId, 'CONFIRMED').subscribe(
            confirmedOrder => {
              this.dialog.open(DialogComponent, {
                data: {message: 'Order successfully confirmed', type: DialogType.success}
              }).afterClosed().subscribe(() => {
                localStorage.removeItem('last_order');
                this.router.navigate(['wizard']);
              });
            }
          )
        )
      )
    );
  }
  cancelOrder() {
    console.log('cancel');
    this.http.changeOrderStatus(JSON.parse(localStorage.getItem('last_order')).id, 'CANCELED').subscribe(
      order => {
        localStorage.removeItem('last_order');
        this.router.navigate(['wizard']);
      }
    );
  }
}
