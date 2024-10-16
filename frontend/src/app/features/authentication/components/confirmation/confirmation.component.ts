import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FormsService} from "../../../../services/forms.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {VerificationType} from "../../utils/verification-type";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {UtilsService} from "../../../../services/utils.service";
import {Path} from "../../../shared/utils/path";
import {OTP_CONFIG} from "../../../shared/constants/otp-input-config";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})
export class ConfirmationComponent {
    protected readonly VerificationType = VerificationType;
    verification :VerificationType | undefined;

    confirmationForm: FormGroup;
    code = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]);
    pwd = new FormControl('', [Validators.required, Validators.minLength(8)]);
    confirmPwd = new FormControl('', [Validators.required, Validators.minLength(8)]);


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
            else this.utils.alert('Page de confirmation inconnue');
        });
    }


    async submitConfirmationForm() {
        const code = this.confirmationForm.value.received_code ?? null;

        if (this.verification === VerificationType.EMAIL_VERIFICATION && code !== null) {
            await this.auth.verifyEmail(code)
                .then((res) => this.router.navigate([Path.LOGIN.toString()], {queryParams: {token: res.response}})
                    .then(() => this.utils.alert('Votre adresse email a été vérifiée', AlertType.SUCCESS)))
                    .catch((err) => this.utils.alert(err.error.error));
        }

        else if (this.verification === VerificationType.PASSWORD_RESET && this.formsSrv.checkFormValidity(this.confirmationForm)) {
            await this.auth.resetPassword(this.confirmationForm.value)
                .then(() => this.router.navigate([Path.LOGIN.toString()])
                .then(() => this.utils.alert('Mot de passe réinitialisé. Vous pouvez maintenant vous connecter', AlertType.SUCCESS)))
                .catch((err) => this.utils.alert(err.error.error));
        }
    }

    protected readonly Path = Path;
    protected readonly OTP_CONFIG = OTP_CONFIG;
}
