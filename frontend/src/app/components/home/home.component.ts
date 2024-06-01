import { Component } from '@angular/core';
import { UtilsService } from '../../service/utils.service';
import { AlertType } from '../../shared/utils/AlertType';
import { NgOptimizedImage } from '@angular/common'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private alertService: UtilsService) { }

  googleLogin() {
    this.alertService.alert('Google login is not available at the moment', AlertType.ERROR);
  }

}
