import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../../service/alert.service';
import {Location} from '@angular/common';
import {AlertType} from '../../../models/AlertType';
import {ReCaptchaV3Service} from 'ng-recaptcha';

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
    recaptcha = new FormControl(null, [Validators.required]);

    otp_config = {
        allowNumbersOnly: true,
        length: 6,
        disableAutoFocus: false,
    }

    constructor(private fb: FormBuilder, private location: Location,
                private alert: AlertService, private recaptchaV3: ReCaptchaV3Service) {
        this.resetForm = this.fb.group({
            email: this.email, code: this.otp, recaptcha: this.recaptcha
        });
    }

    public executeRecaptchaV3() {
        this.recaptchaV3.execute('PasswordResetRequest')
            .subscribe({
                next: (token) => {
                    if (token)
                        this.resetForm.controls['recaptcha'].setValue(token);
                },
                error: (error) => {
                    this.alert.showAlert('Erreur lors de la validation du captcha: ' + error, AlertType.ERROR);
                }
            });
    }

    submitResetForm(event: Event) {
        event.preventDefault();
        this.executeRecaptchaV3();
        for (let i in this.resetForm.controls) {
            if (this.resetForm.controls[i].errors) {
                this.resetForm.controls[i].markAsTouched();
                return this.alert.showAlert('Le champ ' + i + ' est invalide', AlertType.ERROR);
            }
        }

        console.log(this.resetForm.value);
    }


    redirectToSender() {
        this.location.back();
    }

}
