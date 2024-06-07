import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {environment} from "../../../../../../environments/environment";
import {FormsService} from "../../../../../services/forms.service";
import {Role} from "../../../../../models/role";

@Component({
    selector: 'app-register',
    templateUrl: './register-company.component.html',
    styleUrl: './register-company.component.css'
})
export class RegisterCompanyComponent {
    passVisible = false;
    isDev = false;
    registerCompanyForm: FormGroup;

    email = new FormControl('', [Validators.required, Validators.email]);
    pwd = new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]);
    captcha = new FormControl(null, [Validators.required]);
    siren = new FormControl('', [Validators.required, Validators.minLength(9), Validators.maxLength(9)]);

    constructor(private fb: FormBuilder, private formSrv: FormsService) {

        this.registerCompanyForm = this.fb.group({
            email: this.email,
            username: this.email.value!.split('@')[0],
            password: this.pwd,
            siren: this.siren,
            captcha: this.captcha
        });

        this.isDev = !environment.production;
    }

    preFillRecruiter() {
        this.registerCompanyForm.setValue({
            captcha: new FormControl(null).value,
            email: new FormControl('univ-nantes@example.com').value,
            username: new FormControl('univ-nantes').value,
            password: new FormControl('12345678').value,
            siren: new FormControl('130029747').value
        });
    }


    async submitRegistration() {
        await this.formSrv.submitRegisterForm(this.registerCompanyForm, Role.COMPANY)
    }

}
