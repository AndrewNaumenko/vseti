import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatFormFieldModule, MatIconModule,
  MatInputModule, MatMenuModule,
  MatProgressSpinnerModule, MatRadioModule, MatSnackBarModule, MatStepperModule, MatTabsModule
} from '@angular/material';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {DialogComponent} from '../../components/dialog/dialog.component';
import {RoleGuard} from '../../guards/role.guard';
import {TitleFilterPipe} from './pipes/title-filter.pipe';
import {FilterComponent} from './components/filter/filter.component';
import {OfferComponent} from './components/offer/offer.component';
import {CatalogComponent} from './components/catalog/catalog.component';
import {HeaderComponent} from './components/header/header.component';

const ngCoreModules = [
  CommonModule,
  ReactiveFormsModule,
  FormsModule
];

const materialModules = [
  MatFormFieldModule,
  MatInputModule,
  MatCardModule,
  MatButtonModule,
  MatDialogModule,
  MatCheckboxModule,
  MatProgressSpinnerModule,
  MatMenuModule,
  MatIconModule,
  MatAutocompleteModule,
  MatTabsModule,
  MatSnackBarModule,
  MatStepperModule,
  MatRadioModule
];

@NgModule({
  imports: [
    ...ngCoreModules,
    ...materialModules
  ],
  exports: [
    ...ngCoreModules,
    ...materialModules,
    FilterComponent,
    TitleFilterPipe,
    OfferComponent,
    CatalogComponent,
    HeaderComponent
  ],
  providers: [RoleGuard],
  declarations: [
    DialogComponent,
    FilterComponent,
    TitleFilterPipe,
    OfferComponent,
    CatalogComponent,
    HeaderComponent
  ],
  entryComponents: [DialogComponent]
})
export class SharedModule {}
