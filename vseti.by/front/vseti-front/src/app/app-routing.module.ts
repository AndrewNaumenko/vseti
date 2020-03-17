import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import ('./modules/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'wizard',
    loadChildren: () => import ('./modules/wizard/wizard.module').then(m => m.WizardModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
