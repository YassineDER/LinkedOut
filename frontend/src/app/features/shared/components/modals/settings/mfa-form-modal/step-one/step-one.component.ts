import {Component, EventEmitter, Output} from '@angular/core';
import {HttpClientError} from "../../../../../utils/http-client.error";
import {AlertType} from "../../../../../utils/alert-type";
import {SettingsService} from "../../../../../../settings/services/settings.service";
import { UtilsService } from '../../../../../../../services/utils.service';
import {NgIf} from "@angular/common";

@Component({
    selector: 'app-step-one',
    standalone: true,
    template: `
        <div class="flex flex-row items-center">
            <i class="bi bi-shield-check text-4xl text-primary"></i>
            <p class="p-4">Pour activer la vérification en deux étapes, vous aurez besoin d'une application
                d'authentification
                sur votre téléphone. Une fois activée, vous devrez entrer un code de vérification à chaque connexion.
            </p>
        </div>
        <button (click)="requestMfa()" class="btn btn-primary" [disabled]="isTimerActive">
            {{ isTimerActive ? 'Veuillez patienter...' : 'Continuer' }}
        </button>
        <p class="text-center text-gray-500 mt-4" *ngIf="isTimerActive">Vous pouvez réessayer dans {{ timeLeft }} secondes</p>
    `,
    imports: [NgIf],
})
export class StepOneComponent {
    @Output() completed = new EventEmitter<void>();
    isTimerActive = false;
    timeLeft = 120;

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
                this.timeLeft = 120;
            }
        }, 1000);
    }
}
