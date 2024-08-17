import {Component} from '@angular/core';
import {trigger, style, animate, transition,} from '@angular/animations';
import {AlertType} from '../../../shared/utils/alert-type';
import {KeyValuePipe, NgClass} from "@angular/common";
import {UtilsService} from "../../../../services/utils.service";

@Component({
    selector: 'app-alert',
    template: `
        @for (alert of alerts | keyvalue; track alert.key) {
            <div [ngClass]="{
         'alert-success': alert.value.type === SERVERITY.SUCCESS,
            'alert-warning': alert.value.type === SERVERITY.WARNING,
            'alert-error': alert.value.type === SERVERITY.ERROR
            }"
                 @fadeIn role="alert"
                 class="alert py-2 my-2 w-full flex items-center justify-between">
                <i class="bi bi-info
                -circle"></i>
                <span>{{ alert.value.message }}</span>
                <button class="btn btn-sm" (click)="closeAlert(alert.key)">OK</button>
            </div>
        }`,

    animations: [
        trigger('fadeIn', [
            transition(':enter', [
                style({opacity: 0}),
                animate('300ms', style({opacity: 1}))
            ])
        ])
    ]
})
export class AlertComponent {
    alerts: Map<number, { message: string, type: AlertType }> = new Map();
    public SERVERITY = AlertType;

    constructor(private utils: UtilsService) {
        this.utils.alert$.subscribe(alert => {
            if (alert) {
                this.alerts.set(this.alerts.size, {message: alert.message, type: alert.type});
                setTimeout(() => this.closeAlert(this.alerts.size - 1), 5000);
                if (this.alerts.size > 5)
                    this.alerts.delete(0);
            }
        });
    }

    closeAlert(index: number) {
        this.alerts.delete(index);
    }
}
