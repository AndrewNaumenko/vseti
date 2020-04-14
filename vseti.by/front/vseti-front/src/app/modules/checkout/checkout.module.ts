import {RouterModule, Routes} from '@angular/router';
import {RoleGuard} from '../../guards/role.guard';
import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import {CheckoutComponent} from './checkout.component';
import {BasketComponent} from './components/basket/basket.component';
import {BasketElementComponent} from './components/basket-element/basket-element.component';

const routes: Routes = [
  {
    path: '', component: CheckoutComponent, canActivate: [RoleGuard]
  },
];


@NgModule({
  declarations: [
    CheckoutComponent,
    BasketComponent,
    BasketElementComponent,
  ],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  providers: []
})
export class CheckoutModule {}
