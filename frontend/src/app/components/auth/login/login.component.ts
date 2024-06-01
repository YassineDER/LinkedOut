import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../../service/auth.service";
import {UtilsService} from "../../../service/utils.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {
    passVisible = false;
    loginForm: FormGroup;
    email = new FormControl('', [Validators.required, Validators.email]);
    password = new FormControl('', [Validators.required]);

    constructor(private fb: FormBuilder, private auth: AuthService, private utils: UtilsService) {
        this.loginForm = this.fb.group({
            email: this.email,
            password: this.password,
            code: [''],
            captcha: [null, [Validators.required]]
        });
    }


    submitLogin() {
        this.auth.executeRecaptchaV3("Login")
            .then(async token => {
                this.loginForm.controls['captcha'].setValue(token);
                if (this.utils.checkFormValidity(this.loginForm)) {
                    await this.auth.login(this.loginForm.value);
                    this.loginForm.reset();
                }
            });
    }

}
