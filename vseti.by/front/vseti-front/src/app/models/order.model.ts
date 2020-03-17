import {OrderItemModel} from './order-item.model';

export interface OrderModel {
  id: number;
  orderItems: OrderItemModel[];
  email: string;
  timeStamp: number;
  paymentStatus: string;
  orderStatus: string;
}
