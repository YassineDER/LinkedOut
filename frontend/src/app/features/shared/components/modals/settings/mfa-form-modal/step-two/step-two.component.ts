import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../../../../../../services/auth.service";
import {FormsService} from "../../../../../../../services/forms.service";

@Component({
    selector: 'app-step-two',
    standalone: true,
    imports: [
        ReactiveFormsModule
    ],
    templateUrl: './step-two.component.html'
})
export class StepTwoComponent {
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
