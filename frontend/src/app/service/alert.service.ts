import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AlertType } from '../models/AlertType';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private alertSubject = new BehaviorSubject<string | null>(null);
  alert$ = this.alertSubject.asObservable();

  private alertTypeSubject = new BehaviorSubject<AlertType>(AlertType.DEFAULT);
  alertType$ = this.alertTypeSubject.asObservable();

  private alertTimer: any = null;

  showAlert(message: string, type = AlertType.DEFAULT) {
    this.alertSubject.next(message);
    this.alertTypeSubject.next(type);
    this.alertTimer = setTimeout(() => {
      this.alertSubject.next(null);
      this.alertTypeSubject.next(AlertType.DEFAULT);
    }, 3000);
  }

  pauseTimer() {
    clearTimeout(this.alertTimer);
  }

  resumeTimer() {
    this.alertTimer = setTimeout(() => this.alertSubject.next(null), 3000);
  }

}
