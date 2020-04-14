import {Component, Input, OnInit} from '@angular/core';
import {OrderItemModel} from '../../../../models/order-item.model';
import {HttpService} from '../../../shared/services/http-service.service';
import {UpdateService} from '../../../shared/services/update-service.service';
import {NumberOrderItemsService} from '../../../../services/number-order-items.service';
import {MatSnackBar} from '@angular/material';
import {NotificationComponent} from '../../../shared/components/notification/notification.component';

@Component({
  selector: 'app-basket-elem',
  templateUrl: './basket-element.component.html',
  styleUrls: ['./basket-element.component.less']
})
export class BasketElementComponent implements OnInit {
  @Input()
  item: OrderItemModel;
  @Input()
  role: string;
  constructor(private http: HttpService, private updateService: UpdateService, private numberItemsService: NumberOrderItemsService,
              private notification: MatSnackBar) {
  }
  ngOnInit(): void {
  }
  showDeleteFromBasketNotification() {
    this.notification.openFromComponent(NotificationComponent, {
      verticalPosition: 'bottom',
      horizontalPosition: 'right',
      duration: 3000,
      panelClass: ['notification'],
      data: {title: 'Deleted from basket', text: this.item.description}
    });
  }
  deleteItem() {
    console.log('delete item' + this.item.id);
    this.http.deleteOrderItemFromOrder(JSON.parse(localStorage.getItem('last_order')).id, this.item.id).subscribe(order => {
      localStorage.setItem('last_order', JSON.stringify({id: order.id, numItems: order.orderItems.length}));
      this.showDeleteFromBasketNotification();
      this.updateService.sendMessageToUpdate(true);
      this.numberItemsService.sendNewNumber(order.orderItems.length);
      console.log('deleted');
    });
  }
}
