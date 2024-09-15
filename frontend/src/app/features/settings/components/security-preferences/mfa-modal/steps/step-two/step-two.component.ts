import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {FormsService} from "../../../../../../../services/forms.service";
import {SettingsService} from "../../../../../services/settings.service";
import {UtilsService} from "../../../../../../../services/utils.service";
import {NgOtpInputModule} from "ng-otp-input";
import {OTP_CONFIG} from "../../../../../../shared/constants/otp-input-config";
import {NgClass, NgIf} from "@angular/common";

@Component({
    selector: 'app-step-two',
    standalone: true,
    imports: [
        ReactiveFormsModule,
        NgOtpInputModule,
        NgIf,
        NgClass
    ],
    templateUrl: './step-two.component.html'
})
export class StepTwoComponent {
    @Input() usingMfa!: boolean;
    @Output() completed = new EventEmitter<string | null>();
    mfaForm: FormGroup;
    codeCtrl = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern('^[0-9]*$')]);
    passwordCtrl = new FormControl('', [Validators.required, Validators.minLength(8)]);

    constructor(fb: FormBuilder, private settings: SettingsService,
                private forms: FormsService, private utils: UtilsService) {
        this.mfaForm = fb.group({
            code: this.codeCtrl,
            password: this.passwordCtrl,
        });
    }


    async submitMfaAction() {
        const action = this.usingMfa ? "disable" : "enable";
        if (this.forms.checkFormValidity(this.mfaForm)) {
            await this.settings.mfa(action, this.mfaForm.value)
                .then((res) => action === "enable" ? this.completed.emit(res.qr_image) : this.completed.emit(null))
                .catch((error) => this.utils.alert(error.error.error));
            this.mfaForm.reset();
        }
    }

    protected readonly OTP_CONFIG = OTP_CONFIG;
}
