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
        this.registerJobseeker.setValue({
            captcha: new FormControl(null).value,
            email: new FormControl('jobseeker@example.com').value,
            username: new FormControl('YassineDER').value,
            password: new FormControl('12345678').value,
            first_name: new FormControl('Yassine').value,
            last_name: new FormControl('DERGAOUI').value
        });
    }

    async submitRegistration() {
        await this.formsSrv.submitRegisterForm(this.registerJobseeker, Role.JOBSEEKER);
    }

    protected readonly Path = Path;
}
