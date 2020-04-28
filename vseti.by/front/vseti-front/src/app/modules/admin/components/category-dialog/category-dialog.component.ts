import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {CategoryModel} from '../../../../models/category.model';
import {HttpService} from '../../../shared/services/http-service.service';

@Component({
  selector: 'app-category-dialog',
  templateUrl: './category-dialog.component.html',
  styleUrls: ['./category-dialog.component.less']
})

export class CategoryDialogComponent implements OnInit {
  constructor(public matDialogRef: MatDialogRef<CategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public category: CategoryModel, private http: HttpService) {
  }
  ngOnInit(): void {
  }
  save(): void {
    console.log(this.category.category);
    this.http.saveCategory(this.category).subscribe(savedCategory => {
      this.matDialogRef.close('created');
    });
  }
  close(): void {
    this.matDialogRef.close();
  }
}
