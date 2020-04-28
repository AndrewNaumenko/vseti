import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.less']
})
export class DeliveryComponent implements OnInit {
  deliveryForm: FormGroup;
  @Output() deliveryEmitter = new EventEmitter<FormGroup>();
  constructor(private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.deliveryForm = this.formBuilder.group({
      address: this.formBuilder.control('', [Validators.required]),
      phone: this.formBuilder.control('', [Validators.required,
        Validators.pattern( /[+][0-9]{3}[-][0-9]{2}[-][0-9]{3}[-][0-9]{2}[-][0-9]{2}$/g)]),
      paymentType: 'cash'
    });
    this.deliveryEmitter.emit(this.deliveryForm);
  }
  getRequiredError(elemName: string): string {
    return this.deliveryForm.get(elemName).hasError('required') ? 'You must enter a value' : '';
  }
  getPhonePatternError(elemName: string): string {
    return this.deliveryForm.get(elemName).hasError('pattern') ? 'Phone pattern is +xxx-xx-xxx-xx-xx' : '';
  }

  saveDeliveryInfo() {

  }

  getFormData() {
    console.log(this.deliveryForm.get('address').value + ' ' + this.deliveryForm.get('phone').value
      + ' ' + this.deliveryForm.get('paymentType').value);
  }
}
