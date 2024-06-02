import {Component} from '@angular/core';
import {UtilsService} from '../../services/utils.service';
import {AlertType} from '../../shared/utils/AlertType';
import {NgOptimizedImage} from '@angular/common'
import {AuthService} from "../../services/auth.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrl: './home.component.css'
})
export class HomeComponent {
    constructor(private utils: UtilsService, private auth: AuthService) {
    }

    async googleLogin() {
        // this.utils.alert('Google login is not available at the moment', AlertType.ERROR);
        await this.auth.simulateLongRequest()
            .then((msg) => this.utils.alert(msg, AlertType.SUCCESS))
            .catch((err) => this.utils.alert(err.error.error, AlertType.ERROR));
    }

}
