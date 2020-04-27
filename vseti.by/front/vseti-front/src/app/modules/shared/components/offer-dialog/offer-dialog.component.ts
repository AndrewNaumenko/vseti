import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {OfferModel} from '../../../../models/offer.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CategoryModel} from '../../../../models/category.model';
import {HttpService} from '../../services/http-service.service';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {DialogComponent} from '../../../../components/dialog/dialog.component';
import {DialogType} from '../../../../models/dialog-data.model';

@Component({
  selector: 'app-offer-dialog',
  templateUrl: './offer-dialog.component.html',
  styleUrls: ['./offer-dialog.component.less']
})

export class OfferDialogComponent implements OnInit {
  private offerFormGroup: FormGroup;
  private categoriesFromServer: CategoryModel[];
  private filteredCategories: Observable<CategoryModel[]>;
  private offer: OfferModel;

  constructor(private formBuilder: FormBuilder,
              private http: HttpService,
              private dialog: MatDialog,
              public matDialogRef: MatDialogRef<OfferDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public model: OfferModel) {
  }
  ngOnInit(): void {
    this.offer = {id: this.model.id, title: this.model.title, description: this.model.description,
      price: this.model.price, photo: this.model.photo, category: this.model.category};
    this.initForm();
  }
  private initForm() {
    this.offerFormGroup = this.formBuilder.group({
      title: this.formBuilder.control(this.offer.title, [Validators.required]),
      photo: this.formBuilder.control(this.offer.photo, [Validators.required]),
      description: this.formBuilder.control(this.offer.description, [Validators.required]),
      price: this.formBuilder.control(this.offer.price, [Validators.required, Validators.min(0)]),
    });
    this.offerFormGroup.get('title').valueChanges.subscribe(value => this.offer.title = value);
    this.offerFormGroup.get('photo').valueChanges.subscribe(value => this.offer.photo = value);
    this.offerFormGroup.get('description').valueChanges.subscribe(value => this.offer.description = value);
    this.offerFormGroup.get('price').valueChanges.subscribe(value => this.offer.price = value);
    this.http.getCategories().subscribe((categoriesArray: CategoryModel[]) => {
      this.categoriesFromServer = categoriesArray;
      console.log(this.offer);
      this.offerFormGroup.addControl('category', this.formBuilder.control(this.offer.category, [Validators.required]));
      this.filteredCategories = this.offerFormGroup.get('category').valueChanges
        .pipe(
          startWith(''),
          map(value => this.filter(value))
        )
      ;
    });
  }
  private toStringCategory(value: string|CategoryModel): string {
    if (typeof value === 'string') {
      return value.toLowerCase();
    } else {
      return value.category.toLowerCase();
    }
  }

  private filter(value: string|CategoryModel): CategoryModel[] {
    const filterValue = this.toStringCategory(value);
    return this.categoriesFromServer.filter(category => category.category.toLowerCase().includes(filterValue));
  }

  display(category?: CategoryModel): string | undefined {
    return category ? category.category : undefined;
  }

  saveOffer() {
    const enteredCategory = this.toStringCategory(this.offerFormGroup.get('category').value);
    const offerPotentialCategory = this.filter(this.offerFormGroup.get('category').value);
    if (offerPotentialCategory.length === 1 && offerPotentialCategory[0].category === enteredCategory.trim()) {
      this.offer.category = {category: offerPotentialCategory[0].category, id: offerPotentialCategory[0].id};
      // console.log(this.model);
      // this.model.id = this.offer.id;
      // this.model.title = this.offer.title;
      // this.model.description = this.offer.description;
      // this.model.photo = this.offer.photo;
      // this.model.price = this.offer.price;
      // this.model.category = this.offer.category;
      if (this.offer.id === 0) {
        this.http.saveOffer(this.offer).subscribe(offer => {
          console.log(offer);
          this.matDialogRef.close('updated');
        });
      } else {
        this.http.updateOffer(this.offer).subscribe(offer => {
          console.log(offer);
          this.http.updateOfferCategory(this.offer.id, {id: this.offer.category.id, category: this.offer.category.category, offers: []})
            .subscribe(() => {
              console.log(offer);
              this.matDialogRef.close('updated');
            });
        });
      }

    } else if (offerPotentialCategory.length > 1) {
      this.dialog.open(DialogComponent, {
        data: {message: 'Category not selected', type: DialogType.error}
      });
    } else {
      this.dialog.open(DialogComponent, {
        data: {message: 'No match for category', type: DialogType.error}
      });
    }
  }

  closeDialog() {
    this.matDialogRef.close();
  }
}
