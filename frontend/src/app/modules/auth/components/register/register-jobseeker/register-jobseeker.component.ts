import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {environment} from "../../../../../../environments/environment";
import {Role} from "../../../../../models/role";
import {FormsService} from "../../../../../services/forms.service";
import {Path} from "../../../../shared/utils/path";

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

    constructor(private fb: FormBuilder, private formsSrv: FormsService) {
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
        this.randomJobseeker().then(randomUser => {
            this.registerJobseeker.setValue({
                captcha: new FormControl(null).value,
                email: new FormControl(randomUser.email).value,
                username: new FormControl(randomUser.username).value,
                image_url: new FormControl(randomUser.image).value,
                password: new FormControl('12345678').value,
                first_name: new FormControl(randomUser.first_name).value,
                last_name: new FormControl(randomUser.last_name).value
            });
        })
    }

    private async randomJobseeker() {
        let randomUser: any;
        const response = await fetch('https://randomuser.me/api/');
        const data = await response.json();
        randomUser = {
            email: data.results[0].email,
            username: data.results[0].login.username,
            first_name: data.results[0].name.first,
            last_name: data.results[0].name.last,
            image: data.results[0].picture.large
        };
        return randomUser;
    }

    async submitRegistration() {
        this.formsSrv.trimFormValues(this.registerJobseeker);
        await this.formsSrv.submitRegisterForm(this.registerJobseeker, Role.JOBSEEKER);
    }

    protected readonly Path = Path;
}
