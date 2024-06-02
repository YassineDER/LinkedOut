import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AlertType} from '../shared/utils/AlertType';
import {FormGroup} from "@angular/forms";

@Injectable({
    providedIn: 'root'
})
export class UtilsService {
    private alertSubject = new BehaviorSubject<{ message: string, type: AlertType } | null>(null);
    alert$ = this.alertSubject.asObservable();

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


}
