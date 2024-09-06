import {Component, Input} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../../../../services/auth.service";
import {FormsService} from "../../../../../../services/forms.service";

@Component({
    selector: 'app-mfa-form-modal',
    templateUrl: './mfa-form-modal.component.html',
    styleUrl: './mfa-form-modal.component.css'
})
export class MfaFormModalComponent {
    @Input("usingMfa") usingMfa!: boolean;
    mfaForm: FormGroup;
    codeCtrl = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern('^[0-9]*$')]);
    passwordCtrl = new FormControl('', [Validators.required, Validators.minLength(8)]);

    constructor(private fb: FormBuilder, private auth: AuthService, protected forms:FormsService,) {
        this.mfaForm = this.fb.group({
            code: this.codeCtrl,
            password: this.passwordCtrl,
        });
    }

    submitMfaAction() {

    }
}
