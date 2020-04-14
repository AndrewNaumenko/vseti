import {Component, Input, OnInit} from '@angular/core';
import {OrderItemModel} from '../../../../models/order-item.model';
import {RxUnsubscribe} from '../../../../classes/rx-unsubscribe';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.less']
})
export class BasketComponent extends RxUnsubscribe implements OnInit {
  @Input()
  orderItems: OrderItemModel[];
  @Input()
  totalSum = 0.0;
  @Input()
  role: string;
  constructor() {
    super();
    console.log(this.orderItems);
  }
  ngOnInit(): void {
  }
}
