import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-account-infos-modal',
    templateUrl: './account-form-modal.component.html',
    styleUrl: './account-form-modal.component.css'
})
export class AccountFormModalComponent {
    accountForm: FormGroup;

    constructor(private fb: FormBuilder) {
        this.accountForm = this.fb.group({

        });
    }


    submitUserModification() {
        console.log(this.accountForm.value);
    }
}
