import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UtilsService} from "../../../../services/utils.service";
import {environment} from "../../../../../environments/environment";
import {AuthService} from "../../../../services/auth.service";
import {AlertType} from "../../../../shared/utils/AlertType";
import {Router} from "@angular/router";

@Component({
    selector: 'app-register-jobseeker',
    templateUrl: './register-jobseeker.component.html',
    styleUrl: './register-jobseeker.component.css'
})
export class RegisterJobseekerComponent {
    passVisible = false;
    isDev = false;
    registerJobseeker: FormGroup;

    email = new FormControl('', [Validators.required, Validators.email]);
    username = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]);
    pwd = new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]);
    first_name = new FormControl('', [Validators.required]);
    last_name = new FormControl('', [Validators.required]);
    captcha = new FormControl(null, [Validators.required]);

    constructor(private fb: FormBuilder, private utils: UtilsService,
                private auth: AuthService, private router: Router) {
        this.registerJobseeker = this.fb.group({
            email: this.email,
            username: this.username,
            password: this.pwd,
            first_name: this.first_name,
            last_name: this.last_name,
            captcha: this.captcha
        });

        this.isDev = !environment.production;
    }

    preFillJobseeker() {
        this.registerJobseeker.setValue({
            captcha: new FormControl(null).value,
            email: new FormControl('bcha2825@hotmail.fr').value,
            username: new FormControl('YassineDER').value,
            password: new FormControl('12345678').value,
            first_name: new FormControl('Yassine').value,
            last_name: new FormControl('DERGAOUI').value
        });
    }

    async submitRegisterForm() {
        const captcha = await this.auth.executeRecaptchaV3('RegisterJobseeker')
        this.registerJobseeker.controls['captcha'].setValue(captcha);

        if (this.utils.checkFormValidity(this.registerJobseeker)) {
            await this.auth.registerJobseeker(this.registerJobseeker.value)
                .then((res) => this.router.navigate(['/account/verify'])
                        .then(() => this.utils.alert(res, AlertType.SUCCESS)))
                .catch((err) => this.utils.alert(err.error.error, AlertType.ERROR));

            this.registerJobseeker.reset();
        }
    }

}
