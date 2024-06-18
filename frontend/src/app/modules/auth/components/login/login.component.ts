import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {UtilsService} from "../../../../services/utils.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {FormsService} from "../../../../services/forms.service";
import {Path} from "../../../shared/utils/path";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
})
export class LoginComponent {
    passVisible = false;
    loginForm: FormGroup;
    email = new FormControl('', [Validators.required, Validators.email]);
    password = new FormControl('', [Validators.required]);

    constructor(private fb: FormBuilder, private auth: AuthService, private formsSrv:FormsService,
                private utils: UtilsService, private router: Router) {
        this.loginForm = this.fb.group({
            email: this.email,
            password: this.password,
            code: [''],
            captcha: [null, [Validators.required]]
        });
    }


    async submitLogin() {
        const captcha_token = await this.auth.executeRecaptchaV3("Login");
        this.loginForm.controls['captcha'].setValue(captcha_token);

        if (this.formsSrv.checkFormValidity(this.loginForm)) {
            await this.auth.login(this.loginForm.value)
                .then((token) => {
                    localStorage.setItem('token', token);
                    this.router.navigate([Path.HOME.toString()]);
                })
                .catch((error) => this.handleLoginError(error));
            this.resetForm();
        }
    }

    private handleLoginError(error: any) {
        const msg: string = error.error.error;
        if (msg.startsWith("Account is not verified yet"))
            this.router.navigate([Path.VERIFY_EMAIL.toString()])
                .then(() => this.utils.alert(msg, AlertType.ERROR));
        else this.utils.alert(msg, AlertType.ERROR)
    }

    private resetForm() {
        this.loginForm.controls['password'].reset();
    }

    protected readonly Path = Path;
}
