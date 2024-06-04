import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FormsService} from "../../services/forms.service";
import {AlertType} from "../../../../shared/utils/AlertType";
import {VerificationType} from "../../utils/VerificationType";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {UtilsService} from "../../../../services/utils.service";

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


    constructor(fb: FormBuilder, private utils: UtilsService, private formsSrv: FormsService, private router: Router,
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
            else this.utils.alert('Page de confirmation inconnue', AlertType.ERROR);
        });
    }


    async submitConfirmationForm() {
        const code = this.confirmationForm.value.received_code ?? null;

        if (this.verification === VerificationType.EMAIL_VERIFICATION && code !== null) {
            await this.auth.verifyEmail(code)
                .then(() => this.router.navigate(['/login'])
                    .then(() => this.utils.alert('Email vérifié. Vous pouvez maintenant vous connecter', AlertType.SUCCESS)))
                    .catch((err) => this.utils.alert(err.error.error, AlertType.ERROR));
        }

        else if (this.verification === VerificationType.PASSWORD_RESET && this.formsSrv.checkFormValidity(this.confirmationForm)) {
            await this.auth.resetPassword(this.confirmationForm.value).then(() => this.router.navigate(['/login'])
                .then(() => this.utils.alert('Mot de passe réinitialisé. Vous pouvez maintenant vous connecter', AlertType.SUCCESS)))
                .catch((err) => this.utils.alert(err.error.error, AlertType.ERROR));
        }
    }

}
