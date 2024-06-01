import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UtilsService} from '../../../service/utils.service';
import {Location} from '@angular/common';
import {AuthService} from "../../../service/auth.service";
import {AlertType} from "../../../shared/utils/AlertType";

@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password-request.component.html',
    styleUrl: './reset-password-request.component.css'
})
export class ResetPasswordRequestComponent {
    resetForm: FormGroup;
    usingMFA = true;
    otp = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]);
    email = new FormControl('', [Validators.required, Validators.email]);

    otp_config = {
        allowNumbersOnly: true,
        length: 6,
        disableAutoFocus: false,
    }

    constructor(private fb: FormBuilder, private location: Location,
                private utils: UtilsService, private auth: AuthService) {
        this.resetForm = this.fb.group({
            email: this.email, code: this.otp
        });
    }

    async submitResetRequestForm() {
        if (this.utils.checkFormValidity(this.resetForm)) {
            await this.auth.requestPasswordReset(this.resetForm.value)
                .then((res) => this.utils.alert(res, AlertType.SUCCESS))
                .catch((error) => this.utils.alert(error.error.error, AlertType.ERROR));
            this.resetForm.reset();
        }
    }


    redirectToSender() {
        this.location.back();
    }

}
