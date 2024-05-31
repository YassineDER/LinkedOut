import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../../service/auth.service";
import {AlertService} from "../../../service/alert.service";

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

    constructor(private fb: FormBuilder, private auth: AuthService, private alert: AlertService) {
        this.loginForm = this.fb.group({
            email: this.email,
            password: this.password,
            code: [''],
            captcha: [null, [Validators.required]]
        });
    }


    submitLogin() {
        this.auth.executeRecaptchaV3("Login")
            .then(token => {
                this.loginForm.controls['captcha'].setValue(token);
                if (this.alert.checkFormValidity(this.loginForm)) {
                    this.auth.login(this.loginForm.value);
                    this.loginForm.reset();
                }
            });
    }

}
