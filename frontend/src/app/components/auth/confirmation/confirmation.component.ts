import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../service/alert.service";
import {AlertType} from "../../../shared/utils/AlertType";
import {VerificationType} from "../../../shared/utils/VerificationType";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../../service/auth.service";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})
export class ConfirmationComponent{
    protected readonly VerificationType = VerificationType;
    verification :VerificationType | undefined;

    confirmationForm: FormGroup;
    code = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]);
    pwd = new FormControl('', [Validators.required, Validators.minLength(8)]);
    confirmPwd = new FormControl('', [Validators.required, Validators.minLength(8)]);

    otp_config = {
        allowNumbersOnly: true,
        length: 6,
        disableAutoFocus: false,
    }


    constructor(fb: FormBuilder, private alert: AlertService,
                private auth: AuthService, route: ActivatedRoute) {
        this.confirmationForm = fb.group({
            password: this.pwd,
            password_confirmation: this.confirmPwd,
            received_code: this.code
        });

        route.data.subscribe(data => {
            if (data['animation'] === 'ResetPasswordPage')
                this.verification = VerificationType.PASSWORD_RESET;
            else if (data['animation'] === 'EmailVerificationPage')
                this.verification = VerificationType.EMAIL_VERIFICATION;
            else this.alert.show('Page de confirmation inconnue', AlertType.ERROR);
        });
    }


    submitConfirmationForm() {
        const code = this.confirmationForm.value.received_code ?? null;
        if (this.verification === VerificationType.EMAIL_VERIFICATION && code !== null)
            return this.auth.verifyEmail(code);
        if (this.alert.checkFormValidity(this.confirmationForm))
            if (this.verification === VerificationType.PASSWORD_RESET)
                this.auth.resetPassword(this.confirmationForm.value)
    }

}
