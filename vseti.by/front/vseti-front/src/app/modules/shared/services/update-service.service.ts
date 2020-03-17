import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({providedIn: 'root'})
export class UpdateService {
  update: Subject<boolean> = new Subject<boolean>();
  sendMessageToUpdate(message: boolean): void {
    this.update.next(message);
  }
  getMessageToUpdate(): Observable<boolean> {
    return this.update.asObservable();
  }
}
