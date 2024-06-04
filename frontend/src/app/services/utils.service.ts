import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AlertType} from '../shared/utils/AlertType';
import {FormGroup} from "@angular/forms";
import {AuthService} from "./auth.service";
import {Role} from "../models/role";
import {Router} from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class UtilsService {
    private alertSubject = new BehaviorSubject<{ message: string, type: AlertType } | null>(null);
    alert$ = this.alertSubject.asObservable();

    constructor(private auth: AuthService, private router: Router) {
    }

    /**
     * Display an alert message using the alert component in the app.
     * @param message The message to display
     * @param type The type of alert to display.
     * @default type AlertType.DEFAULT
     */
    alert(message: string, type: AlertType = AlertType.DEFAULT) {
        this.alertSubject.next({message, type});
    }


    /**
     * Check if a form is valid and mark all invalid fields as touched
     * @param form The form to check
     * @return boolean True if the form is valid, false otherwise
     */
    checkFormValidity(form: FormGroup) : boolean {
        for (let i in form.controls) {
            if (form.controls[i].errors) {
                form.controls[i].markAsTouched();
                this.alert('Le champ ' + i + ' est invalide', AlertType.ERROR);
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
                    .then(() => this.alert(res, AlertType.SUCCESS)))
                .catch((err) => this.alert(err.error.error, AlertType.ERROR));
        }

        form.reset();
    }

}
