import {Component, Input, OnInit} from '@angular/core';
import {OfferModel} from '../../../../models/offer.model';
import {HttpService} from '../../services/http-service.service';
import {MatDialog, MatSnackBar} from '@angular/material';
import {Router} from '@angular/router';
import {UpdateService} from '../../services/update-service.service';
import {NumberOrderItemsService} from '../../../../services/number-order-items.service';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.less']
})

export class OfferComponent implements OnInit {
  @Input()
  offer: OfferModel;
  @Input()
  role: string;
  constructor(private http: HttpService, private dialog: MatDialog, private router: Router, private updateService: UpdateService,
              private notification: MatSnackBar, private numberItemsService: NumberOrderItemsService) {
  }

  ngOnInit(): void {
  }

  deleteOffer() {
    console.log('delete' + this.offer.id);
    this.http.deleteOffer(this.offer.id).subscribe(() => {
      this.updateService.sendMessageToUpdate(true);
    });
  }

}
