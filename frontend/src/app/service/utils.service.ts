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

    alert(message: string, type: AlertType = AlertType.DEFAULT) {
        this.alertSubject.next({message, type});
    }


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
