import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-mfa-modal',
    templateUrl: './mfa-form-modal.component.html',
    styleUrl: './mfa-form-modal.component.css'
})
export class MfaFormModalComponent {
    @Input("usingMfa") usingMfa!: boolean;
    step = 1;
    qrCode!: string;

    nextStep(qrImage?: string) {
        if (qrImage)
            this.qrCode = qrImage;
        this.step++;
    }

    finalizeMFA() {
        if (this.step === 3)
            this.location.reload();
    }

    protected readonly location = location;
}
