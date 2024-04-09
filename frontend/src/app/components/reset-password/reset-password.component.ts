import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SnackBarService } from '../../service/snack-bar.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  resetForm: FormGroup;

	constructor(private fb: FormBuilder, private snack: SnackBarService){
		this.resetForm = this.fb.group({
			password: ['', [Validators.required, Validators.minLength(6)] ],
      confirm_password: ['', [Validators.required, Validators.minLength(6)] ]
		});
	}

  submitResetForm(){
    console.log(this.resetForm.value);
  }

  passwordsAreSame() { 
    let pass = this.resetForm.controls['password'].value;
    let confirmPass = this.resetForm.controls['confirm_password'].value;

    return pass === confirmPass ? true : false;
  }

  linkIsValid() {
    
  }
}
