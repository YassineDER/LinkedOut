import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../../../service/alert.service';
import {AlertType} from "../../../../shared/utils/AlertType";
import {environment} from "../../../../../environments/environment";
import {AuthService} from "../../../../service/auth.service";

@Component({
    selector: 'app-register',
    templateUrl: './register-company.component.html',
    styleUrl: './register-company.component.css'
})
export class RegisterCompanyComponent {
    passVisible = false;
    isDev = false;
    registerCompany: FormGroup;

    email = new FormControl('', [Validators.required, Validators.email]);
    pwd = new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]);
    captcha = new FormControl(null, [Validators.required]);
    siren = new FormControl('', [Validators.required, Validators.minLength(9), Validators.maxLength(9)]);

    constructor(private fb: FormBuilder, private alert: AlertService, private auth: AuthService) {

        this.registerCompany = this.fb.group({
            email: this.email,
            username: this.email.value!.split('@')[0],
            password: this.pwd,
            siren: this.siren,
            captcha: this.captcha
        });

        this.isDev = !environment.production;
    }

    preFillRecruiter() {
        this.registerCompany.setValue({
            captcha: new FormControl(null).value,
            email: new FormControl('univ-nantes@example.com').value,
            username: new FormControl('univ-nantes').value,
            password: new FormControl('12345678').value,
            siren: new FormControl('130029747').value
        });
    }

    submitRegisterForm(event: Event) {
        event.preventDefault();
        this.auth.executeRecaptchaV3("RegisterCompany")
            .then(token => this.registerCompany.controls['captcha'].setValue(token));

        for (let i in this.registerCompany.controls) {
            if (this.registerCompany.controls[i].errors) {
                this.registerCompany.controls[i].markAsTouched();
                return this.alert.show('Le champ ' + i + ' est invalide', AlertType.ERROR);
            }
        }

        console.log('Form submitted: ', this.registerCompany.value);
    }


}
