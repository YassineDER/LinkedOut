import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {UtilsService} from "../../../../services/utils.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {FormsService} from "../../../../services/forms.service";
import {Path} from "../../../shared/utils/path";
import {HttpClientError} from "../../../shared/utils/http-client.error";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
    passVisible = false;
    loginForm: FormGroup;
    email = new FormControl('', [Validators.required, Validators.email]);
    password = new FormControl('', [Validators.required]);

    constructor(private fb: FormBuilder, private auth: AuthService, protected forms:FormsService,
                private utils: UtilsService, private router: Router, private route: ActivatedRoute) {
        this.loginForm = this.fb.group({
            email: this.email,
            password: this.password,
            code: [''],
        });
    }

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            const token = params['token'];
            if (token) {
                localStorage.setItem('token', token);
                window.location.reload();
            }
        })
    }

    async submitLogin() {
        if (this.forms.checkFormValidity(this.loginForm)) {
            await this.auth.login(this.loginForm.value)
                .then((res) => {
                    localStorage.setItem('token', res.response);
                    window.location.reload();
                })
                .catch((error) => this.handleLoginError(error));
            this.resetForm();
        }
    }

    private handleLoginError(error: HttpClientError) {
        const msg = error.error.error;
        if (msg.startsWith("Account is not verified yet"))
            this.router.navigate([Path.VERIFY_EMAIL.toString()])
                .then(() => this.utils.alert(msg, AlertType.WARNING));
        else this.utils.alert(msg)
    }

    private resetForm() {
        this.loginForm.controls['password'].reset();
    }

    protected readonly Path = Path;
}
