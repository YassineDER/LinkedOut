import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Location} from '@angular/common';
import {Router} from "@angular/router";
import {UtilsService} from "../../../../services/utils.service";
import {AuthService} from "../../../../services/auth.service";
import {FormsService} from "../../../../services/forms.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {Path} from "../../../shared/utils/path";
import {HttpClientError} from "../../../shared/utils/http-client.error";
import {OTP_CONFIG} from "../../../shared/constants/otp-input-config";

@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password-request.component.html',
    styleUrl: './reset-password-request.component.css'
})
export class ResetPasswordRequestComponent {
    passResetForm: FormGroup;
    usingMFA = false;
    mfa_code = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]);
    email = new FormControl('', [Validators.required, Validators.email]);

    constructor(private fb: FormBuilder, private location: Location, private router: Router,
                private utils: UtilsService, private auth: AuthService, private forms: FormsService) {
        this.passResetForm = this.fb.group({
            email: this.email,
        });
    }

    async submitResetRequestForm() {
        if (this.forms.checkFormValidity(this.passResetForm)) {
            await this.auth.requestPasswordReset(this.passResetForm.value)
                .then((res) => this.router.navigate([Path.RESET_PASSWORD.toString()])
                    .then(() => this.utils.alert(res.response, AlertType.SUCCESS)))
                .catch((error) => this.handleResetRequestError(error));
            this.resetForm();
        }
    }

    private resetForm() {
        if (!this.usingMFA)
            this.passResetForm.controls['email'].reset();
    }


    handleResetRequestError(error: HttpClientError) {
        if (error.error.error.startsWith('MFA code')) {
            this.usingMFA = true;
            this.passResetForm.addControl('code', this.mfa_code);
        }

        else this.utils.alert(error.error.error);
    }


    redirectToSender() {
        this.location.back();
    }

    protected readonly Path = Path;
    protected readonly OTP_CONFIG = OTP_CONFIG;
}
