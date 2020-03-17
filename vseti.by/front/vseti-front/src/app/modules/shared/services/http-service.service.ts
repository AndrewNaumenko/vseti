import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {OfferModel} from '../../../models/offer.model';
import {HttpClient} from '@angular/common/http';
import {CategoryModel} from '../../../models/category.model';
import {OrderModel} from '../../../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<CategoryModel[]> {
    return this.http.get<CategoryModel[]>('/catalog/api/v1/categories');
  }

  getCategoryById(id: number): Observable<CategoryModel> {
    return this.http.get<CategoryModel>('/catalog/api/v1/categories/id/' + id);
  }

  getCategoryByName(name: string): Observable<CategoryModel> {
    return this.http.get<CategoryModel>('/catalog/api/v1/categories/name/' + name);
  }

  getOffers(): Observable<OfferModel[]> {
    return this.http.get<OfferModel[]>('/catalog/api/v1/offers');
  }

  saveOffer(offer: OfferModel): Observable<OfferModel> {
    return this.http.post<OfferModel>('/catalog/api/v1/offers', offer);
  }
  saveCategory(category: CategoryModel): Observable<CategoryModel> {
    return this.http.post<CategoryModel>('/catalog/api/v1/categories', category);
  }
  updateOffer(offer: OfferModel): Observable<OfferModel> {
    return this.http.put<OfferModel>('/catalog/api/v1/offers', offer);
  }
  updateOfferCategory(id: number, category: CategoryModel): Observable<OfferModel> {
    return this.http.put<OfferModel>('/catalog/api/v1/offers/' + id + '/category', category);
  }

  updateCategoryName(category: CategoryModel): Observable<CategoryModel> {
    return this.http.put<CategoryModel>('/catalog/api/v1/categories', category);
  }

  deleteOffer(id: number): Observable<void> {
    return this.http.delete<void>('/catalog/api/v1/offers/' + id);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>('/catalog/api/v1/categories/' + id);
  }

  createOrder(order: {offers: number[], email: string}): Observable<OrderModel> {
    return this.http.post<OrderModel>('/processor/api/v1/processor/orders', order);
  }

  addOfferToOrder(orderId: number, offerId: number): Observable<OrderModel> {
    return this.http.put<OrderModel>(`/processor/api/v1/processor/orders/${orderId}/add?offerId=${offerId}`, null);
  }

  deleteOrderItemFromOrder(orderId: number, orderItemId: number): Observable<OrderModel> {
    return this.http.put<OrderModel>(`/processor/api/v1/processor/orders/${orderId}/delete?orderItemId=${orderItemId}`, null);
  }

  getOrderById(orderId: number): Observable<OrderModel> {
    return this.http.get<OrderModel>(`/processor/api/v1/processor/orders/${orderId}`);
  }
  getNumOrderItemsInOrder(orderId: number): Observable<number> {
    return this.http.get<number>(`/processor/api/v1/processor/orders/${orderId}/length`);
  }
  changeOrderStatus(orderId: number, orderStatus: string): Observable<OrderModel> {
    return this.http.put<OrderModel>(`/processor/api/v1/processor/orders/${orderId}/order-status`, orderStatus);
  }

  changeDeliveryAddress(orderId: number, address: string): Observable<OrderModel> {
    return this.http.put<OrderModel>(`/processor/api/v1/processor/orders/${orderId}/address`, address);
  }

  changeContactNumber(orderId: number, contactNumber: string): Observable<OrderModel> {
    return this.http.put<OrderModel>(`/processor/api/v1/processor/orders/${orderId}/contact-number`, contactNumber);
  }
  changePaymentType(orderId: number, paymentType: string): Observable<OrderModel> {
    return this.http.put<OrderModel>(`/processor/api/v1/processor/orders/${orderId}/payment-type`, paymentType);
  }
}

