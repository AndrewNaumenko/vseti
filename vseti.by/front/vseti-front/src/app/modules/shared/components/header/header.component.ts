import {Component, OnInit} from '@angular/core';
import {UserModel} from '../../../../models/user.model';
import {Router} from '@angular/router';
import {NumberOrderItemsService} from '../../../../services/number-order-items.service';
import {takeUntil} from 'rxjs/operators';
import {RxUnsubscribe} from '../../../../classes/rx-unsubscribe';
import {HttpService} from '../../services/http-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})

export class HeaderComponent extends RxUnsubscribe implements OnInit {
  user: UserModel;
  numberOrderItems = 0;
  constructor(private router: Router, private numberItemsService: NumberOrderItemsService, private http: HttpService) {
    super();
    this.user = JSON.parse(localStorage.getItem('user_info'));
    if (localStorage.getItem('last_order')) {
      const id = JSON.parse(localStorage.getItem('last_order')).id;
      this.http.getNumOrderItemsInOrder(id).subscribe(orderLength => {
        this.numberOrderItems = orderLength;
        localStorage.setItem('last_order', JSON.stringify({id, numItems: this.numberOrderItems}));
      });
      // this.numberOrderItems = JSON.parse(localStorage.getItem('last_order')).numItems;
    }
    console.log(this.user);
  }

  ngOnInit(): void {
    this.numberItemsService.getNewNumber()
      .pipe(
        takeUntil((this.destroy$))
      )
      .subscribe(value => {
        this.numberOrderItems = value;
      });
  }

  logOut() {
    localStorage.clear();
    window.location.reload();
  }

  openBasket() {
    this.router.navigate(['checkout']);
  }

  openWizard() {
    this.router.navigate(['wizard']);
  }
}
