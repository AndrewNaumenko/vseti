import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {OfferModel} from '../../../models/offer.model';

@Injectable({providedIn: 'root'})
export class FilteredOffersService {
  filteredOffers: Subject<OfferModel[]> = new Subject<OfferModel[]>();
  sendFilteredOffers(message: OfferModel[]): void {
    this.filteredOffers.next(message);
  }
  getFilteredOffers(): Observable<OfferModel[]> {
    return this.filteredOffers.asObservable();
  }
}
