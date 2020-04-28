import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import {AdminComponent} from './admin.component';
import {RoleGuard} from '../../guards/role.guard';
import {CategoryComponent} from './components/category/category.component';
import {CategoriesControlComponent} from './components/categories-comtrol/categories-control.component';
import {CategoryDialogComponent} from './components/category-dialog/category-dialog.component';

const routes: Routes = [
  {
    path: '', component: AdminComponent, canActivate: [RoleGuard]
  },
];


@NgModule({
  declarations: [
    AdminComponent,
    CategoryComponent,
    CategoriesControlComponent,
    CategoryDialogComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  entryComponents: [CategoryDialogComponent],
  providers: []
})
export class AdminModule {}
