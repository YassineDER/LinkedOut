import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UtilsService} from '../../../service/utils.service';
import {Location} from '@angular/common';
import {AuthService} from "../../../service/auth.service";

@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password.component.html',
    styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
    resetForm: FormGroup;
    usingMFA = true;
    otp = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]);
    email = new FormControl('', [Validators.required, Validators.email]);
    captcha = new FormControl(null, [Validators.required]);

    otp_config = {
        allowNumbersOnly: true,
        length: 6,
        disableAutoFocus: false,
    }

    constructor(private fb: FormBuilder, private location: Location,
                private utils: UtilsService, private auth: AuthService) {
        this.resetForm = this.fb.group({
            email: this.email, code: this.otp, recaptcha: this.captcha
        });
    }

    submitResetForm() {
        this.auth.executeRecaptchaV3('ResetPassword')
            .then(async token => {
                this.resetForm.controls['captcha'].setValue(token);
                if (this.utils.checkFormValidity(this.resetForm)) {
                    await this.auth.resetPassword(this.resetForm.value);
                    this.resetForm.reset();
                }
            });
    }


    redirectToSender() {
        this.location.back();
    }

}
