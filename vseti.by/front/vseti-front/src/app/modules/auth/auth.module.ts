import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {AuthComponent} from './auth.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {LoginComponent} from './components/login/login.component';

const routes: Routes = [
  {
    path: '', component: AuthComponent,
    children: [
      {path: '', redirectTo: 'login'},
      {path: 'login', component: LoginComponent},
      {path: 'registration', component: RegistrationComponent}
    ]
  },
  {path: '**', redirectTo: 'login'}
];


@NgModule({
  declarations: [
    AuthComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  providers: []
})
export class AuthModule {}
