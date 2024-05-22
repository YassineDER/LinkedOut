import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../service/storage.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
	passVisible = false;
	loginFrom: FormGroup;

	constructor(private fb: FormBuilder, private storage: StorageService){
		this.loginFrom = this.fb.group({
			email: ['', [Validators.required, Validators.email] ],
			password: ['', [Validators.required] ]
		});
	}


	ngOnInit() {

	}

	test() {
		this.storage.test();
	}


	submitLoginform(){
		console.log(this.loginFrom.value)
	}

	verifyEmail() {
		return this.loginFrom.controls['email'].valid;
	}

}
