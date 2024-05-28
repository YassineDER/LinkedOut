import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../../service/alert.service";
import {environment} from "../../../../../environments/environment";
import {AuthService} from "../../../../service/auth.service";
import {AlertType} from "../../../../shared/utils/AlertType";

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

    constructor(private fb: FormBuilder, private alert: AlertService,
                private auth: AuthService) {
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

    submitRegisterForm(event: Event) {
        event.preventDefault();
        this.auth.executeRecaptchaV3("RegisterJobseeker")
            .then(token => this.registerJobseeker.controls['captcha'].setValue(token));

        for (let i in this.registerJobseeker.controls) {
            if (this.registerJobseeker.controls[i].errors) {
                this.registerJobseeker.controls[i].markAsTouched();
                return this.alert.show('Le champ ' + i + ' est invalide', AlertType.ERROR);
            }
        }

        console.log('Form submitted: ', this.registerJobseeker.value);
    }
}
