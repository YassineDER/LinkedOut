import {Injectable} from '@angular/core';
import {AlertType} from '../../../shared/utils/AlertType';
import {FormGroup} from "@angular/forms";
import {AuthService} from "./auth.service";
import {Role} from "../../../models/role";
import {Router} from "@angular/router";
import {UtilsService} from "../../../services/utils.service";

@Injectable({
    providedIn: 'root'
})
export class FormsService {
    constructor(private auth: AuthService, private router: Router, private utils: UtilsService) {}

    /**
     * Check if a form is valid and mark all invalid fields as touched
     * @param form The form to check
     * @return boolean True if the form is valid, false otherwise
     */
    checkFormValidity(form: FormGroup) : boolean {
        for (let i in form.controls) {
            if (form.controls[i].errors) {
                form.controls[i].markAsTouched();
                this.utils.alert('Le champ ' + i + ' est invalide', AlertType.ERROR);
                return false;
            }
        }

        return true;
    }


    async submitRegisterForm(form: FormGroup, role: Role) {
        const captcha = await this.auth.executeRecaptchaV3('Register_' + role.toString())
        form.controls['captcha'].setValue(captcha);

        if (this.checkFormValidity(form)) {
            await this.auth.register(form.value, role)
                .then((res) =>  this.router.navigate(['/account/verify'])
                    .then(() => this.utils.alert(res, AlertType.SUCCESS)))
                .catch((err) => this.utils.alert(err.error.error, AlertType.ERROR));
        }

        form.reset();
    }

}
