import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AlertService } from '../../../service/alert.service';
import { Location } from '@angular/common';
import { AlertType } from '../../../models/AlertType';
import { ReCaptchaV3Service } from 'ng-recaptcha';

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
        private alert: AlertService, private recaptcha: ReCaptchaV3Service) {
        this.resetForm = this.fb.group({
            email: this.email,
            code: this.otp,
        });
    }

    public executeRecaptchaV3() {
        this.recaptcha.execute('myAction').subscribe(
          (token) => {
            this.addTokenLog('Recaptcha v3 token', token);
          },
          (error) => {
            this.log.push(`Recaptcha v3 error: see console`);
            console.log(`Recaptcha v3 error:`, error);
          }
        );
      }

    submitResetForm(event: Event) {
        event.preventDefault();
        for(let i in this.resetForm.controls) {
            if (this.resetForm.controls[i].errors) {
                this.resetForm.controls[i].markAsTouched();
                return this.alert.showAlert('Les champs sont invalides', AlertType.ERROR);
            }
        }

        console.log(this.resetForm.value);
    }


    redirectToSender() {
        this.location.back();
    }

}
