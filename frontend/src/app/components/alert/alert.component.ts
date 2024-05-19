import { Component } from '@angular/core';
import { AlertService } from '../../service/alert.service';
import { trigger, style, animate, transition, } from '@angular/animations';
import { AlertType } from '../../models/AlertType';

@Component({
  selector: 'app-alert',
  template: `
  @if (alertMessage) {
    <div (mouseenter)="pauseTimer()" (mouseleave)="resumeTimer()"
      [ngClass]="{'alert-success': alertType === SERVERITY.SUCCESS,
       'alert-warning': alertType === SERVERITY.WARNING,
       'alert-error': alertType === SERVERITY.ERROR}"
    @fadeInOut role="alert" 
    class="alert py-2 w-full flex items-center justify-between">
      <i class="bi bi-info-circle"></i>
      <span>{{ alertMessage }}</span>
      <button class="btn btn-sm" (click)="closeAlert()">OK</button>
    </div>
  }`,
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms', style({ opacity: 1 }))
      ]),
      transition(':leave', [
        animate('300ms', style({ opacity: 0 }))
      ])
    ])
  ]
})
export class AlertComponent {
  alertMessage: string | null = null;
  alertType = AlertType.DEFAULT;
  public SERVERITY = AlertType;

  constructor(private alertService: AlertService) {
    this.alertService.alert$.subscribe((msg) => this.alertMessage = msg);
    this.alertService.alertType$.subscribe((type) => this.alertType = type);
  }

  closeAlert() {
    this.alertMessage = null;
  }

  pauseTimer() {
    this.alertService.pauseTimer();
  }

  resumeTimer() {
    this.alertService.resumeTimer();
  }
}
