import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../../service/alert.service';
import {AlertType} from "../../../shared/utils/AlertType";
import {ReCaptchaV3Service} from 'ng-recaptcha';
import {environment} from "../../../../environments/environment";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrl: './register.component.css'
})
export class RegisterComponent {
    passVisible = false;
    isDev = false;
    registerFrom: FormGroup;
    email = new FormControl('', [Validators.required, Validators.email]);
    username = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]);
    pwd = new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]);
    first_name = new FormControl('', [Validators.required]);
    last_name = new FormControl('', [Validators.required]);
    captcha = new FormControl(null, [Validators.required]);

    constructor(private fb: FormBuilder, private alert: AlertService,
                private recaptchaV3: ReCaptchaV3Service) {
        this.registerFrom = this.fb.group({
            email: this.email,
            username: this.username,
            password: this.pwd,
            first_name: this.first_name,
            last_name: this.last_name,
            captcha: this.captcha
        });

        this.isDev = !environment.production;
    }

    executeRecaptchaV3() {
        let token;
        this.recaptchaV3.execute('Register')
            .subscribe({
                next: (tk) => {
                    if (tk) token = tk;
                },
                error: (error) => {
                    this.alert.show('Erreur lors de la validation du captcha: ' + error, AlertType.ERROR);
                }
            });

        this.registerFrom.controls['captcha'].setValue(token);
        return token;
    }

    preFillForm() {
        this.registerFrom.setValue({
            captcha: new FormControl(this.executeRecaptchaV3()).value,
            email: new FormControl('bcha2825@hotmail.fr').value,
            username: new FormControl('YassineDER').value,
            password: new FormControl('123456').value,
            first_name: new FormControl('Yassine').value,
            last_name: new FormControl('DERGAOUI').value }
        );
    }

    submitRegisterForm(event: Event) {
        event.preventDefault();
        this.executeRecaptchaV3();
        for (let i in this.registerFrom.controls) {
            if (this.registerFrom.controls[i].errors) {
                this.registerFrom.controls[i].markAsTouched();
                return this.alert.show('Le champ ' + i + ' est invalide', AlertType.ERROR);
            }
        }

        console.log(this.registerFrom.value);
    }

}
