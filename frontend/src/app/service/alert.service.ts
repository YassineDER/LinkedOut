import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AlertType} from '../shared/utils/AlertType';

@Injectable({
    providedIn: 'root'
})
export class AlertService {
    private alertSubject = new BehaviorSubject<{ message: string, type: AlertType } | null>(null);
    alert$ = this.alertSubject.asObservable();

    show(message: string, type: AlertType = AlertType.DEFAULT) {
        this.alertSubject.next({message, type});
    }


}
