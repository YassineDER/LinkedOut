import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UtilsService} from '../../../services/utils.service';
import {Location} from '@angular/common';
import {AuthService} from "../../../services/auth.service";
import {AlertType} from "../../../shared/utils/AlertType";
import {Router} from "@angular/router";

@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password-request.component.html',
    styleUrl: './reset-password-request.component.css'
})
export class ResetPasswordRequestComponent {
    resetForm: FormGroup;
    usingMFA = false;
    otp = new FormControl('000000', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]);
    email = new FormControl('', [Validators.required, Validators.email]);
    captcha = new FormControl(null, [Validators.required]);

    otp_config = {
        allowNumbersOnly: true,
        length: 6,
        disableAutoFocus: false,
    }

    constructor(private fb: FormBuilder, private location: Location, private router: Router,
                private utils: UtilsService, private auth: AuthService) {
        this.resetForm = this.fb.group({
            email: this.email, captcha: this.captcha
        });

    }

    async submitResetRequestForm() {
        const captcha = await this.auth.executeRecaptchaV3('ResetPassword');
        this.resetForm.controls['captcha'].setValue(captcha);
        if (this.usingMFA)
            this.resetForm.addControl('code', this.otp);

        if (this.utils.checkFormValidity(this.resetForm)) {
            await this.auth.requestPasswordReset(this.resetForm.value)
                .then((res) => this.router.navigate(['/password/reset'])
                    .then(() => this.utils.alert(res, AlertType.SUCCESS)))
                .catch((error) => this.handleResetRequestError(error));
            this.resetForm.reset();
        }
    }

    handleResetRequestError(error: any) {
        this.utils.alert(error.error.error, AlertType.ERROR);
        // TODO: Handle error if it's a 2FA error

    }


    redirectToSender() {
        this.location.back();
    }

}
