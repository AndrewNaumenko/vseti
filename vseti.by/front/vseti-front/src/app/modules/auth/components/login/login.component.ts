import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserModel} from '../../../../models/user.model';
import {DialogComponent} from '../../../../components/dialog/dialog.component';
import {AuthFormService} from '../../services/auth-form.service';
import {MatDialog} from '@angular/material';
import {Router} from '@angular/router';
import {DialogType} from '../../../../models/dialog-data.model';
import {NumberOrderItemsService} from '../../../../services/number-order-items.service';

@Component({
  selector: 'app-auth',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})

export class LoginComponent implements OnInit {
  private loginFormGroup: FormGroup;
  constructor(private formBuilder: FormBuilder,
              private authService: AuthFormService,
              private dialog: MatDialog,
              private router: Router,
              private numberItemsService: NumberOrderItemsService) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      email: this.formBuilder.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.control('', Validators.required)
    });
  }
  public onSubmit(): void {
    const answer = this.loginFormGroup.value;
    const user: UserModel = {email: answer.email, password: answer.password, role: '',
                             surname: '', name: '', patronymic: '', age: 0};
    this.authService.loginUser(user)
      .then(response => {
        localStorage.clear();
        localStorage.setItem('user_info', JSON.stringify(response));
        this.authService.getLastOrderByEmailAndOrderStatus(response.email, 'IN_PROCESS').subscribe(
          order => {
            localStorage.setItem('last_order', JSON.stringify({id: order.id, numItems: order.orderItems.length}));
            this.numberItemsService.sendNewNumber(order.orderItems.length);
          }
        );
        switch (response.role) {
          case 'USER':
            this.router.navigate(['wizard']);
            break;
          case 'ADMIN':
            this.router.navigate(['admin']);
            break;
        }
      })
      .catch(error => {
        this.dialog.open(DialogComponent, {
          data: {message: error.toString(), type: DialogType.error}
        });
      });
  }

  getEmailError(elemName: string): string {
      return this.loginFormGroup.get(elemName).hasError('email') ? 'Not a valid email' : '';
  }
  getRequiredError(elemName: string): string {
    return this.loginFormGroup.get(elemName).hasError('required') ? 'You must enter a value' : '';
  }
}
