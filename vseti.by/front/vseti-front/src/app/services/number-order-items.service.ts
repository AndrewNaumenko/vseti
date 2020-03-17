import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({providedIn: 'root'})
export class NumberOrderItemsService {
  number: Subject<number> = new Subject<number>();
  sendNewNumber(newNumber: number): void {
    this.number.next(newNumber);
  }
  getNewNumber(): Observable<number> {
    return this.number.asObservable();
  }
}
