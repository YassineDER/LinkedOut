import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-mfa-form-modal',
    templateUrl: './mfa-form-modal.component.html',
    styleUrl: './mfa-form-modal.component.css'
})
export class MfaFormModalComponent {
    @Input("usingMfa") usingMfa!: boolean;
    step = 1;

    nextStep() {
        this.step++;
    }
}
