import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../service/storage.service';
import {AuthService} from "../../../service/auth.service";

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
	passVisible = false;
	loginFrom: FormGroup;

	constructor(private fb: FormBuilder, private auth: AuthService){
		this.loginFrom = this.fb.group({
			email: ['', [Validators.required, Validators.email] ],
			password: ['', [Validators.required] ]
		});
	}


	ngOnInit() {

	}


	submitLoginform(){
		console.log(this.loginFrom.value)
	}

}
