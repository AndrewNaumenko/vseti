import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {WizardComponent} from './wizard.component';
import {RoleGuard} from '../../guards/role.guard';

const routes: Routes = [
  {
    path: '', component: WizardComponent, canActivate: [RoleGuard]
  },
];


@NgModule({
  declarations: [
    WizardComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  providers: []
})
export class WizardModule {}
