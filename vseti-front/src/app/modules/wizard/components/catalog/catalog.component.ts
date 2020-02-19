import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {OfferModel} from '../../../../models/offer.model';
import {HttpService} from '../../../../services/http-service';


@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.less']
})
export class CatalogComponent implements OnInit {

  title = 'order-entry';

  offersList$: Observable<OfferModel[]>;


  constructor(private http: HttpService) {
  }

  ngOnInit(): void {
    this.offersList$ = this.http.getOffers();
  }
}
