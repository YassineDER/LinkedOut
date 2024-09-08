import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {FormsService} from "../../../../../../../services/forms.service";
import {SettingsService} from "../../../../../../settings/services/settings.service";
import {UtilsService} from "../../../../../../../services/utils.service";

@Component({
    selector: 'app-step-two',
    standalone: true,
    imports: [
        ReactiveFormsModule
    ],
    templateUrl: './step-two.component.html'
})
export class StepTwoComponent {
    @Output() completed = new EventEmitter<void>();
    mfaForm: FormGroup;
    codeCtrl = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern('^[0-9]*$')]);
    passwordCtrl = new FormControl('', [Validators.required, Validators.minLength(8)]);

    constructor(private fb: FormBuilder, private settings: SettingsService,
                protected forms: FormsService, private utils: UtilsService) {
        this.mfaForm = this.fb.group({
            code: this.codeCtrl,
            password: this.passwordCtrl,
        });
    }


    async submitMfaAction() {
        if (this.forms.checkFormValidity(this.mfaForm)) {
            await this.settings.mfa("enable", this.mfaForm.value)
                .then(() => this.completed.emit())
                .catch((error) => this.utils.alert(error.error.error));
            this.mfaForm.reset();
        }
    }
}
