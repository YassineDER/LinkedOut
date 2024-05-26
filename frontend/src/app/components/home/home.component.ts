import { Component } from '@angular/core';
import { AlertService } from '../../service/alert.service';
import { AlertType } from '../../shared/utils/AlertType';
import { NgOptimizedImage } from '@angular/common'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private alertService: AlertService) { }

  googleLogin() {
    this.alertService.show('Google login is not available at the moment', AlertType.ERROR);
  }

}
