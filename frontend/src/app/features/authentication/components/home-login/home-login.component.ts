import {Component} from '@angular/core';
import {AlertType} from '../../../shared/utils/alert-type';
import {AuthService} from "../../../../services/auth.service";
import {UtilsService} from "../../../../services/utils.service";
import {Path} from "../../../shared/utils/path";

@Component({
    selector: 'app-home-component-login',
    templateUrl: './home-login.component.html',
    styleUrl: './home-login.component.css'
})
export class HomeLoginComponent {
    constructor(private utils: UtilsService, private auth: AuthService) {
    }

    async googleLogin() {
        // this.utils.alert('Google login is not available at the moment', AlertType.ERROR);
        await this.auth.simulateLongRequest()
            .then((res) => this.utils.alert(res.response, AlertType.SUCCESS))
            .catch((err) => this.utils.alert(err.error.error));
    }

    protected readonly Path = Path;
}
