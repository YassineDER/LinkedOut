import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../service/alert.service";
import {AlertType} from "../../../shared/utils/AlertType";
import {VerificationType} from "../../../shared/utils/VerificationType";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})
export class ConfirmationComponent implements OnInit{
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


    constructor(private fb: FormBuilder, private alert: AlertService,
                private router: Router, route: ActivatedRoute) {
        this.confirmationForm = this.fb.group({
            password: this.pwd, confirmPassword: this.confirmPwd, code: this.code
        });

        route.data.subscribe(data => {
            if (data['animation'] === 'ResetPasswordPage')
                this.verification = VerificationType.PASSWORD_RESET;
            else if (data['animation'] === 'EmailVerificationPage')
                this.verification = VerificationType.EMAIL_VERIFICATION;
            else this.alert.show('Page de confirmation inconnue', AlertType.ERROR);
        });
    }

    ngOnInit() {

    }


    submitConfirmationForm(event: Event) {
        event.preventDefault();
        for (let i in this.confirmationForm.controls) {
            if (this.confirmationForm.controls[i].errors) {
                this.confirmationForm.controls[i].markAsTouched();
                console.log(this.confirmationForm.controls[i].errors)
                return this.alert.show('Le champ ' + i + ' est invalide', AlertType.ERROR);
            }
        }

        if (this.verification === VerificationType.EMAIL_VERIFICATION)
            this.verifyEmail();
        else if (this.verification === VerificationType.PASSWORD_RESET)
            this.resetPassword();

    }

    resetPassword() {
        this.router.navigate(['/login']).then(() => {
            this.alert.show('Mot de passe réinitialisé avec succès', AlertType.SUCCESS);
        });
    }

    verifyEmail() {
        this.router.navigate(['/login']).then(() => {
            this.alert.show('Email vérifié avec succès', AlertType.SUCCESS);
        });
    }

}
