import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {WizardComponent} from './wizard.component';
import {CatalogComponent} from './components/catalog/catalog.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatButtonModule, MatCardModule, MatInputModule} from '@angular/material';

const routes: Routes = [
  {
    path: '', component: WizardComponent,
    children: [
      {path: '', redirectTo: 'catalog'},
      {path: 'catalog', component: CatalogComponent}
    ]
  },
  {path: '**', redirectTo: 'catalog'}
];

const materialModules = [
  MatCardModule,
  ReactiveFormsModule,
  MatInputModule,
  MatButtonModule
];

const ngCoreModules = [
  CommonModule
];

@NgModule({
  declarations: [
    WizardComponent,
    CatalogComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    ngCoreModules,
    HttpClientModule,
    materialModules
  ]
})
export class WizardModule { }
