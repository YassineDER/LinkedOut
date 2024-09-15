import {Component, EventEmitter, Input, Output} from '@angular/core';
import {HttpClientError} from "../../../../../../shared/utils/http-client.error";
import {AlertType} from "../../../../../../shared/utils/alert-type";
import {SettingsService} from "../../../../../services/settings.service";
import { UtilsService } from '../../../../../../../services/utils.service';
import {NgIf} from "@angular/common";

@Component({
    selector: 'app-step-one',
    standalone: true,
    templateUrl: './step-one.component.html',
    imports: [NgIf],
})
export class StepOneComponent {
    @Input() usingMFA!: boolean;
    @Output() completed = new EventEmitter<void>();
    isTimerActive = false;
    timeLeft = 60;

    constructor(private settings: SettingsService, private utils: UtilsService) {}

    async requestMfa() {
        this.isTimerActive = true;
        this.startTimer();
        await this.settings.requestMfa()
            .then(() => this.completed.emit())
            .catch((err: HttpClientError) => this.utils.alert(err.error.error, AlertType.ERROR));
    }

    private startTimer() {
        const interval = setInterval(() => {
            this.timeLeft--;
            if (this.timeLeft <= 0) {
                clearInterval(interval);
                this.isTimerActive = false;
                this.timeLeft = 60;
            }
        }, 1000);
    }
}
