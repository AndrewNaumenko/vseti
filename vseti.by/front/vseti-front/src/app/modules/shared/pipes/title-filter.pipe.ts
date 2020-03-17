import {Pipe, PipeTransform} from '@angular/core';
import {OfferModel} from '../../../models/offer.model';

@Pipe ({
  name: 'titleFilter'
})
export class TitleFilterPipe implements PipeTransform {
  transform(offers: OfferModel[], searchText: string): OfferModel[] {
    if (!offers) {return []; }
    if (!searchText) {return offers; }
    searchText = searchText.toLowerCase();
    return offers.filter(offer => offer.title.toLowerCase().includes(searchText.toLowerCase()));
  }
}
