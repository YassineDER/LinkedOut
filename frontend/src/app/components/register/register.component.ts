import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  hide = true;
  registerFrom: FormGroup;
  usernames = ["user1", "user2", "user3", "user4", "user6", "user7"];

  constructor(private fb: FormBuilder) {
    this.registerFrom = this.fb.group({
      first_name: ['', [Validators.required]],
      last_name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirm_password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }


  formIsValid() {
    const username = this.registerFrom.value.username;
  
    return this.registerFrom.valid
      && this.registerFrom.value.password === this.registerFrom.value.confirm_password
      && !this.usernames.includes(username);
  }

  
  submitRegisterForm() {
    console.log(this.registerFrom.value);
  }

}
