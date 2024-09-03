import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Location} from '@angular/common';
import {Router} from "@angular/router";
import {UtilsService} from "../../../../services/utils.service";
import {AuthService} from "../../../../services/auth.service";
import {FormsService} from "../../../../services/forms.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {Path} from "../../../shared/utils/path";

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

    otp_config = {
        allowNumbersOnly: true,
        length: 6,
        disableAutoFocus: false,
    }

    constructor(private fb: FormBuilder, private location: Location, private router: Router,
                private utils: UtilsService, private auth: AuthService, private forms: FormsService) {
        this.resetForm = this.fb.group({
            email: this.email,
        });
    }

    async submitResetRequestForm() {
        if (this.usingMFA)
            this.resetForm.addControl('code', this.otp);

        if (this.forms.checkFormValidity(this.resetForm)) {
            await this.auth.requestPasswordReset(this.resetForm.value)
                .then((res) => this.router.navigate([Path.RESET_PASSWORD.toString()])
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

    protected readonly Path = Path;
}
