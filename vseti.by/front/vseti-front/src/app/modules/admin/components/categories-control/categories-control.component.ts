import {Component, OnInit} from '@angular/core';
import {RxUnsubscribe} from '../../../../classes/rx-unsubscribe';
import {Observable} from 'rxjs';
import {HttpService} from '../../../shared/services/http-service.service';
import {LoaderService} from '../../../../services/loader-service.service';
import {takeUntil} from 'rxjs/operators';
import {CategoryModel} from '../../../../models/category.model';
import {MatDialog} from '@angular/material';
import {CategoryDialogComponent} from '../category-dialog/category-dialog.component';
import {Router} from '@angular/router';
import {UpdateService} from '../../../shared/services/update-service.service';

@Component({
  selector: 'app-categories-control',
  templateUrl: './categories-control.component.html',
  styleUrls: ['./categories-control.component.less']
})
export class CategoriesControlComponent extends RxUnsubscribe implements OnInit {
  categoriesFromServer: CategoryModel[];
  isLoading: Observable<boolean> = this.loaderService.isLoading;
  constructor(private http: HttpService, private loaderService: LoaderService, private dialog: MatDialog,
              private router: Router, private updateService: UpdateService) {
    super();
  }
  ngOnInit(): void {
    this.isLoading.subscribe();
    this.updateService.getMessageToUpdate()
      .pipe(
        takeUntil((this.destroy$))
      )
      .subscribe(value => {
        if (value === true) {
          this.reloadCategories();
        }
      });
    this.reloadCategories();
  }

  createCategory() {
    console.log('create');
    this.dialog.open(CategoryDialogComponent, {
      data: {id: 0, category: '', offers: []}
    }).afterClosed().subscribe(closeResponse => {
      if (closeResponse === 'created') {
        // this.router.navigate(['admin']);
        this.reloadCategories();
      }
    });
  }
  reloadCategories(): void {
    this.http.getCategories()
      .pipe(
        takeUntil((this.destroy$))
      )
      .subscribe(
        (categoriesArray: CategoryModel[]) => {
          this.categoriesFromServer = categoriesArray;
        }
      );
  }
}
