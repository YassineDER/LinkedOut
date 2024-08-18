import {Injectable} from '@angular/core';
import {AlertType} from '../modules/shared/utils/alert-type';
import {FormGroup} from "@angular/forms";
import {AuthService} from "./auth.service";
import {Role} from "../models/role";
import {Router} from "@angular/router";
import {UtilsService} from "./utils.service";
import {Path} from "../modules/shared/utils/path";

@Injectable({
    providedIn: 'root'
})
export class FormsService {
    constructor(private auth: AuthService, private router: Router,
                private utils: UtilsService) {}

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

    /**
     * Submit the register form and redirect to the verify email page if successful
     * @param form The register form
     * @param role The role of the user to register
     * @return Promise<void> A promise that resolves when the form is submitted
     */
    async submitRegisterForm(form: FormGroup, role: Role) {
        if (this.checkFormValidity(form)) {
            await this.auth.register(form.value, role)
                .then((res) =>  this.router.navigate([Path.VERIFY_EMAIL.toString()])
                    .then(() => this.utils.alert(res, AlertType.SUCCESS)))
                .catch((err) => this.utils.alert(err.error.error, AlertType.ERROR));
        }

        form.reset();
    }

    /**
     * Trim all the values of a form
     * @param form
     */
    trimFormValues(form: FormGroup) {
        for (const control in form.controls) {
            let controlValue = form.controls[control].value;
            if (typeof controlValue === 'string') {
                controlValue = controlValue.replace(/\s/g, '');
                form.controls[control].setValue(controlValue);
            }
        }
    }

}
