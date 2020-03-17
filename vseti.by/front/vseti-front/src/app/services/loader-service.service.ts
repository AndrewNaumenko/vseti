import {BehaviorSubject, Subject} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable()
export class LoaderService {
  isLoading = new BehaviorSubject<boolean>(false);
  show() {
    console.log('show loader');
    this.isLoading.next(true);
  }
  hide() {
    console.log('hide loader');
    this.isLoading.next(false);
  }
}
